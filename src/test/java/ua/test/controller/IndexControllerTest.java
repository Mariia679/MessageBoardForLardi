package ua.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.support.BindingAwareModelMap;

import ua.com.callboard.controller.admin.TopicController;
import ua.com.callboard.controller.user.IndexController;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;

public class IndexControllerTest {

	private IndexController controller;

	private TopicService topicServiceMock;

	@Before
	public void setUp() {
		controller = new IndexController();
		topicServiceMock = mock(TopicService.class);

		ReflectionTestUtils.setField(controller, "topicService",
				topicServiceMock);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void index() {
		BindingAwareModelMap model = new BindingAwareModelMap();
		String view = controller.index();
		assertEquals(IndexController.VIEW_TODO_ADD, view);
		List<Topic> formObject = (List<Topic>) model.asMap().get(
				TopicController.MODEL_ATTRIBUTE);
	}

	@Test
	public void admin() {
		String view = controller.admin();
		assertEquals(IndexController.VIEW_TODO_ADMIN, view);

	}

	@Test
	public void login() {
		String view = controller.login();
		assertEquals(IndexController.VIEW_TODO_LOGIN, view);

	}
}
