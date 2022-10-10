package com.example.modules.user;


import com.alibaba.fastjson2.JSONObject;

import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityWantMapper;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.CommodityWantService;
import com.example.modules.notice.mapper.NoticeMapper;
import com.example.modules.notice.service.NoticeService;
import com.example.modules.user.mapper.UserMapper;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Anquan.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapperImpl;
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
//    void lonng(){
//        List<CommodityVO> list = (List<CommodityVO>) redisTemplate.opsForValue().get("market");
//    }

//    @Test
//    void longtao01(){
//        List<CommodityVO> list = commodityServiceImpl.getRecCommodityService();
//        System.out.println("commodityServiceImpl :"+list);
//        redisTemplate.opsForValue().set("market",list);
//        List<CommodityVO> o = (List<CommodityVO>) redisTemplate.opsForValue().get("market");
//        System.out.println("redisTemplate :"+o);
//    }

//    @Test
//    void lsdmlafj(){
////        List<CommodityVO> list = commodityServiceImpl.getRecCommodityService();
////        redisTemplate.opsForValue().set("recmarket",list);
//        List<CommodityVO> recmarket = (List<CommodityVO>) redisTemplate.opsForValue().get("recmarket");
//        for (int i=0;i<recmarket.size();i++){
////            Date gmtCreate = recmarket.get(i).getGmtCreate();
////            System.out.println(gmtCreate.getTime());
//            System.out.println(recmarket.get(i).getGmtCreate());
//        }
//    }

//    @Test
//    void asda787asd8(){
////        List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
//////        System.out.println(recCommodityService);
////        redisTemplate.opsForValue().set("recmarket",recCommodityService);
////        List<CommodityVO> o = (List<CommodityVO>) redisTemplate.opsForValue().get("recmarket");
////
////        System.out.println(o);
//////        redisTemplate.delete("recmarket");
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


