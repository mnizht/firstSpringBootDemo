package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.pojo.User;
import com.example.demo.repository.JpaUserRepository;
import com.example.demo.service.UserService;

/**
 * @author zhuht
 * @date 2018-12-17
 * */

@RestController
@RequestMapping(value = "/user")

public class UserController {
	


	@Autowired
	//UserService userService;
	private JpaUserRepository jpaUserRepository = null;
	
	@RequestMapping("/getUserById")
	@ResponseBody
	public User getUserById(Long id) {
		User user = jpaUserRepository.getUserById(id);
		return user;
	}
	
	@RequestMapping("/findByUserNameLike")
	@ResponseBody
	public List<User> findByUserNameLike(String userName) {
		List<User> userList = jpaUserRepository.findByUserNameLike(userName);
		return userList;
	}
	
	@RequestMapping("/findByLastNameAndFirstName")
	@ResponseBody
	public List<User> findByLastNameAndFirstName(String lastName,String firstName) {
		List<User> userList = jpaUserRepository.findByLastNameAndFirstName(lastName,firstName);
		return userList;
	}
	
	@RequestMapping("/findByUserNameLikeOrNoteLike")
	@ResponseBody
	public List<User> findByUserNameLikeOrNoteLike(String userName, String note) {
		String userNameLike = "%" + userName + "%";
		String noteLike = "%" + note + "%";
		List<User> userList = jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike,noteLike);
		return userList;
	}
	
	@RequestMapping("/findByStartDateBetween")
	@ResponseBody
	public List<User> findByStartDateBetween(@RequestParam("beginDate") Date beginDate,@RequestParam("endDate") Date endDate){
		

		
		List<User> userList = jpaUserRepository.findByStartDateBetween(beginDate, endDate);
		return userList;
	}
	
	//解决传递日期字符串时无法自动转换成日期类型的情况
	@InitBinder
	public void initBinder(WebDataBinder binder,WebRequest request) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,true));
	}
	
	@RequestMapping("/findByAgeLessThan")
	@ResponseBody
	public List<User> findByAgeLessThan(@RequestParam("age")Integer age){
		return jpaUserRepository.findByAgeLessThan(age);
	}
	
	@RequestMapping("/findByAgeIn")
	@ResponseBody
	public List<User> findByAgeIn(@RequestParam("ages")Collection<Integer> ages){
		return jpaUserRepository.findByAgeIn(ages);
	}
	
	@RequestMapping("/findByActiveTrue")
	@ResponseBody
	public List<User> findByActiveTrue(){
		return jpaUserRepository.findByActiveTrue();
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<User> findAll(){
		return jpaUserRepository.findAll();
	}
	

	
	@RequestMapping("find")
	public List<User> findUser(HttpServletRequest request){
		
		String method = request.getParameter("method")==null?"":request.getParameter("method");
		
		switch(method) {
		case "findByAgeLessThan":return jpaUserRepository.findByAgeLessThan(Integer.valueOf(request.getParameter("age")));
		case "findByFirstName":return jpaUserRepository.findByFirstName(request.getParameter("firstName"));
		//....
		
		default:return null;
		}
	}
	
	@PostMapping(value = "/create" )
	public void createUser(@RequestBody User user ) {
		//返回插入的实例，包括自动生成的id
		User u = jpaUserRepository.save(user);
		if(u==null) {
			System.out.println("create failed");
		}
		System.out.println("create success "+u.getId());
	}
	
	@PostMapping(value = "/update" )
	public void updateUser(@RequestBody User user ) {
		//返回插入的实例，包括自动生成的id
		User u = jpaUserRepository.save(user);
		if(u==null) {
			System.out.println("update failed");
		}
		System.out.println("update success "+u);
	}
	
	/***
	 * 改由jpa实现
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
	
	***/
}
