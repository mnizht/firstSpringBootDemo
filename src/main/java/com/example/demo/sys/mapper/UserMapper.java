package com.example.demo.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.TempDTO;
import com.example.demo.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhuht
 * @since 2019-05-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

  List<TempDTO> selectSumAge();
}
