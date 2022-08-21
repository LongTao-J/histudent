package com.example.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.StuInfo;
import com.example.modules.user.pojo.User;
import com.example.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapperImpl;

    @Override
    public StuInfo getStuInfo(String userid) {
        StuInfo stuInfo = userMapperImpl.getStuInFoMapper(userid);
        return stuInfo;
    }
}
