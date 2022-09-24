package com.example.modules.user;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.dto.Smss;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.websocket.entity.MegUser;
import com.example.modules.websocket.entity.MesssageWs;
import com.example.utils.R;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.python.modules._json._json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SpringBootTest
public class UserTest {

//    @Autowired
//    UserService userServiceImpl;
//
//    @Autowired
//    UserMapper userMapper;
//
//    @Autowired
//    CommodityService commodityServiceImpl;
//
//    @Test
//    void tes7(){
////        UserInfoLt userInfolt = userServiceImpl.getUserInfolt("1");
////        System.out.println("========= "+userInfolt);
//    }
//
//    @Test
//    void testas(){
////        List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
//    }
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userServiceImpl;
//    @Test
//    void ppp(){
//
//        Set keys = redisTemplate.opsForHash().keys("huccct");
//        List<MegUser> megUserList=new ArrayList<>();
//        for (Object s:keys){
//            String x= (String) s;
//            MegUser megUser=new MegUser();
//            megUser.setUsername(x);
//            megUser.setHeadImg(userServiceImpl.getImgByUserName(x));
//
//            List<String> list= (List<String>) redisTemplate.opsForHash().get("huccct",x);
//            JSONObject jsonObject=JSONUtil.parseObj(list.get(0));
//            megUser.setText(jsonObject.getStr("text"));
//
//            megUserList.add(megUser);
//        }
//        System.out.println(megUserList);
//    }
    @Test
    void sda74ry(){
//        redisTemplate.opsForHash().delete("huccct","xiaoyus");
//        List<String> o = (List<String>) redisTemplate.opsForHash().get("huccct", "xiaoyus");
//        redisTemplate.opsForHash().delete("xiaoyus","huccct");
//        List<String> o = (List<String>) redisTemplate.opsForHash().get("xiaoyus", "huccct");
//        System.out.println(o);
    }

}
