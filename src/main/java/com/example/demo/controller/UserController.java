package com.example.demo.controller;

import com.example.demo.pojo.db.User;
import com.example.demo.pojo.dto.TestParam;
import com.example.demo.pojo.dto.UserParam;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@RestController
@RequestMapping(value = "/user")

public class UserController {
  private final UserService userService;

  @PostMapping
  public void userSave(@RequestBody UserParam param){
    userService.save(param);
  }

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @RequestMapping("/query")
  public User mybatisGetUser(@RequestParam String username) {
    return userService.selectUserByUserName(username);
  }


  @GetMapping("/params")
  public void testParam(@RequestBody TestParam param) {
    System.out.println(param);
  }

  /**
   * 判断字符串的编码
   */
  public static String getEncoding(String str) {
    String[] encode = new String[]{
      "UTF-8",
      "ISO-8859-1",
      "GB2312",
      "GBK",
      "GB18030",
      "Big5",
      "Unicode",
      "ASCII"
    };
    for (String s : encode) {
      try {
        if (str.equals(new String(str.getBytes(s), s))) {
          return s;
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    return "";
  }

}
