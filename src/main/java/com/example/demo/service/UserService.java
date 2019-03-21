package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.enumeration.SexEnum;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * author zhuhaitao
 * date 2019/3/21 16:17
 **/
@Service
public class UserService {

  @Resource
  private UserDao userDao;

  public User selectUserByUserName(String userName) {
    return userDao.findUserByUserName(userName);
  }

  public List<User> findAllUser() {
    return userDao.findAllUser();
  }

  public void insertUser() {
    Date startDate = new Date();
    Date endDate = new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 7));
    userDao.insertUser("张无忌", "张", "无忌", SexEnum.MALE, 25, startDate, endDate, true, "张教主解围光明顶", 1);
  }

  public void updateUser() {
    Date startDate = new Date();
    Date endDate = new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 7));
    userDao.updateUser("张无忌", "张", "无忌", SexEnum.MALE, 25, startDate, endDate, true, "张教主解围光明顶", 1);
  }

  public void deleteUserById(int id) {
    userDao.deleteUser(id);
  }
  @Transactional
  public void updateUserNum(int intNum, double doubleNum, long longNum, int id) {
    userDao.updateUserNumById(intNum, doubleNum, longNum, id);
  }
}
