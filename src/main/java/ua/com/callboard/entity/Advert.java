package ua.com.callboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "advert", indexes = { @Index(columnList = "name, date"),
		@Index(columnList = "name"), @Index(columnList = "date") })
public class Advert extends AbstractEntity {

	/**
	 * @param date
	 *            Stores time in the Unix system
	 */

	private Long date;

	/**
	 * @param user
	 *            It is the entity instance of class User.
	 *            
	 * The entity instances can be related to a single instance of the other entity
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	/**
	 * @param review
	 *            Max length of review can be 400 character
	 */

	@Column(name = "review", length = 400)
	private String review;

	/**
	 * @param name
	 *            Max length of the ad title can be 30 character
	 */
	
	@Column(name = "name", length = 30)
	private String name;

	/**
	 * @param user
	 *            It is the entity instance of class Topic. 
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Topic topic;
	
	
	/**
	 * Annotation @Transient specifies that the property or field is not persistent.
	 * MultipartFile - a representation of an uploaded file received in a multipart request.
	 * 
	 * @param file
	 * 				The file contents are either stored in memory or temporarily on disk.
	 */
	@Transient
	private MultipartFile file;

	/**
	 * 
	 * @param version
	 * 				So that the browser knew that you changed the picture to a new one 
	 * 				and it had to do it too
	 */
	
	private int version;
	
	/*
    /**********************************************************
    /* Method get() and set()
    /**********************************************************
     */

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
