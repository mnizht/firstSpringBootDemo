package com.example.demo;

import com.example.demo.repository.JpaUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhuhaitao
 * @date 2019/1/2 9:26
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FirstSpringBootDemoApplication.class)
public class UserControllerTest {

  @Resource
  private JpaUserRepository jpaUserRepository;

//  @Test
//  public void findByMap(){
//    Map<String,Date> map = new HashMap<String,Date>();
//    map.put("startDate",new Date());
//
//    System.out.println(jpaUserRepository.findByMap(map));
//  }

  @Test
  public void findBySexAndStatusIn(){
    List<Integer> list = Arrays.asList();
    System.out.println(jpaUserRepository.findByNoteAndStatusIn("zhuht",list));
    ///sss
  }
}
