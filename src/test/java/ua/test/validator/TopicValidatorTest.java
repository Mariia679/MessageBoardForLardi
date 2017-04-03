package ua.test.validator;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.Errors;

import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;
import ua.com.callboard.validator.TopicValidator;
import ua.test.config.TopicTestUtil;

public class TopicValidatorTest {

	TopicValidator validator;

	TopicService service = mock(TopicService.class);

	@Before
	public void setUp() {
		validator = new TopicValidator(service);

		ReflectionTestUtils.setField(validator, "service", service);
	}

	@Test
	public void validate() {
		Topic formObject = TopicTestUtil.createFormObject(null,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);
		Errors errors = mock(Errors.class);
		validator.validate(formObject, errors);
	}
}
