package ua.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.repository.TopicRepository;
import ua.com.callboard.service.implementation.TopicServiceImpl;

public class TopicServiceTest {

	TopicRepository topicRepository = mock(TopicRepository.class);

	TopicServiceImpl topicServiceImpl;

	@Before
	public void before() {
		Topic topic = new Topic();
		topic.setId(1L);
		topic.getAdverts();
		when(topicRepository.findOne(1L)).thenReturn(topic);
		List<Topic> list = new ArrayList<>();
		list.add(topic);
		list.add(new Topic());
		when(topicRepository.findAll()).thenReturn(list);
		topicServiceImpl = new TopicServiceImpl(topicRepository);

	}

	@Test
	public void test1() {
		Topic one = topicServiceImpl.findOne(1L);
		Assert.assertEquals(new Long(1L), one.getId());
	}

	@Test
	public void test2() {
		List<Topic> list = topicServiceImpl.findAll();
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void test4() {
		Topic one = topicServiceImpl.findOne(1L);
		Assert.assertEquals(null, one.getName());
	}

	@Test
	public void findByName() {
		Topic one = topicServiceImpl.findOne("");
		Assert.assertEquals(null, one);
	}

	@Test
	public void test3() {
		Topic topic = new Topic();
		topic.setName("name");
		topicServiceImpl.save(topic);
		Assert.assertTrue(true);
	}

	@Test
	public void delete() {
		Topic topic = topicServiceImpl.findOne(1L);
		topicServiceImpl.delete(topic.getId());
		Assert.assertTrue(true);
	}

	@Test
	public void postConstruct() {
		topicRepository = mock(TopicRepository.class);
		topicServiceImpl = new TopicServiceImpl(topicRepository);
		topicServiceImpl.topicHardcoded();
		Assert.assertTrue(true);
	}

}
