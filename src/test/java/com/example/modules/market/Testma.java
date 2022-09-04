package com.example.modules.market;

import com.example.modules.countDown.mapper.CountDownMapper;
import com.example.modules.market.mapper.CommodityCollectionMapper;
import com.example.modules.market.mapper.CommodityImageMapper;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.text.ParseException;

@SpringBootTest
public class Testma {

    @Autowired
    CommodityService commodityServiceImpl;

    @Autowired
    RedisLtService redisLtServiceImpl;

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    @Autowired
    CommodityImageMapper commodityImageMapper;

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityCommentService commodityCommentServiceImpl;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void aaa(){
//        List<CommodityVO> allCommodityService = commodityServiceImpl.getAllCommodityService();
//        for (CommodityVO commodityVO:allCommodityService){
//            System.out.println(commodityVO);
//        }
    }

    @Test
    void aaas(){
//        List<String> imgs = redisLtServiceImpl.getCommodityAllImgFromRedis("1552570983563436034");
//        for (String s:imgs){
//            System.out.println(s);
//        }

    }

    @Test
    void as(){
//        List<CommodityVO> commodityVOList=new ArrayList<>();
//        commodityVOList=commodityMapper.getAllCommodityVo();
//
    }

    @Test
    void sksk(){
//        List<CommodityVO> commodityVOList=new ArrayList<>();
//        commodityVOList=commodityMapper.getAllCommodityVo();
//        commodityImageServiceImpl.getAllImgService("4a9372e200c4f288efbf9adaf925f491");
//        for (CommodityVO commodityVO:commodityVOList){
//            List<String> allImg=commodityImageServiceImpl.getAllImgService(commodityVO.getId());
//            System.out.println("++++++++"+allImg.size());
//        }
//        for (int i=0;i<commodityVOList.size();i++){
//            List<String> allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
//            commodityVOList.get(i).setAllImg(allImg);
//            commodityVOList.get(i).setTotalImage(allImg.get(0));
//            System.out.println(allImg.get(0));
//        }
    }

    @Test
    void asadasd(){
//        List<String> allImgService = commodityImageMapper.getAllCommodityImg("753fe041eed0a5ddbb6c4a20676bd32a");
//        System.out.println(allImgService);
    }

    @Test
    void kaksk(){
//        List<CommodityVO> myCommodityService = commodityServiceImpl.getMyCommodityService();
//        System.out.println(myCommodityService);
//        List<String> allImg= commodityImageServiceImpl.getAllImgService("753fe041eed0a5ddbb6c4a20676bd32a");

    }

    @Test
    void ss(){
//        System.out.println(redisTemplate.opsForHash().keys(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED));
    }

    @Autowired
    CommodityWantRepository commodityWantRepositoryImpl;

    @Test
    void assazzxcn(){
//        List<CommodityVO> myWantCommodity = commodityWantRepositoryImpl.getMyWantCommodity("1552570983563436034");
//        for (CommodityVO commodityVO:myWantCommodity){
//            System.out.println(commodityVO);
//        }
    }

    @Autowired
    CommodityCollectionService commodityCollectionServiceImpl;

    @Autowired
    CommodityCollectionMapper commodityCollectionMapperImpl;

    @Test
    void sop(){
//        Integer count = commodityCollectionMapperImpl.getCommodityCountMapper("123131qqweqwe");
//        System.out.println("=======: "+count);

//        Integer qweqwe = commodityCollectionServiceImpl.getCommodityCollectionCount("qweqwe");
//        System.out.println("+++++++++++"+qweqwe);

    }

    @Autowired
    CountDownMapper countDownMapper;

    @Test
    void test09(){
//        QueryWrapper<CountDown> wrapper=new QueryWrapper<>();
//        wrapper.eq("user_id","1552570983563436034");
//        countDownMapper.selectList(wrapper);
//        LambdaQueryWrapper<CountDown> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq("user_id","1552570983563436034");
    }

    @Test
    void test90(){
//        List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
//        System.out.println(recCommodityService);
    }

    @Test
    void test99(){
//        String Ctime="2022/10/30/00:00:00";
//        String nian=Ctime.substring(0,4);
//        String yue=Ctime.substring(5,7);
//        String ri=Ctime.substring(8,10);
//        tys9(nian+"-"+yue+"-"+ri);
    }


    String tys9(String xsy){
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String ris=today.toString();//获取当天时间
        try {
            Date star = dft.parse(ris);//开始时间
            Date endDay=dft.parse(xsy);//结束时间
            Long starTime=star.getTime();
            Long endTime=endDay.getTime();
            Long num=endTime-starTime;//时间戳相差的毫秒数
            num=num/24/60/60/1000;
            return num.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }

//    public String testt(String ni,String yu,String ri){
//        int year= Integer.parseInt(ni),month= Integer.parseInt(yu),day= Integer.parseInt(ri);
//        boolean leap=(year%400==0||(year%4==0&&year%100!=0));
//        int total=(year-1980)+(year-1980+3)/4;
//        for(int i=month-1;i>0;i--){
//            switch (i){
//                case 1: case 3: case 5: case 7: case 8: case 10: case 12: total+=31;break;
//                case 4: case 6: case 9: case 11: total+=30;break;
//                case 2:total+=leap?29:28;break;
//            }
//        }
//        total+=day;
//        int week=1;
//        week=(week+total)%7;
//        String sp="";
//        switch (week){
//            case 1:sp="周一";break;
//            case 2:sp="周二";break;
//            case 3:sp="周三";break;
//            case 4:sp="周四";break;
//            case 5:sp="周五";break;
//            case 6:sp="周六";break;
//            case 0:sp="周日";break;
//        }
//        return sp;
//    }
}
