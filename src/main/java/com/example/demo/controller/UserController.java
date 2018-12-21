package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.repository.JpaUserRepository;
import com.example.demo.utils.ServiceResult;
import com.sun.net.httpserver.Authenticator;
import org.jboss.jandex.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zhuht
 * @date 2018-12-17
 * */

@RestController
@RequestMapping(value = "/user")

public class UserController {
	

	@Autowired
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

	/**
	 * 按实体对象删除数据时，是根据对象的id来删除的；
	 * 当这个对象没有id时，会先把这个对象insert一下，获得一个返回的id，再用这个id去删除*/
	@PostMapping("/delete")
	public ServiceResult deleteUser(@RequestBody User user){
		ServiceResult sr = new ServiceResult();
		sr.setSuccess(true);
		try{
			jpaUserRepository.delete(user);

		}catch(IllegalArgumentException e){
			sr.setSuccess(false);
			sr.setErrorMessage(e.toString());
			sr.setRtnInfo("删除用户 "+user.getUserName()+" 失败!");
		}
		return sr;
	}
	

}
