package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

/**
 * @author zhuht
 * @date 2018-12-17
 * */

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		System.out.println("This user's id is :" + id);
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=Application/json")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating user " + user.getName());
		userService.createUser(user);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

	}
	
	@GetMapping(value = "/get",headers="Accept=Application/json")
	public List<User> getAllUser(){
		List<User> list = userService.getUser();
		return list;
	}
	
	@PutMapping(value="/update",headers="Accept=Application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser){
		System.out.println("updating user..."+currentUser.getId());
		User user = userService.findById(currentUser.getId());
		if(user == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}else {
			userService.createUser(currentUser);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value="/{id}",headers="Accept=Application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
		User user = userService.findById(id);
		if(user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
