package ua.com.callboard.editor;

import java.beans.PropertyEditorSupport;

import ua.com.callboard.entity.Topic;
import ua.com.callboard.service.TopicService;

public class TopicEditor extends PropertyEditorSupport {

	private final TopicService service;

	public TopicEditor(TopicService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Topic topic = service.findOne(Long.valueOf(text));
		setValue(topic);
	}
}
