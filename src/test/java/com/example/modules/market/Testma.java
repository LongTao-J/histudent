package com.example.modules.market;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.countDown.entity.po.CountDown;
import com.example.modules.countDown.mapper.CountDownMapper;
import com.example.modules.market.entity.po.CommodityImage;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityCollectionMapper;
import com.example.modules.market.mapper.CommodityImageMapper;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.*;
import com.example.modules.market.utils.CommodityRedisKeyUtil;
import com.example.modules.user.utils.Consts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.management.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
