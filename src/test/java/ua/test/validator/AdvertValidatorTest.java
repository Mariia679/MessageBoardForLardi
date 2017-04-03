package ua.test.validator;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.Errors;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.service.AdvertService;
import ua.com.callboard.validator.AdvertValidator;
import ua.test.config.AdvertTestUtil;

public class AdvertValidatorTest {

	AdvertValidator validator;

	AdvertService service = mock(AdvertService.class);

	@Before
	public void setUp() {
		validator = new AdvertValidator();
	}

	@Test
	public void validate() {
		Advert formObject = AdvertTestUtil.createFormObject(null,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);
		Errors errors = mock(Errors.class);
		validator.validate(formObject, errors);
	}
}
