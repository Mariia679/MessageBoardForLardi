package ua.com.callboard.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.callboard.entity.Topic;
import ua.com.callboard.repository.TopicRepository;
import ua.com.callboard.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	private TopicRepository topicRepository;

	@Autowired
	public TopicServiceImpl(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	/**
	 * The PostConstruct annotation is used on a method that needs to be
	 * executed after dependency injection is done to perform any
	 * initialization.
	 * 
	 * To add predefined rubrics
	 */
	@PostConstruct
	public void topicHardcoded() {
		List<Topic> topics = topicRepository.findAll();
		if (topics.isEmpty()) {
			Topic topic = new Topic();
			topic.setName("продажа");
			topicRepository.save(topic);

			Topic topic1 = new Topic();
			topic1.setName("покупка");
			topicRepository.save(topic1);

			Topic topic2 = new Topic();
			topic2.setName("аренда");
			topicRepository.save(topic2);

			Topic topic3 = new Topic();
			topic3.setName("услуги");
			topicRepository.save(topic3);

			Topic topic4 = new Topic();
			topic4.setName("знакомства");
			topicRepository.save(topic4);

		}
	}

	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		topicRepository.delete(id);
	}

	@Override
	public void save(Topic topic) {
		topicRepository.save(topic);

	}

	@Override
	public Topic findOne(Long id) {
		return topicRepository.findOne(id);
	}

	@Override
	public Topic findOne(String name) {
		return topicRepository.findByName(name);
	}

}
