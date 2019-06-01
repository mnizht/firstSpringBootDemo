package com.example.demo.pojo.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author zhuhaitao
 * @date 2019/5/31 11:20
 */
@Data
@Accessors(chain = true)
public class UserParam {
  private BigInteger id;
  private String name;
  private Integer age;
  private String email;
}
