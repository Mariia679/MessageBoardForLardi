package ua.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.entity.Advert;
import ua.com.callboard.entity.Topic;
import ua.com.callboard.entity.User;
import ua.com.callboard.repository.AdvertRepository;
import ua.com.callboard.service.FileWriter;
import ua.com.callboard.service.implementation.AdvertServiceImpl;

public class AdvertServiceTest {

	AdvertRepository advertRepository = mock(AdvertRepository.class);

	AdvertServiceImpl advertServiceImpl;

	AdvertFilter advertFilter;

	Pageable pageable;

	MockMultipartFile file;

	private FileWriter fileWriter;

	@Before
	public void before() {
		Advert advert = new Advert();
		advert.setId(1L);
		fileWriter = mock(FileWriter.class);
		file = new MockMultipartFile("File", "file2.jpg", "img", new byte[] {
				1, 2, 3, 4, 5, 66, 7, 7, 8, 9, 77, 8, 9, 0 });
		advert.setFile(file);
		when(advertRepository.findOne(1L)).thenReturn(advert);
		List<Advert> list = new ArrayList<>();
		list.add(advert);
		list.add(new Advert());
		when(advertRepository.findAll()).thenReturn(list);
		advertServiceImpl = new AdvertServiceImpl(advertRepository);
	}

	@Test
	public void test() {
		Advert advert = new Advert();
		Topic topic = new Topic();
		User user = new User();
		advert.setName("name");
		advert.setDate(1490652905L);
		advert.setReview("name");
		advert.setTopic(topic);
		advert.setVersion(1);
		advert.setUser(user);
		advert.setFile(file);
		advert.getDate();
		advert.getUser();
		advert.getReview();
		advert.getName();
		advert.getTopic();
		advert.getVersion();
		advertServiceImpl.save(advert);
		Assert.assertTrue(true);
	}

	@Test
	public void test1() {
		Advert advert = advertServiceImpl.findByName("name");
		Assert.assertEquals(null, advert);
	}

	@Test
	public void test2() {
		Advert advert = advertServiceImpl.findOne(1L);
		Assert.assertEquals(new Long(1L), advert.getId());
	}

	@Test
	public void test3() {
		Advert advert = advertServiceImpl.findOne(1L);
		advertServiceImpl.delete(advert.getId());
		Assert.assertTrue(true);
	}

	@Test
	public void test4() {
		List<Advert> advert = advertServiceImpl.findAll();
		Assert.assertEquals(2, advert.size());
	}

	@Test
	public void test5() {
		Page<Advert> page = advertServiceImpl.findAll(advertFilter, pageable);
		Assert.assertEquals(null, page);
	}

	@Test
	public void test6() {
		List<Advert> list = advertServiceImpl.findByTopics(1l);
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void test7() {
		List<Advert> list = advertServiceImpl.findByUsername("name");
		Assert.assertEquals(0, list.size());
	}
}
