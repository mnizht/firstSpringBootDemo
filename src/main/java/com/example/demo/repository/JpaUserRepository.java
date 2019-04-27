package com.example.demo.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.pojo.db.User;
import com.example.demo.pojo.dto.NamesOnly;
import com.example.demo.pojo.dto.NamesOnly2;
import com.example.demo.pojo.dto.UserNumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<User, Long> {

	/**
	 * 按用户名模糊查询
	 * @param userName 用户名
	 * @return 用户列表
	 * */
	List<User> findByUserNameLike(String userName);

	List<User> findByUserNameEndingWith(String username);
	List<User> findByUserNameContaining(String username);

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

	List<User> findByNoteAndStatusIn(String note,List<Integer> status);

	@Query(value = "select * from t_user n where if(?3 is null, 1=1 , start_date>?3) and if(?4 is null, 1=1, start_date<?4) " +
			"order by start_date desc limit ?1,?2 ",
			nativeQuery = true)
	List<User> findPageListByStartDate(Integer page, Integer size, Date startDate, Date endDate);

	@Query(value = "select  * from t_user where  if (?1 is null,true,age in ?1)" ,nativeQuery =true)
	List<User> findAllByAgeIn(List<Integer> ages);

	@Query(value = "select user_name,s.status from t_user t left join status s " +
				"on t.status=s.id where t.id=?1",nativeQuery = true)
	List<Map> searchInfo(String userId);

//	@Query(value = "select * from t_user where  start_date>:#{map.get('startDate')}",
//	nativeQuery = true)
//	List<?> findByMap(Map<String,Date> map);

	@Query(value = "select user_name userName,note,sum(age) ages from t_user group by user_name,note",
	nativeQuery = true)
	List<Map<String,Object>> findByUserNameAndNote();

	//查询所选日期在start_date 和 end_date之间的数据
	List<User> findAllByStartDateBeforeAndEndDateAfter(Date date,Date date2);

	List<User> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date date,Date date2);

	//分页
	@Query(value = "select * from t_user",
	countQuery = "select count(*)  from t_user",
	nativeQuery = true)
	Page<User> findAllPage(Pageable pageable);

	//查询属性子集
	Collection<NamesOnly> findByLastName(String lastname);

	<T> Collection<T> findByLastName(String lastname,Class<T> type);

	void deleteByAge(int age);

	User findFirstByAge(int age);

	@Query(value = "select * from t_user where 1=1",
	nativeQuery = true)
	List<User> findByMap(Map<String, Date> map);

	List<User> findByStatusOrderByEndDate(Integer status);

	@Query(value = "select sum(int_num) intNum,sum(double_num) doubleNum,sum(long_num) longNum from t_user"
	,nativeQuery = true)
	UserNumDTO findSumNum();

	@Query("select new com.example.demo.pojo.dto.NamesOnly2(u.firstName,u.lastName) from user u  ")
	List<NamesOnly2> findNameOnly2();

}
