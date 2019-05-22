package com.example.demo.pojo.dto;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author zhuhaitao
 * @date 2019/5/22 15:18
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserParam {
  @NotNull
  String userName;
  String firstName;
  String lastName;
  Date startDate;
  Date endDate;
  Integer age;
  @Value(value = "false")
  Boolean active;
  String note;
  @Value("1")
  Integer status;
  Integer sex;
}
