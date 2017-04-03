package ua.com.callboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.callboard.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>,
		JpaSpecificationExecutor<Topic> {

	Topic findByName(String name);
	
	List<Topic> findAll();

}
