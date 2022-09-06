package com.example.modules.user;

import com.example.modules.market.service.CommodityService;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userServiceImpl;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommodityService commodityServiceImpl;

    @Test
    void tes7(){
//        UserInfoLt userInfolt = userServiceImpl.getUserInfolt("1");
//        System.out.println("========= "+userInfolt);
    }

    @Test
    void testas(){
//        List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
    }
}
