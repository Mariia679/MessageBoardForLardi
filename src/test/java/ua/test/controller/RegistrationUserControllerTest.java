package ua.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.callboard.controller.user.RegistrationUserController;
import ua.com.callboard.entity.User;
import ua.com.callboard.service.UserService;
import ua.com.callboard.validator.UserValidator;
import ua.test.config.TopicTestUtil;
import ua.test.config.UserTestUtil;

public class RegistrationUserControllerTest {

	private RegistrationUserController controller;

	private UserService serviceMock;

	@Resource
	private UserValidator validator;

	@Before
	public void setUp() {
		controller = new RegistrationUserController();
		serviceMock = mock(UserService.class);

		ReflectionTestUtils.setField(controller, "userService", serviceMock);
	}

	@Test
	public void cancel() {
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.cancel(session);
		String expectedView = TopicTestUtil
				.createRedirectViewPath(RegistrationUserController.VIEW_TODO);
		assertEquals(expectedView, view);
	}

	@Test
	public void registartion() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		String view = controller.registration(model);
		assertEquals(RegistrationUserController.VIEW_TODO_REG, view);
		User formObject = (User) model.asMap().get(
				RegistrationUserController.MODEL_ATTRIBUTE);

		assertNull(formObject.getId());
		assertNull(formObject.getEmail());
		assertNull(formObject.getPassword());

		assertNull(formObject.getRole());
		assertNull(formObject.getUsername());
		assertNotNull(formObject.getAdverts());

	}

	@Test
	public void add() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		User formObject = UserTestUtil.createFormObject(null,
				UserTestUtil.NAME, UserTestUtil.ADVERTS, UserTestUtil.EMAIL,
				UserTestUtil.PASSWORD, UserTestUtil.USERNAME);

		User modelUSer = UserTestUtil.createModel(UserTestUtil.ID,
				UserTestUtil.NAME, UserTestUtil.ADVERTS, UserTestUtil.EMAIL,
				UserTestUtil.PASSWORD, UserTestUtil.USERNAME);

		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST",
				"/admin/registration");
		BindingResult result = bindAndValidate(mockRequest, formObject);
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.registration(formObject, result, model,
				session);
		WebDataBinder binder = new WebDataBinder(formObject);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(mockRequest.getParameterMap()));
		binder.getValidator();
		controller.initBinder(binder);
		verify(serviceMock, times(1)).save(formObject);
		verifyNoMoreInteractions(serviceMock);

		String expectedView = TopicTestUtil
				.createRedirectViewPath(RegistrationUserController.VIEW_TODO_ADMIN);
		assertEquals(expectedView, view);

	}

	private BindingResult bindAndValidate(HttpServletRequest request,
			Object formObject) {
		WebDataBinder binder = new WebDataBinder(formObject);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
		binder.getValidator();
		return binder.getBindingResult();
	}
}
