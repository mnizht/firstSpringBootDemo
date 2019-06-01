package com.example.demo.sys.mapper;

import com.example.demo.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhuht
 * @since 2019-05-31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
