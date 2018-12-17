package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhuht
 * @date 2018-12-17
 * */
@Component
public class SpringBootDemo {

	@Value("${com.springboot.demo.name}")
	private String name;
	@Value("${com.springboot.demo.title}")
	private String title;
	@Value("${com.springboot.demo.referenceUrl}")
	private String referenceUrl;
	@Value("${com.springboot.demo.randomNumber}")
	private int randomNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReferenceUrl() {
		return referenceUrl;
	}
	public void setReferenceUrl(String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	
	
	
	
	
}
