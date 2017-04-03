package ua.com.callboard.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.callboard.entity.Advert;

public class AdvertValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Advert.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Advert advert = (Advert) target;
		System.out.println();
		System.out.println(advert);
		System.out.println();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "review", "",
				"Can`t be empty");
		if (advert.getName().length() > 30) {
			errors.rejectValue("name", "", "Too long");
		}
		if (advert.getReview().length() > 400) {
			errors.rejectValue("review", "", "Too long");
		}
	}
}
