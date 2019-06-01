package com.example.demo.sys.controller;


import com.example.demo.pojo.converter.UserConverter;
import com.example.demo.pojo.param.UserParam;
import com.example.demo.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuht
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

  private IUserService userService;

  public UserController(IUserService userService){
    this.userService = userService;
  }

  @PostMapping
  public void addUser(@RequestBody UserParam param){

      userService.save(UserConverter.userParam2User(param));
    System.out.println(userService.saveOrUpdate(UserConverter.userParam2User(param)));
  }



}
