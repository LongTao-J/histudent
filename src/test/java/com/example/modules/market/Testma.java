package com.example.modules.market;

import com.example.modules.market.entity.po.CommodityImage;
import com.example.modules.market.entity.vo.CommentVo;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityImageMapper;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.service.CommodityCommentService;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.RedisLtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//        List<String> allImgService = commodityImageMapper.getAllCommodityImg("131c0b2e607e7837e655abc95f8a15c2");
    }

    @Test
    void kaksk(){
        List<CommentVo> allCommBycIdSer = commodityCommentServiceImpl.getAllCommBycIdSer("4a9372e200c4f288efbf9adaf925f491");
    }
}
