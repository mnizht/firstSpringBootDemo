package com.example.demo.service;

import com.example.demo.pojo.db.User;

import java.util.List;


public interface UserService {

  void createUser(User user);

  List<User> getUser();

  User findById(long id);

  User update(User user, long l);

  void deleteUserById(long id);
}
