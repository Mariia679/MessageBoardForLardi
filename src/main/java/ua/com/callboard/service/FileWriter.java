package ua.com.callboard.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder {
		ADVERT
	}

	boolean write(Folder folder, MultipartFile file, Long id);
}
