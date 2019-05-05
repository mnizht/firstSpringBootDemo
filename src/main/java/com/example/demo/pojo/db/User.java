package com.example.demo.pojo.db;

import com.example.demo.enumeration.SexEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@Data
@Accessors(chain = true)
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
    return "User{" + this.getId() + "," + this.getUserName() + "," + this.getSex() + "," + this.getNote() + "}";
  }

}

