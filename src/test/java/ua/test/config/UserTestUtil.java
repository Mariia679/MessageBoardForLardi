package ua.test.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Role;
import ua.com.callboard.entity.User;

public class UserTestUtil {

	public static final Long ID = 1L;
	public static final String NAME = "name";
	public static final List<Advert> ADVERTS = new ArrayList<>();
	public static final String EMAIL = "ssandy679@gmail.com";
	public static final String PASSWORD = "ssandy679";
	public static final String USERNAME = "mariia";

	public static User createFormObject(Long id, String name,
			List<Advert> adverts, String email, String password, String username) {
		User user = new User();

		user.setId(id);
		user.setUsername(name);
		user.setAdverts(adverts);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(Role.ROLE_USER);
		return user;
	}

	public static User createModel(Long id, String name, List<Advert> adverts,
			String email, String password, String username) {
		User model = new User();
		model.setAdverts(adverts);
		model.setUsername(name);
		model.setEmail(email);
		model.setPassword(password);
		model.setRole(Role.ROLE_USER);
		ReflectionTestUtils.setField(model, "id", id);

		return model;
	}

}
