package ua.com.callboard.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.callboard.entity.User;
import ua.com.callboard.service.UserService;

public class UserValidator implements Validator {

	/**
	 * A compiled representation of a regular expression.
	 * 
	 * @param REG
	 *            for validate an email
	 */

	private static final Pattern REG = Pattern
			.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

	private final UserService service;

	public UserValidator(UserService service) {
		this.service = service;
	}

	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "",
				"Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
				"Can't be empty");
		if (!REG.matcher(user.getEmail()).matches()) {
			errors.rejectValue("email", "", "Probably you were mistaken");
		} else if (errors.getFieldError("email") == null) {
			if (service.findUnique(user.getEmail(), user.getUsername()) != null) {
				errors.rejectValue("username", "", "Already exist");
			}
		}
	}
}
