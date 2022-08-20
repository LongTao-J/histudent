package com.example.modules.market;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.service.CommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class Testma {

    @Autowired
    CommodityService commodityServiceImpl;

    @Autowired
    CommodityMapper commodityMapper;

    @Test
    void test01(){
//        CommodityDTO commodityDTO=new CommodityDTO();
//        commodityDTO.setIntroduce("nnnnnb");
//        commodityDTO.setCount(1);
//        commodityDTO.setPrice(BigDecimal.valueOf(23));
//        commodityDTO.setTitle("1221");
//        commodityServiceImpl.issueCommodity(commodityDTO);

    }

    @Test
    void test02(){
//        List<CommodityVO> allCommodityVo = commodityMapper.getAllCommodityVo();
//        for (CommodityVO commodityVO:allCommodityVo){
//            System.out.println(commodityVO);
//        }
    }
}
