package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityServiceImpl;

    @Autowired
    RedisTemplate redisTemplate;

    //发布商品
    @PostMapping("/issue")
    @CrossOrigin
    public R<CommodityDTO> addCommodity(@RequestBody CommodityDTO commodityDTO){
        try {
            boolean flag = commodityServiceImpl.issueCommodity(commodityDTO);
            if (flag){
                return R.success(commodityDTO,"发布商品成功",200);
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //查询所有商品
    @GetMapping("/getAll")
    @CrossOrigin
    public R<List<CommodityVO>> getAllCommodity(){
        try {
            List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
            return R.success(voList,"查询所有商品成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

}
