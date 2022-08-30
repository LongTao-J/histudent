package com.example.modules.user;

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

    @Test
    void tes7(){
//        UserInfoLt userInfolt = userServiceImpl.getUserInfolt("1");
//        System.out.println("========= "+userInfolt);
    }
}
