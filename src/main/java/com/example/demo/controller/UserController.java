package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.TestParam;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhuht
 * @date 2018-12-17
 */

@RestController
@RequestMapping(value = "/user")

public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/query")
    public User mybatisGetUser(){
        return userService.selectUserByUserName("梵天一");
    }


    @GetMapping("/params")
    public void testParam(@RequestBody TestParam param){
        System.out.println(param);
    }

    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode[] = new String[]{
          "UTF-8",
          "ISO-8859-1",
          "GB2312",
          "GBK",
          "GB18030",
          "Big5",
          "Unicode",
          "ASCII"
        };
        for (int i = 0; i < encode.length; i++){
            try {
                if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {
                    return encode[i];
                }
            } catch (Exception ex) {
            }
        }

        return "";
    }

}
