package com.example.demo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.converter.SexConverter;
import com.example.demo.enumeration.SexEnum;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@Entity(name = "user")
@Table(name = "t_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @Column(name = "user_name")
  private String userName = null;

  @Column(name = "first_name")
  private String firstName = null;

  @Column(name = "last_name")
  private String lastName = null;

  @Column(name = "start_date")
  private Date startDate = null;

  @Column(name = "age")
  private Integer age = null;

  @Column(name = "active")
  private boolean active;

  @Column(name = "note")
  private String note = null;

  @Column(name = "status")
  private Integer status;


  //定义转换器
  @Convert(converter = SexConverter.class)
  private SexEnum sex = null;



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


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "User{" + this.getId() + "," + this.getUserName() + "," + this.getSex() + "," + this.getNote() + "}";
  }


}
