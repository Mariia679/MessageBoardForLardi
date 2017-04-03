package ua.com.callboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "topic", indexes = @Index(columnList = "_name"))
public class Topic extends AbstractEntity {

	/**
	 * @param name
	 *            Contains name topic
	 */
	@Column(name = "_name")
	private String name;
	
	/**
	 * @param adverts
	 *            contains a collection of ads An entity instance can be related
	 *            to multiple instances of the other entities. MappedBy says
	 *            that this party is not responsible for the connection
	 */
	@OneToMany(mappedBy = "topic")
	private List<Advert> adverts = new ArrayList<>();

	/*
	/********************************************************** 
	/* Method get() and set()
	/**********************************************************
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<Advert> adverts) {
		this.adverts = adverts;
	}

}
