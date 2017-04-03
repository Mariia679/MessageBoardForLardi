package ua.test.validator;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.Errors;

import ua.com.callboard.entity.User;
import ua.com.callboard.service.UserService;
import ua.com.callboard.validator.UserValidator;
import ua.test.config.UserTestUtil;

public class UserValidatorTest {

	UserValidator validator;

	UserService service = mock(UserService.class);

	@Before
	public void setUp() {
		validator = new UserValidator(service);

		ReflectionTestUtils.setField(validator, "service", service);
	}

	@Test
	public void validate() {
		User formObject = UserTestUtil.createFormObject(null,
				UserTestUtil.NAME, UserTestUtil.ADVERTS, UserTestUtil.EMAIL,
				UserTestUtil.PASSWORD, UserTestUtil.USERNAME);
		Errors errors = mock(Errors.class);
		validator.validate(formObject, errors);
	}
}
