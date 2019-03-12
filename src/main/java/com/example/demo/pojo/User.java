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
import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@Data
@Accessors(chain=true)
@Entity
@Alias(value = "user")
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

  @Column(name = "end_date")
  private Date endDate = null;

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



  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "User{" + this.getId() + "," + this.getUserName() + "," + this.getSex() + "," + this.getNote() + "}";
  }

  @Value
  class NamesOnly2 {
    String firstname,lastname;
  }
}

