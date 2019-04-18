package com.example.demo.pojo.dto;

import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * @author zhuhaitao
 * @date 2019/1/17 17:25
 */
@Value
@Data
@Accessors(chain = true)
public class NamesOnly2 {
  String firstName,lastName;

  public NamesOnly2(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
