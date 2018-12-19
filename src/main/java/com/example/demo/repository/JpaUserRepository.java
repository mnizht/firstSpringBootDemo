package com.example.demo.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Example;
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
	
	//...where x.lastname = ?1 and x.firstname = ?2
	List<User> findByLastNameAndFirstName(String lastName,String firstName);
	//...where x.lastname = ?1 or x.firstname = ?2
	List<User> findByLastNameOrFirstName(String lastName,String firstName);
	
	/**
	 * 以下三种方法等价*/
	//… where x.firstname = 1?
	List<User> findByFirstName(String firstName);
	List<User> findByFirstNameIs(String firstName);
	List<User> findByFirstNameEquals(String firstName);
	//… where x.startDate between 1? and ?2
	List<User> findByStartDateBetween(Date beginDate,Date endDate);
	//… where x.age < ?1
	List<User> findByAgeLessThan(Integer age);
	//… where x.age ⇐ ?1
	List<User> findByAgeLessThanEqual(Integer age);
	//… where x.age > ?1
	List<User> findByAgeGreaterThan(Integer age);
	//… where x.age >= ?1
	List<User> findByAgeGreaterThanEqual(Integer age);

	//… where x.startDate > ?1
	List<User> findByStartDateAfter(Date startDate);
	//… where x.startDate < ?1
	List<User> findByStartDateBefore(Date startDate);
	//… where x.age is null
	List<User> findByAgeIsNull();
	//… where x.age not null
	//findByAge(Is)NotNull  其中is可省略
	List<User> findByAgeNotNull();
	//… where x.firstname like ?1
	List<User> findByFirstNameLike(String firstName);
	//… where x.firstname not like ?1
	List<User> findByFirstNameNotLike(String firstName);
	//… where x.firstname like ?1 (parameter bound with appended %)
	List<User> findByFirstNameStartingWith(String firstName);
	//… where x.firstname like ?1 (parameter bound with prepended %)
	List<User> findByFirstNameEndingWith(String firstName);
	//… where x.firstname like ?1 (parameter bound wrapped in %)
	List<User> findByFirstNameContaining(String firstName);
	//… where x.age = ?1 order by x.lastname desc
	List<User> findByAgeOrderByLastNameDesc(Integer age);
	//… where x.lastname <> ?1
	List<User> findByLastNameNot(String lastName);
	//… where x.age in ?1
	List<User> findByAgeIn(Collection<Integer> ages);
	//… where x.age not in ?1
	List<User> findByAgeNotIn(Collection<Integer> ages);
	//… where x.active = true
	List<User> findByActiveTrue();
	//… where x.active = false
	List<User> findByActiveFalse();
	//… where UPPER(x.firstame) = UPPER(?1)
	List<User> findByFirstNameIgnoreCase(String firstName);
	
	
	
}
