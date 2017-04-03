package ua.com.callboard.controller.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.editor.AdvertEditor;
import ua.com.callboard.editor.TopicEditor;
import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.entity.User;
import ua.com.callboard.service.AdvertService;
import ua.com.callboard.service.TopicService;
import ua.com.callboard.service.UserService;
import ua.com.callboard.validator.AdvertValidator;

@Controller
@RequestMapping("/allTopics")
@SessionAttributes("advert")
public class AllTopicsController {

	@Autowired
	private AdvertService advertService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	/**
	 * This const used for test
	 */

	public static final String VIEW_TODO = "/allTopics";
	public static final String VIEW_TODO_ADD = "user-allTopics";

	public static final String MODEL_ATTRIBUTE_TODO = "ads";
	public static final String MODEL_ATTRIBUTE = "headTopics";
	public static final String MODEL_ATTRIBUTE_PAGE = "page";

	/**
	 * Such init-binder methods support all arguments that RequestMapping
	 * supports, except for command/form objects and corresponding validation
	 * result objects.
	 * 
	 * @param binder
	 *            Includes support for field markers which address a common
	 *            problem with HTML checkboxes and select options: detecting
	 *            that a field was part of the form, but did not generate a
	 *            request parameter because it was empty. A field marker allows
	 *            to detect that state and reset the corresponding bean property
	 *            accordingly.
	 */
	@InitBinder("advert")
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Advert.class, new AdvertEditor(
				advertService));
		binder.registerCustomEditor(Topic.class, new TopicEditor(topicService));
		binder.setValidator(new AdvertValidator());
	}

	/**
	 * Annotation that binds a method parameter or method return value to a
	 * named model attribute, exposed to a web view.
	 * 
	 * @return new Advert in method save()
	 */
	@ModelAttribute("advert")
	public Advert getForm() {
		return new Advert();
	}

	/**
	 * 
	 * @return new AdvertFilter in method show(),
	 */

	@ModelAttribute("filter")
	public AdvertFilter getFilter() {
		return new AdvertFilter();
	}

	/**
	 * 
	 * Substitutes all the rubrics for a link "headTopics" on the page of the
	 * jsp which is in the user folder under the name allTopics
	 * 
	 * @param model
	 * @return folder user file allTopics.jsp
	 */

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable,
			@ModelAttribute("filter") AdvertFilter filter, Principal principal) {
		model.addAttribute("headTopics", topicService.findAll());
		model.addAttribute("page", advertService.findAll(filter, pageable));
		model.addAttribute("ads",
				userService.findByUsernameForCheckbox(principal.getName()));

		return "user-allTopics";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, SessionStatus status) {
		advertService.delete(id);
		status.setComplete();
		return "redirect:/allTopics";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model,
			@PageableDefault Pageable pageable,
			@ModelAttribute("filter") AdvertFilter filter, Principal principal,
			SessionStatus status) {
		model.addAttribute("advert", advertService.findOne(id));
		return show(model, pageable, filter, principal);
	}

	@GetMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/allTopics";
	}

	/**
	 * 
	 * @param topic
	 *            is a Valid object which is returned by reference "advert"
	 * @param br
	 *            General interface that represents binding results
	 * @param model
	 * @param status
	 * @return redirect to allTopics
	 */
	@PostMapping
	public String save(@ModelAttribute("advert") @Valid Advert advert,
			BindingResult br, @PageableDefault Pageable pageable,
			Principal principal, @ModelAttribute("filter") AdvertFilter filter,
			Model model, SessionStatus status) {
		if (br.hasErrors()) {
			return show(model, pageable, filter, principal);
		}

		/**
		 * @param epoch
		 *            save date in UNIX system
		 */
		Long epoch = System.currentTimeMillis() / 1000;
		User user = userService.findByUsername(principal.getName());
		advert.setDate(epoch);
		advert.setUser(user);
		advertService.save(advert);
		status.setComplete();
		return "redirect:/allTopics";
	}

}
