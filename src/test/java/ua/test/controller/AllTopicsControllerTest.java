package ua.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.callboard.controller.admin.TopicController;
import ua.com.callboard.controller.user.AllTopicsController;
import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.AdvertService;
import ua.com.callboard.service.TopicService;
import ua.com.callboard.service.UserService;
import ua.com.callboard.validator.AdvertValidator;
import ua.test.config.AdvertTestUtil;
import ua.test.config.TopicTestUtil;

public class AllTopicsControllerTest {

	private AllTopicsController controller;

	private TopicService topicServiceMock;

	private AdvertService advertServiceMock;

	private UserService userServiceMock;

	@Resource
	private AdvertValidator validator;

	AdvertFilter advertFilter = mock(AdvertFilter.class);;

	Pageable pageable = mock(Pageable.class);

	@Before
	public void setUp() {
		controller = new AllTopicsController();
		topicServiceMock = mock(TopicService.class);
		advertServiceMock = mock(AdvertService.class);
		userServiceMock = mock(UserService.class);
		ReflectionTestUtils.setField(controller, "topicService",
				topicServiceMock);
		ReflectionTestUtils.setField(controller, "advertService",
				advertServiceMock);
		ReflectionTestUtils
				.setField(controller, "userService", userServiceMock);

	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Test
	public void show() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Pageable pageable = mock(Pageable.class);
		AdvertFilter filter = mock(AdvertFilter.class);
		Principal principal = mock(Principal.class);
		Advert formObject = AdvertTestUtil.createFormObject(null,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);
		String view = controller.show(model, pageable, filter, principal);
		assertEquals(AllTopicsController.VIEW_TODO_ADD, view);
		List<Topic> formObject1 = (List<Topic>) model.asMap().get(
				AllTopicsController.MODEL_ATTRIBUTE);
		List<Advert> formObjectAdvert = (List<Advert>) model.asMap().get(
				AllTopicsController.MODEL_ATTRIBUTE_PAGE);
	}

	@Test
	public void add() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Advert formObject = AdvertTestUtil.createFormObject(null,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);

		Advert modelAdvert = AdvertTestUtil.createFormObject(AdvertTestUtil.ID,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);
		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST",
				"/allTopics");
		BindingResult result = bindAndValidate(mockRequest, formObject);
		WebDataBinder binder = new WebDataBinder(formObject);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(mockRequest.getParameterMap()));
		binder.getValidator();
		assertEquals(formObject, controller.getForm());
		assertEquals(0, binder.getBindingResult().getErrorCount());
		Principal principal = mock(Principal.class);
		SessionStatus session = mock(SessionStatus.class);
		controller.initBinder(binder);
		advertFilter.setNameUser("name");
		String view = controller.save(formObject, result, pageable, principal,
				advertFilter, model, session);

		verify(advertServiceMock, times(1)).save(formObject);
		verifyNoMoreInteractions(advertServiceMock);

		String expectedView = TopicTestUtil
				.createRedirectViewPath(AllTopicsController.VIEW_TODO);
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

	@Test
	public void deleteById() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Advert formObject = AdvertTestUtil.createFormObject(null,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.delete(AdvertTestUtil.ID, session);

		verify(advertServiceMock, times(1)).delete(AdvertTestUtil.ID);
		verifyNoMoreInteractions(advertServiceMock);

		String expectedView = TopicTestUtil
				.createRedirectViewPath(AllTopicsController.VIEW_TODO);
		assertEquals(expectedView, view);
	}

	@Test
	public void cancel() {
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.cancel(session);
		String expectedView = TopicTestUtil
				.createRedirectViewPath(AllTopicsController.VIEW_TODO);
		assertEquals(expectedView, view);
	}

	@Test
	public void update() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Advert formObject = AdvertTestUtil.createFormObject(null,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);

		Advert modelTopic = AdvertTestUtil.createModel(AdvertTestUtil.ID,
				AdvertTestUtil.NAME, AdvertTestUtil.TOPIC,
				AdvertTestUtil.REVIEW, AdvertTestUtil.DATE,
				AdvertTestUtil.VERSION, AdvertTestUtil.USER);
		Principal principal = mock(Principal.class);
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.update(TopicTestUtil.ID, model, pageable,
				advertFilter, principal, session);

		assertEquals(AllTopicsController.VIEW_TODO_ADD, view);

	}

	@Test
	public void getFilter() {
		AdvertFilter filter = controller.getFilter();
		Assert.assertTrue(true);
	}
}
