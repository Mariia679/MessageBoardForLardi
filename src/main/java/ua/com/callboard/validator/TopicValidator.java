package ua.com.callboard.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;

public class TopicValidator implements Validator {

	private final TopicService service;

	public TopicValidator(TopicService service) {
		this.service = service;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Topic.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Topic topic = (Topic) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "",
				"Can`t be empty");
		if (service.findOne(topic.getName()) != null) {
			errors.rejectValue("name", "", "Already exist");
		}
	}
}
