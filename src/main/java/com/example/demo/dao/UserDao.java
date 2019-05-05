package com.example.demo.dao;

import com.example.demo.enumeration.SexEnum;
import com.example.demo.pojo.db.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;


import java.util.Date;
import java.util.List;

/**
 * author zhuhaitao
 * date 2019/3/21 15:57
 **/
@Mapper
public interface UserDao {
  @Select("select * from t_user where user_name = #{userName}")
  @Results(value = {
    @Result(column = "id",property = "id",jdbcType = JdbcType.BIGINT,javaType = Long.class),
    @Result(column = "user_name",property = "userName",jdbcType = JdbcType.VARCHAR,javaType = String.class),
    @Result(column = "last_name",property = "lastName",jdbcType = JdbcType.VARCHAR,javaType = String.class),
    @Result(column = "first_name",property = "firstName",jdbcType = JdbcType.VARCHAR,javaType = String.class),
    @Result(column = "sex",property = "sex",jdbcType = JdbcType.INTEGER,javaType = SexEnum.class),
    @Result(column = "age",property = "age",jdbcType = JdbcType.INTEGER,javaType = Integer.class),
    @Result(column = "start_date",property = "startDate",jdbcType = JdbcType.DATETIMEOFFSET,javaType = Date.class),
    @Result(column = "end_date",property = "endDate",jdbcType = JdbcType.DATETIMEOFFSET,javaType = Date.class),
    @Result(column = "active",property = "active",jdbcType = JdbcType.INTEGER,javaType = Boolean.class),
    @Result(column = "note",property = "note",jdbcType = JdbcType.VARCHAR,javaType = String.class),
    @Result(column = "status",property = "status",jdbcType = JdbcType.INTEGER,javaType = Integer.class)
  })
  User findUserByUserName(@Param(value = "userName") String userName);

  @Select("select * from t_user")
  List<User> findAllUser();

  @Insert("insert into t_user(user_name,last_name,first_name,sex,age,start_date,end_date,active,note,status) " +
    "values(#{userName},#{lastName},#{firstName},#{sex},#{age},#{startDate},#{endDate},#{active},#{note},#{status})")
  void insertUser(@Param("userName") String userName, @Param("lastName") String lastName, @Param("firstName") String firstName,
                  @Param("sex") SexEnum sex, @Param("age") Integer age, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                  @Param("active") boolean active, @Param("note") String note, @Param("status") Integer status);

  @Update("update t_user set user_name = #{userName},last_name = #{lastName}, first_name = #{firstName},sex = #{sex}," +
    "age = #{age},start_date = #{startDate},end_date = #{endDate},active = #{active},note = #{note}, status = #{status}")
  void updateUser(@Param("userName") String userName, @Param("lastName") String lastName, @Param("firstName") String firstName,
                  @Param("sex") SexEnum sex, @Param("age") Integer age, @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                  @Param("active") boolean active, @Param("note") String note, @Param("status") Integer status);

  @Delete("Delete from t_user where id = #{id}")
  void deleteUser(@Param("id") int id);

  @Update("update t_user set int_num = #{intNum},double_num = #{doubleNum},long_num = #{longNum} where id = #{id}")
  void updateUserNumById(@Param("intNum") int intNum, @Param("doubleNum") double doubleNum, @Param("longNum") long longNum, @Param("id") int id);
}
