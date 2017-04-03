package ua.com.callboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.entity.Advert;

public interface AdvertService {

	List<Advert> findAll();

	void delete(Long id);

	void save(Advert advert);

	Advert findOne(Long id);

	Advert findByName(String name);

	List<Advert> findByTopics(Long topicsId);

	List<Advert> findByUsername(String username);

	Page<Advert> findAll(AdvertFilter filter, Pageable pageable);

}
