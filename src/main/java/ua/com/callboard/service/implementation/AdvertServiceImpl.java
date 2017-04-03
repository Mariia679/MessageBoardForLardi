package ua.com.callboard.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.callboard.dto.filter.AdvertFilter;
import ua.com.callboard.entity.Advert;
import ua.com.callboard.repository.AdvertRepository;
import ua.com.callboard.service.AdvertService;
import ua.com.callboard.service.FileWriter;
import ua.com.callboard.service.FileWriter.Folder;
import ua.com.callboard.service.specification.AdvertSpecification;

@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	private FileWriter fileWriter;

	@Autowired
	public AdvertServiceImpl(AdvertRepository advertRepository) {
		this.advertRepository = advertRepository;
	}

	@Override
	public List<Advert> findAll() {
		return advertRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		advertRepository.delete(id);
	}

	@Override
	public void save(Advert advert) {
		MultipartFile file = advert.getFile();
		advertRepository.saveAndFlush(advert);
		if (fileWriter.write(Folder.ADVERT, file, advert.getId())) {
			advert.setVersion(advert.getVersion() + 1);
			advertRepository.save(advert);
		}
	}

	@Override
	public Advert findOne(Long id) {
		return advertRepository.findOne(id);
	}

	@Override
	public Advert findByName(String name) {
		return advertRepository.findByName(name);
	}

	@Override
	public List<Advert> findByTopics(Long topicsId) {
		return advertRepository.findByTopics(topicsId);
	}

	@Override
	public List<Advert> findByUsername(String username) {
		return advertRepository.findByUsername(username);
	}
	
	/**
	 * A page is a sublist of a list of objects. It allows gain information
	 * about the position of it in the containing entire list.
	 */
	@Override
	public Page<Advert> findAll(AdvertFilter filter, Pageable pageable) {
		return advertRepository.findAll(new AdvertSpecification(filter),
				pageable);

	}

}
