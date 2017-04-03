package ua.test.editor;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import ua.com.callboard.editor.AdvertEditor;
import ua.com.callboard.service.AdvertService;

public class AdvertEditorTest {

	AdvertEditor editor;

	AdvertService service;

	@Before
	public void setUp() {
		service = mock(AdvertService.class);
		editor = new AdvertEditor(service);
		ReflectionTestUtils.setField(editor, "service", service);
	}

	@Test
	public void setAsText() {
		editor.setAsText("1");
		Assert.assertTrue(true);
	}
}
