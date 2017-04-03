package ua.com.callboard.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class AdvertFilter {

	private String nameAdvert = "";

	private String nameUser = "";

	private List<Long> topicId = new ArrayList<>();
	
	private List<Long> userId = new ArrayList<>();
	

	/*
    /**********************************************************
    /* Method get() and set()
    /**********************************************************
     */

	public String getNameAdvert() {
		return nameAdvert;
	}

	public void setNameAdvert(String nameAdvert) {
		this.nameAdvert = nameAdvert;
	}

	public List<Long> getTopicId() {
		return topicId;
	}

	public void setTopicId(List<Long> topicId) {
		this.topicId = topicId;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public List<Long> getUserId() {
		return userId;
	}

	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}
	
}
