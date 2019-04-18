package com.example.demo.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * author zhuhaitao
 * date 2019/4/1 11:51
 **/
@Data
@Accessors(chain = true)
public class TestParam {
  private Integer a;
  private Integer b;
  private Double c;
  private Double d;
  private String str1;
  private String str2;
  private Date date1;
  private Date date2;


}
