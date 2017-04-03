package ua.com.callboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.callboard.entity.Advert;

/**
 * JpaRepository - JPA specific extension of Repository.
 * JpaSpecificationExecutor - Interface to allow execution of Specifications
 * based on the JPA criteria API.
 */
public interface AdvertRepository extends JpaRepository<Advert, Long>,
		JpaSpecificationExecutor<Advert> {

	@Query("SELECT a FROM Advert a LEFT JOIN FETCH a.user "
			+ "LEFT JOIN FETCH a.topic WHERE a.id=?1")
	Advert findOne(Long id);

	@Query("SELECT a FROM Advert a LEFT JOIN FETCH a.user "
			+ "LEFT JOIN FETCH a.topic")
	List<Advert> findAll();

	@Query("SELECT a FROM Advert a LEFT JOIN FETCH a.user "
			+ "LEFT JOIN FETCH a.topic WHERE a.name=?1")
	Advert findByName(String name);

	@Query("SELECT a FROM Advert a LEFT JOIN FETCH a.user "
			+ "LEFT JOIN FETCH a.topic WHERE a.topic.id=?1")
	List<Advert> findByTopics(Long topicsId);

	@Query("SELECT a FROM Advert a LEFT JOIN FETCH a.user "
			+ "LEFT JOIN FETCH a.topic WHERE a.user.username=?1")
	List<Advert> findByUsername(String username);

}
