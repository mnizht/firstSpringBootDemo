package com.example.demo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.annotation.HandlesTypes;

import com.example.demo.converter.SexConverter;
import com.example.demo.enumeration.SexEnum;
import com.example.demo.typehandler.SexTypeHandler;
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
@Alias(value = "t_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  private String userName = null;

  private String firstName = null;

  private String lastName = null;

  private Date startDate = null;

  private Date endDate = null;

  private Integer age = null;

  private boolean active;

  private String note = null;

  private Integer status;


  private SexEnum sex = null;



  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "User{" + this.getId() + "," + this.getUserName() + "," + this.getSex() + "," + this.getNote() + "}";
  }

}

