package com.example.demo.pojo.converter;

import com.example.demo.pojo.dto.UserDTO;
import com.example.demo.pojo.param.UserParam;
import com.example.demo.sys.entity.User;

/**
 * @author zhuhaitao
 * @date 2019/5/31 15:10
 */
public class UserConverter {
  private UserConverter() {
  }

  public static User userParam2User(UserParam param) {
    return new User()
      .setId(param.getId())
      .setName(param.getName())
      .setAge(param.getAge())
      .setEmail(param.getEmail());
  }

  public static User userDTO2User(UserDTO dto) {
    return new User()
      .setId(dto.getId())
      .setName(dto.getName())
      .setAge(dto.getAge())
      .setEmail(dto.getEmail());
  }

  public static UserDTO user2UserDTO(User user) {
    return new UserDTO()
      .setId(user.getId())
      .setName(user.getName())
      .setAge(user.getAge())
      .setEmail(user.getEmail());
  }
}
