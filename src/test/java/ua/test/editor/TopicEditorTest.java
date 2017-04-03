package ua.test.editor;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import ua.com.callboard.editor.TopicEditor;
import ua.com.callboard.service.TopicService;

public class TopicEditorTest {

	TopicEditor editor;

	TopicService service;

	@Before
	public void setUp() {
		service = mock(TopicService.class);
		editor = new TopicEditor(service);
		ReflectionTestUtils.setField(editor, "service", service);
	}

	@Test
	public void setAsText() {
		editor.setAsText("1");
		Assert.assertTrue(true);
	}
}
