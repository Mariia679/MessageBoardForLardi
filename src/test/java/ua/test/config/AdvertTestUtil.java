package ua.test.config;

import org.springframework.test.util.ReflectionTestUtils;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.entity.User;

public class AdvertTestUtil {
	public static final Long ID = 1L;
	public static final Long DATE = 1L;
	public static final Integer VERSION = 1;
	public static final String NAME = "name";
	public static final String REVIEW = "review";
	public static final Topic TOPIC = new Topic();
	public static final User USER = new User();

	public static Advert createFormObject(Long id, String name, Topic topic,
			String review, Long date, Integer version, User user) {
		Advert advert = new Advert();

		advert.setId(id);
		advert.setName(name);
		advert.setDate(date);

		advert.setReview(review);

		advert.setTopic(topic);

		advert.setVersion(version);
		advert.setUser(user);
		return advert;
	}

	public static Advert createModel(Long id, String name, Topic topic,
			String review, Long date, Integer version, User user) {
		Advert model = new Advert();
		model.setName(name);
		model.setDate(date);

		model.setReview(review);

		model.setTopic(topic);

		model.setVersion(version);
		model.setUser(user);
		ReflectionTestUtils.setField(model, "id", id);

		return model;
	}
}
