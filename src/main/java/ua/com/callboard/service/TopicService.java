package ua.com.callboard.service;

import java.util.List;

import ua.com.callboard.entity.Topic;

public interface TopicService {

	List<Topic> findAll();

	void delete(Long id);

	void save(Topic topic);

	Topic findOne(Long id);

	Topic findOne(String name);

}
