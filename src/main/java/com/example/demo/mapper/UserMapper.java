package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * author zhuhaitao
 * date 2019/3/7 16:20
 **/
@Mapper
public interface UserMapper {
  @Select("select * from t_user where user_naem = #{userName}")
  User findByUserName(String userName);

}
