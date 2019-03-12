package com.example.demo;

import com.example.demo.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author zhuhaitao
 * date 2019/3/7 16:24
 **/
@SpringBootApplication
public class SampleMybatisApplication implements CommandLineRunner {
  private final UserMapper userMapper;

  public SampleMybatisApplication(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public static void main(String[] args){
    SpringApplication.run(SampleMybatisApplication.class,args);
  }


  @Override
  public void run(String... args) throws Exception {
    System.out.println(userMapper.findByUserName("梵天一"));
  }
}
