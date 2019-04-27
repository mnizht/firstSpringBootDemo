package com.example.demo.utils;

import com.example.demo.utils.exception.UserException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuhaitao
 * @date 2019/3/14 14:48
 **/
public class BeanUtils {

  private BeanUtils() {
  }

  // 不序列化转map 当前方法为 获取get Method
  public static Map<String, Object> convertToMap(Object object) {
    Map<String, Object> map = new HashMap<>();
    Class t = object.getClass();
    if (object instanceof Map) {
      throw new UserException("不支持map!");
    }

    try {
      Method[] methodArr = t.getDeclaredMethods();
      for (Method m : methodArr) {
        if (m.getName().indexOf("get") == 0 && Modifier.isPublic(m.getModifiers())) {
          String key = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
          Object value = m.invoke(object);
          map.put(key, value);
        }
      }
    } catch (Exception e) {
      throw new UserException(e.getMessage());
    }
    return map;
  }

}
