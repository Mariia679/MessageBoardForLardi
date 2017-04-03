package ua.com.callboard.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.callboard.entity.User;
import ua.com.callboard.service.UserService;
import ua.com.callboard.validator.UserValidator;

@Controller
@RequestMapping("/admin/registration")
@SessionAttributes("userForm")
public class RegistrationUserController {

	@Autowired
	private UserService userService;

	/**
	 * This const used for test
	 */

	public static final String VIEW_TODO = "/admin/registration";

	public static final String VIEW_TODO_REG = "admin-registration";

	public static final String MODEL_ATTRIBUTE = "userForm";

	public static final String VIEW_TODO_ADMIN = "/admin";

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
	@InitBinder("userForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator(userService));
	}

	/**
	 * Simple interface SessionStatus that can be injected into handler methods,
	 * allowing them to signal that their session processing is complete.
	 * 
	 * Mark the current handler's session processing as complete, allowing for
	 * cleanup of session attributes.
	 * 
	 * @param status
	 * @return redirect to admin/registration
	 */

	@GetMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/admin/registration";
	}

	/**
	 * 
	 * @param model
	 * @return folder admin file registration.jsp
	 */

	@GetMapping
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "admin-registration";
	}

	/**
	 * 
	 * @param user
	 *            is a Valid object which is returned by reference "userForm"
	 * @param br
	 *            General interface that represents binding results
	 * @param model
	 * @param status
	 * @return redirect to admin
	 */
	@PostMapping
	public String registration(@ModelAttribute("userForm") @Valid User user,
			BindingResult br, Model model, SessionStatus status) {
		if (br.hasErrors())
			return "admin-registration";
		userService.save(user);
		status.setComplete();
		return "redirect:/admin";
	}

}
