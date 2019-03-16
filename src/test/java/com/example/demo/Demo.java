package com.example.demo;

import com.example.demo.pojo.User;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author zhuhaitao
 * date 2019/3/12 14:11
 **/
public class Demo {
  public static void main(String[] args) {
    //URLTest();
    //moneyToUpper(3000000);
    listTest();

  }
  public static void URLTest(){
    try {
      String s = "张三 妈妈";
      System.out.println(URLEncoder.encode(s));
      System.out.println(com.sun.deploy.net.URLEncoder.encode(s,"utf8"));
      s = s.replace(" ","");
      System.out.println(URLEncoder.encode(s,"UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public static void userTest(){
    User user1 = new User()
      .setUserName("张小凡");
    User user2 = new User()
      .setUserName("林惊羽");
    List<User> list = Arrays.asList(user1, user2);

    Method[] methods = user1.getClass().getDeclaredMethods();
    for(Method method:methods){
      System.out.println(method.getName());
    }
  }

  public static void moneyToUpper(long money){
    System.out.println(MoneyForm.digitUppercase(money));
  }

  public static void listTest(){
    User user1 = new User()
      .setUserName("张小凡");
    User user2 = new User()
      .setUserName("林惊羽");
    User user3 = new User()
      .setUserName("陆雪琪");
    List<User> list = new ArrayList<>();
    list.add(user1);
    list.add(user2);
    list.add(user3);

    for(User user:list){
      System.out.println(user);
    }

  }
}
