package com.example.demo.service.serviceimpl;

import com.example.demo.pojo.db.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public void createUser(User user) {
    // TODO Auto-generated method stub
    userRepository.save(user);
  }

  @Override
  public List<User> getUser() {
    // TODO Auto-generated method stub
    return (List<User>) userRepository.findAll();
  }

  @Override
  public User findById(long id) {
    // TODO Auto-generated method stub
    try {
      return userRepository.findById(id).get();

    } catch (NoSuchElementException e) {
      return null;
    }

  }

  @Override
  public User update(User user, long l) {
    // TODO Auto-generated method stub
    return userRepository.save(user);
  }

  @Override
  public void deleteUserById(long id) {
    // TODO Auto-generated method stub
    userRepository.deleteById(id);
  }

}
