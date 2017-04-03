package ua.com.callboard.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;
import ua.com.callboard.validator.TopicValidator;

/**
 * Indicates that an annotated class is a "Controller" (e.g. a web controller).
 * 
 * Annotation @RequestMapping for mapping web requests onto specific handler
 * classes and/or handler methods.
 * 
 * Annotation @SessionAttributes that indicates the session attributes that a
 * specific handler uses.
 *
 */
@Controller
@RequestMapping("/admin/topic")
@SessionAttributes("topic")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	/**
	 * This const used for test
	 */

	public static final String VIEW_TODO_ADD = "admin-topic";
	public static final String VIEW_TODO_ADD_SAVE = "/admin/topic";

	public static final String MODEL_ATTRIBUTE_TODO = "topics";
	public static final String MODEL_ATTRIBUTE = "headTopics";

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

	@InitBinder("topic")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new TopicValidator(topicService));
	}

	/**
	 * Annotation that binds a method parameter or method return value to a
	 * named model attribute, exposed to a web view.
	 * 
	 * @return new Topic in method save()
	 */
	@ModelAttribute("topic")
	public Topic getForm() {
		return new Topic();
	}

	/**
	 * 
	 * Substitutes all the rubrics for a link "topics" on the page of the jsp
	 * which is in the admin folder under the name topic
	 * 
	 * @param model
	 * @return folder admin file topic.jsp
	 */
	@RequestMapping
	public String show(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "admin-topic";
	}

	/**
	 * Annotation @PathVariable which indicates that a method parameter should
	 * be bound to a URI template variable. Supported for {@link RequestMapping}
	 * annotated handler methods in Servlet environments.
	 * 
	 * @param id
	 *            topic id
	 * @return redirect to folder admin file topic.jsp. A redirect is needed in
	 *         order to delete only once an object
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		topicService.delete(id);
		return "redirect:/admin/topic";
	}

	/**
	 * 
	 * @param id
	 *            topic id
	 * @param model
	 * @return method show where in the form we will see the completed fields
	 *         that can be fixed and updated
	 * 
	 */
	@RequestMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("topic", topicService.findOne(id));
		return show(model);
	}

	/**
	 * Simple interface SessionStatus that can be injected into handler methods,
	 * allowing them to signal that their session processing is complete.
	 * 
	 * Mark the current handler's session processing as complete, allowing for
	 * cleanup of session attributes.
	 * 
	 * @param status
	 * @return redirect to  admin/topic
	 */
	@GetMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/topic";
	}

	/**
	 * 
	 * @param topic
	 *            is a Valid object which is returned by reference "topic"
	 * @param br
	 *            General interface that represents binding results
	 * @param model
	 * @param status
	 * @return redirect to admin/topic
	 */
	@PostMapping
	public String save(@ModelAttribute("topic") @Valid Topic topic,
			BindingResult br, Model model, SessionStatus status) {
		if (br.hasErrors()) {
			return show(model);
		}
		topicService.save(topic);
		status.setComplete();
		return "redirect:/admin/topic";
	}
}
