package com.example.modules.user;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityWantMapper;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.CommodityWantService;
import com.example.modules.notice.entity.po.Notice;
import com.example.modules.notice.mapper.NoticeMapper;
import com.example.modules.notice.service.NoticeService;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.pojo.dto.Smss;
import com.example.modules.user.pojo.dto.UserInfoLt;
import com.example.modules.user.pojo.po.StuInfo;
import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Anquan.LoginUser;
import com.example.modules.user.utils.Anquan.RedisCache;
import com.example.modules.user.utils.Consts;
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
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class UserTest {

//    @Autowired
//    UserService userServiceImpl;
//
    @Autowired
    UserMapper userMapperImpl;
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
    NoticeMapper noticeMapperImpl;
    @Autowired
    UserService userServiceImpl;
    @Autowired
    RedisCache redisCache;
    @Autowired
    NoticeService noticeServiceImpl;
    @Autowired
    CommodityWantMapper commodityWantMapperImpl;
    @Autowired
    CommodityWantService commodityWantServiceImpl;
    @Autowired
    CommodityService commodityServiceImpl;

//    @Test
//    void asda787asd8(){
//        List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
//        System.out.println(voList);
//    }

//    @Test
//    void asd5asd4a(){
////        List<String> list= (List<String>) redisTemplate.opsForHash().get("huccctttt","Hi!同学");
////        List<String> list= (List<String>) redisTemplate.opsForHash().get("1552570983563436034","132313");
//        Set keys = redisTemplate.opsForHash().keys("1552570983563436034");
//        System.out.println(keys);
//
//    }

//    @Test
//    void asdm9(){
//        String userKey=Consts.LOGIN_USERS+"17516050906";
////        String userKey=Consts.LOGIN_USERS+"lt";
////        redisTemplate.delete(userKey1);
//        redisTemplate.delete(userKey);
//    }
//
//    @Test
//    void ASDskdsfjo(){
//        List<String> listX= (List<String>) redisTemplate.opsForHash().get("龙涛","huccct");
//        System.out.println(listX);
    }
//    @Test
//    void asopop(){
//        CommodityWant likeDataFromMySql=commodityWantServiceImpl.getWantByUserIdAndCommodityId("132313","7996511584ea9ba22493871b86870b6b");
//        System.out.println(likeDataFromMySql);
//    }

//    @Test
//    void asdpo0(){
//        QueryWrapper<CommodityWant> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", "132313");
//        wrapper.eq("commodity_id", "7996511584ea9ba22493871b86870b6b");
//        CommodityWant commodityWant = commodityWantMapperImpl.selectOne(wrapper);
//        System.out.println(commodityWant);
//    }

//    @Test
//    void asd5a5sd5(){
//        List<Notice> all = noticeServiceImpl.getAll();
//        System.out.println(all);
//    }


//    @Test
//    void asd78(){
//        LambdaQueryWrapper<Notice> queryWrapper=new LambdaQueryWrapper<>();
//        queryWrapper.eq(Notice::getName,"Hi!同学");
//        List<Notice> notices = noticeMapperImpl.selectList(queryWrapper);
//    }

//    @Test
//    void SADas(){
//        ValueOperations operations=redisTemplate.opsForValue();
//        operations.set("qwq","444444444",9,TimeUnit.MINUTES);
//        Object o = operations.get("qwq");
//        System.out.println(o.toString());

//        redisTemplate.delete(Consts.LOGIN_USERS+"18876521895");
//        Object o = operations.get("qwq");
//        if (o==null){
//            System.out.println("saaaaasa");
//        }
//    }





//    @Test
//    void ashjkbm(){
//        redisTemplate.opsForHash().delete(Consts.LOGIN_USERS,"18110359126");
//    }

//    @Test
//    void as90(){
//        StuInfo stuInfo = userServiceImpl.getStuInfo("1552570983563436034");
//        System.out.println(stuInfo);
//    }

//    @Test
//    public void ASasa(){
//        redisTemplate.opsForHash().delete(Consts.LOGIN_USERS,"lt");
//    }
//    @Test
//    public void sada(){
//        ValueOperations<String,String> redis = redisTemplate.opsForValue();
//        String smslocal=redis.get("18876521895");
//        System.out.println(smslocal);
//    }
//    @Test
//    void dsa09ann(){
//
//    }

//    @Test
//    public void as9jd(){
//        redisTemplate.opsForValue().set("lt","66666666666",100,TimeUnit.SECONDS);
//        String o = (String) redisTemplate.opsForValue().get("lt");
//        System.out.println(o);
//    }

//    @Test
//    void ao98s(){
//        List<String> o = (List<String>) redisTemplate.opsForHash().get("xiaoyus", "huccct");
//        System.out.println(o);
//    }
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
//    @Test
//    void sda74ry(){
//        redisTemplate.opsForHash().delete("xiaoyus","huccct");
//        Set huccct = redisTemplate.opsForHash().keys("xiaoyus");
//        System.out.println(huccct);
//    }


