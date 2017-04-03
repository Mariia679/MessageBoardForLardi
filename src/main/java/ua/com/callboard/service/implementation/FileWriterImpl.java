package ua.com.callboard.service.implementation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.callboard.service.FileWriter;

@Service
public class FileWriterImpl implements FileWriter {

	/**
	 * Records a picture
	 */
	@Override
	public boolean write(Folder folder, MultipartFile file, Long id) {
		if (file != null && !file.isEmpty()) {
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "images" + File.separator
					+ folder.name().toLowerCase());
			if (!pathToFolder.exists()) {
				pathToFolder.mkdirs();
			}
			try {
				InputStream in = new ByteArrayInputStream(file.getBytes());
				BufferedImage old = ImageIO.read(in);
				File pathToFile = new File(pathToFolder, String.valueOf(id)
						+ ".jpg");
				ImageIO.write(old, "jpg", pathToFile);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
