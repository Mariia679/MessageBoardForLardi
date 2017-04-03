package ua.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

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

import ua.com.callboard.controller.admin.TopicController;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;
import ua.com.callboard.validator.TopicValidator;
import ua.test.config.TopicTestUtil;

public class TopicControllerTest {

	private TopicController controller;

	private TopicService serviceMock;

	@Resource
	private TopicValidator validator;

	@Before
	public void setUp() {
		controller = new TopicController();
		serviceMock = mock(TopicService.class);

		ReflectionTestUtils.setField(controller, "topicService", serviceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void show() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		String view = controller.show(model);
		assertEquals(TopicController.VIEW_TODO_ADD, view);
		List<Topic> formObject = (List<Topic>) model.asMap().get(
				TopicController.MODEL_ATTRIBUTE_TODO);
		formObject = (List<Topic>) model.asMap().get(
				TopicController.MODEL_ATTRIBUTE);
	}

	@Test
	public void add() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Topic formObject = TopicTestUtil.createFormObject(null,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);

		Topic modelTopic = TopicTestUtil.createModel(TopicTestUtil.ID,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);

		MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST",
				"/admin/topic");
		BindingResult result = bindAndValidate(mockRequest, formObject);
		WebDataBinder binder = new WebDataBinder(formObject);
		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(mockRequest.getParameterMap()));
		binder.getValidator();
		assertEquals(formObject, controller.getForm());
		assertEquals(0, binder.getBindingResult().getErrorCount());
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.save(formObject, result, model, session);
		controller.initBinder(binder);
		verify(serviceMock, times(1)).save(formObject);
		verifyNoMoreInteractions(serviceMock);

		String expectedView = TopicTestUtil
				.createRedirectViewPath(TopicController.VIEW_TODO_ADD_SAVE);
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
		Topic model = TopicTestUtil.createModel(TopicTestUtil.ID,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);
		String view = controller.delete(TopicTestUtil.ID);

		verify(serviceMock, times(1)).delete(TopicTestUtil.ID);
		verifyNoMoreInteractions(serviceMock);

		String expectedView = TopicTestUtil
				.createRedirectViewPath(TopicController.VIEW_TODO_ADD_SAVE);
		assertEquals(expectedView, view);
	}

	@Test
	public void cancel() {
		Topic model = TopicTestUtil.createModel(TopicTestUtil.ID,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);
		SessionStatus session = mock(SessionStatus.class);
		String view = controller.cancel(session);
		String expectedView = TopicTestUtil
				.createRedirectViewPath(TopicController.VIEW_TODO_ADD_SAVE);
		assertEquals(expectedView, view);
	}

	@Test
	public void update() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		Topic formObject = TopicTestUtil.createFormObject(null,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);

		Topic modelTopic = TopicTestUtil.createModel(TopicTestUtil.ID,
				TopicTestUtil.NAME, TopicTestUtil.ADVERTS);
		String view = controller.update(TopicTestUtil.ID, model);

		assertEquals(TopicController.VIEW_TODO_ADD, view);

	}
}
