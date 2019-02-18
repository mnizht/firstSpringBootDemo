package com.example.demo;

import com.example.demo.pojo.NamesOnly2;
import com.example.demo.pojo.User;
import com.example.demo.repository.JpaUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

  @Test
  public void findByMap(){
    Map<String,Date> map = new HashMap<String,Date>();
    map.put("startDate",new Date());

    System.out.println(jpaUserRepository.findByMap(map));
  }

  @Test
  public void findBySexAndStatusIn(){
    List<Integer> list = Arrays.asList();
    System.out.println(jpaUserRepository.findByNoteAndStatusIn("zhuht",list));
    ///sss
  }

  @Test
  public void findByUserNameAndNote(){
    System.out.println(jpaUserRepository.findByUserNameAndNote().size());
  }

  @Test
  public void findByLastNameNot(){
    System.out.println(jpaUserRepository.findByLastNameNot("朱"));
  }

  @Test
  public void findAllByStartDateBeforeAndEndDateAfter(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try {
      Date endDate = sdf.parse("2019-01-18 00:00:00");
      //System.out.println(jpaUserRepository.findAllByStartDateBeforeAndEndDateAfter(new Date(),endDate));
      System.out.println(jpaUserRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(new Date(),endDate));

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void findAllPage(){

    Sort.Direction sortDirection = Sort.Direction.fromString("ASC");
    Pageable pageable = PageRequest.of(0,5,sortDirection,"id");
    Page<User> page = jpaUserRepository.findAllPage(pageable);
    System.out.println("totalPages="+page.getTotalPages()+",TotalElements="+page.getTotalElements()
    +", size="+page.getSize()+", Number="+page.getNumber()+", NumberElements="+page.getNumberOfElements());
    System.out.println(page.getPageable());
    System.out.println(page.getContent());
  }

  @Test
  public void findByLastName(){
//    System.out.println(jpaUserRepository.findByLastName("one"));
    jpaUserRepository.findByLastName("one").stream().forEach(item->{
      System.out.println(item.getFirstName()+item.getLastName());
    });
  }

  @Test
  public void findCollectionByLastName(){
    Collection<User> users = jpaUserRepository.findByLastName("one",User.class);
    Collection<NamesOnly2> names = jpaUserRepository.findByLastName("one",NamesOnly2.class);
    users.stream().forEach(item->{
      System.out.println(item);
    });

    names.stream().forEach(item->{
      System.out.println(item.getFirstName()+item.getLastName());
    });
  }

  @Test
  public void deleteByAge(){
    jpaUserRepository.deleteByAge(30);
  }

  @Test
  public void rtnTest(){
      User user = jpaUserRepository.findFirstByAge(25);
  }
}
