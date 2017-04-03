package ua.test.filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.com.callboard.dto.filter.AdvertFilter;

public class AdvertFilterTest {
	AdvertFilter advert = new AdvertFilter();

	//
	@Before
	public void before() {
		List<Long> topicId = new ArrayList<>();
		List<Long> userId = new ArrayList<>();
		topicId.add(1L);
		userId.add(1L);
		advert.setTopicId(topicId);
		advert.setNameAdvert("name");
		advert.setNameUser("username");
		advert.setUserId(userId);

	}

	@Test
	public void getNameAdvert() {
		String advertFilter = advert.getNameAdvert();
		Assert.assertEquals("name", advertFilter);

	}

	@Test
	public void getNameUser() {
		String username = advert.getNameUser();
		Assert.assertEquals("username", username);

	}
	
	@Test
	public void getTopicId() {
		List<Long> list = advert.getTopicId();
		Assert.assertEquals(1, list.size());

	}

	@Test
	public void getUserId() {
		List<Long> list = advert.getUserId();
		Assert.assertEquals(1, list.size());

	}
}
