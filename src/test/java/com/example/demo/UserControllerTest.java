package com.example.demo;

import com.example.demo.pojo.db.User;
import com.example.demo.pojo.dto.NamesOnly;
import com.example.demo.pojo.dto.NamesOnly2;
import com.example.demo.pojo.dto.UserNumDTO;
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
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhuhaitao
 * @date 2019/1/2 9:26
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstSpringBootDemoApplication.class)
public class UserControllerTest {

  @Resource
  private JpaUserRepository jpaUserRepository;

  @Test
  public void findByMap() {
    Map<String, Date> map = new HashMap<>();
    map.put("startDate", new Date());

    System.out.println(jpaUserRepository.findByMap(map));
  }

  @Test
  public void findBySexAndStatusIn() {
    List<Integer> list = new ArrayList<>();
    System.out.println(jpaUserRepository.findByNoteAndStatusIn("zhuht", list));
    ///sss
  }

  @Test
  public void findByUserNameAndNote() {
    System.out.println(jpaUserRepository.findByUserNameAndNote().size());
  }

  @Test
  public void findByLastNameNot() {
    System.out.println(jpaUserRepository.findByLastNameNot("朱"));
  }

  @Test
  public void findAllByStartDateBeforeAndEndDateAfter() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try {
      Date endDate = sdf.parse("2019-01-18 00:00:00");
      //System.out.println(jpaUserRepository.findAllByStartDateBeforeAndEndDateAfter(new Date(),endDate));
      System.out.println(jpaUserRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(new Date(), endDate));

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void findAllPage() {

    Sort.Direction sortDirection = Sort.Direction.fromString("ASC");
    Pageable pageable = PageRequest.of(0, 5, sortDirection, "id");
    Page<User> page = jpaUserRepository.findAllPage(pageable);
    System.out.println("totalPages=" + page.getTotalPages() + ",TotalElements=" + page.getTotalElements()
      + ", size=" + page.getSize() + ", Number=" + page.getNumber() + ", NumberElements=" + page.getNumberOfElements());
    System.out.println(page.getPageable());
    System.out.println(page.getContent());
  }

  @Test
  public void findByLastName() {
//    System.out.println(jpaUserRepository.findByLastName("one"));
    for (NamesOnly item : jpaUserRepository.findByLastName("one")) {
      System.out.println(item.getFirstName() + item.getLastName());
    }
  }

  @Test
  public void findCollectionByLastName() {
    Collection<User> users = jpaUserRepository.findByLastName("one", User.class);
    Collection<NamesOnly2> names = jpaUserRepository.findByLastName("one", NamesOnly2.class);
    users.forEach(System.out::println);

    names.forEach(item -> System.out.println(item.getFirstName() + item.getLastName()));
  }

  @Test
  public void deleteByAge() {
    jpaUserRepository.deleteByAge(30);
  }

  @Test
  public void rtnTest() {
    User user = jpaUserRepository.findFirstByAge(25);
    System.out.println(user);
  }

  @Test
  public void userSaveTest() {
    List<User> list = jpaUserRepository.findAllByAgeIn(Arrays.asList(23, 24));
    System.out.println(list);
    List<User> update = list.stream().filter(item -> {
      item.setUserName(item.getUserName() + 2);
      return item.getAge() == 24;
    }).collect(Collectors.toList());
    jpaUserRepository.saveAll(update);
  }

  @Test
  public void beanMapTest() {
//    List<User> list = jpaUserRepository.findAll();
//    System.out.println(list);
//    System.out.println(BeanUtils.objectsToMaps(list));

    List<NamesOnly> list = new ArrayList<>(jpaUserRepository.findByLastName("毛"));
    System.out.println(list);

    Method[] methods = list.get(0).getClass().getDeclaredMethods();
    for (Method method : methods) {
      System.out.println(method.getName());
    }
  }

  @Test
  public void jpaOrderByTest() {
    List<User> list = jpaUserRepository.findByStatusOrderByEndDate(1);
    for (User user : list) {
      System.out.println(user);
    }
  }

  @Test
  public void NumTest() {
    UserNumDTO dto = jpaUserRepository.findSumNum();
    System.out.println(dto.getIntNum());
    System.out.println(dto.getDoubleNum());
    System.out.println(dto.getLongNum());
  }

  @Test
  public void nameOnly2Test() {
    List<NamesOnly2> nameOnly2 = jpaUserRepository.findNameOnly2();
    System.out.println(nameOnly2);
  }


  @Test
  public void likeTest() {
    List<User> user = jpaUserRepository.findByUserNameLike("天");
    List<User> user2 = jpaUserRepository.findByUserNameLike("%天%");
    List<User> user3 = jpaUserRepository.findByUserNameEndingWith("一");
    List<User> user4 = jpaUserRepository.findByUserNameContaining("羽");

    System.out.println(user);
    System.out.println(user2);
    System.out.println(user3);
    System.out.println(user4);
  }
}
