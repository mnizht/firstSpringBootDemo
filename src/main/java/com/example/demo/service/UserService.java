package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojo.User;

public interface UserService {

	public void createUser(User user);

	public List<User> getUser();

	public User findById(long id);
	
	public User update(User user, long l);

	public void deleteUserById(long id);
}
