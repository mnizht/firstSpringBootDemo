package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {

	/**
	 * 按用户名模糊查询
	 * @param userName 用户名
	 * @return 用户列表
	 * */
	List<User> findByUserNameLike(String userName);
	
	/**
	 * 根据主键查询
	 * @param id -- 主键
	 * @return 用户
	 * */
	User getUserById(Long id);
	
	/**
	 * 按照用户名称或者备注进行模糊查询
	 * @param userName 用户名
	 * @param note 备注
	 * @return 用户列表
	 * */
	List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
