package ua.test.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;

public class TopicTestUtil {

	public static final Long ID = 1L;
	public static final String NAME = "name";
	public static final List<Advert> ADVERTS = new ArrayList<>();

	public static Topic createFormObject(Long id, String name,
			List<Advert> adverts) {
		Topic topic = new Topic();

		topic.setId(id);
		topic.setName(name);

		topic.setAdverts(adverts);

		return topic;
	}

	public static Topic createModel(Long id, String name, List<Advert> adverts) {
		Topic model = new Topic();
		model.setAdverts(adverts);
		model.setName(name);
		ReflectionTestUtils.setField(model, "id", id);

		return model;
	}

	public static String createRedirectViewPath(String path) {
		StringBuilder redirectViewPath = new StringBuilder();
		redirectViewPath.append("redirect:");
		redirectViewPath.append(path);
		return redirectViewPath.toString();
	}

}
