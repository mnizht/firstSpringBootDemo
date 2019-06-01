package com.example.demo.sys.service.impl;

import com.example.demo.pojo.param.UserParam;
import com.example.demo.sys.entity.User;
import com.example.demo.sys.mapper.UserMapper;
import com.example.demo.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuht
 * @since 2019-05-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
