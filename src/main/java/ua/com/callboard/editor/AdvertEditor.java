package ua.com.callboard.editor;

import java.beans.PropertyEditorSupport;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.service.AdvertService;

/**
 * 
 * PropertyEditorSupport - This is a support class to help build property
 * editors.
 *
 */
public class AdvertEditor extends PropertyEditorSupport {

	private final AdvertService service;

	public AdvertEditor(AdvertService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Advert advert = service.findOne(Long.valueOf(text));
		setValue(advert);
	}

}
