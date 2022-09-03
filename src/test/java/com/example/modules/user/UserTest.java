package com.example.modules.user;

import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.User;
import com.example.modules.user.pojo.UserInfoLt;
import com.example.modules.user.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userServiceImpl;

    @Autowired
    UserMapper userMapper;

    @Test
    void tes7(){
//        UserInfoLt userInfolt = userServiceImpl.getUserInfolt("1");
//        System.out.println("========= "+userInfolt);
    }

    @Test
    void testas(){
        User user = userMapper.selectById("1552570983563436034");
        user.setClassBackimg("3e3e3ee3");
        userMapper.updateById(user);
    }
}
