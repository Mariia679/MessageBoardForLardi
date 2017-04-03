package ua.test.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.service.FileWriter.Folder;
import ua.com.callboard.service.implementation.FileWriterImpl;

public class FileWriterTest {

	FileWriterImpl fileWriterImpl;
	
	MockMultipartFile file;

	@Before
	public void before() {
		fileWriterImpl = new FileWriterImpl();
		file = new MockMultipartFile("File", "file2.jpg", "img", new byte[] {
				1, 2, 3, 4, 5, 66, 7, 7, 8, 9, 77, 8, 9, 0 });
	}

	@Test
	public void write() throws IOException {
		Advert advert = new Advert();
		advert.setId(1L);
		fileWriterImpl.write(Folder.ADVERT, file, advert.getId());
	}
}
