package mybatis;

import com.example.demo.FirstSpringBootDemoApplication;
import com.example.demo.pojo.dto.TempDTO;
import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhuht
 * @date 2022/3/10 9:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FirstSpringBootDemoApplication.class)
public class MybatisTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testSelect() {
    List<User> users = userMapper.selectList(null);
//    users.forEach(System.out::println);
    List<TempDTO> tempDTOS = userMapper.selectSumAge();
    tempDTOS.forEach(System.out::println);
    if (CollectionUtils.isNotEmpty(tempDTOS)) {
      // 由于查询的SQL最后没有group by，当where 条件后没有数据时，会返回一条记录 null，导致list.size() = 1,
//      get(0) 后使用时会报NPE
      System.out.println(tempDTOS.get(0).getName());
    }

  }
}
