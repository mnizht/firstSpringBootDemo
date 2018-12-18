package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.converter.SexConverter;
import com.example.demo.enumeration.SexEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author zhuht
 * @date 2018-12-17
 * */

@Entity(name="user")
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(name = "user_name")
	private String userName = null;

	private String note = null;
	
	//定义转换器
	@Convert(converter = SexConverter.class)
	private SexEnum sex = null;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	
	

}
