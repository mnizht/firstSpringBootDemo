package com.example.demo.utils;

import net.sf.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author zhuhaitao
 * date 2019/3/14 14:48
 **/
public class BeanUtils {

  /**
   * Bean to Map*/
  public static <T> Map<String,Object> beanToMap(T bean){
    Map<String,Object> map = new HashMap<>();
    if(bean != null){
      BeanMap beanMap = BeanMap.create(bean);
      for(Object key:beanMap.keySet()){
        map.put(key+"",beanMap.get(key));
      }
    }
    return map;
  }

  /**
   * List<T> to List<Map<String,Object>>*/
  public static <T> List<Map<String,Object>> objectsToMaps(List<T> objList){
    List<Map<String,Object>> list = new ArrayList<>();
    if(objList!=null && objList.size()>0){
      Map<String,Object> map = null;
      T bean = null;
      for(int i=0;i<objList.size();i++){
        bean = objList.get(i);
        map = beanToMap(bean);
        list.add(map);
      }
    }
    return list;
  }

}
