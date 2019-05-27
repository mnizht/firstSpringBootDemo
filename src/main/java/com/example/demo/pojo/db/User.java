package com.example.demo.pojo.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author zhuhaitao
 * @date 2019/5/25 16:53
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
  Long id; //数据库自增
  String name;
  Integer age;
  String email;

}
