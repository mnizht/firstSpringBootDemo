package com.example.demo.repository;

import com.example.demo.pojo.db.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {

}
