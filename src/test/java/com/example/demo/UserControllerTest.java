package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhuhaitao
 * @date 2019/1/2 9:26
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FirstSpringBootDemoApplication.class)
public class UserControllerTest {

  @Resource
  private UserService userService;
  @Test
  public void insertUser(){
    userService.insertUser();
  }
}
