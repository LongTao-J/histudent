package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.CommodityService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityServiceImpl;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CommodityImageService commodityImageServiceImpl;

    //发布商品
    @PostMapping("/issue")
    @CrossOrigin
    public R<String> addCommodity(@RequestBody CommodityDTO commodityDTO){
        try {
            commodityServiceImpl.issueCommodity(commodityDTO);
            return R.success("发布商品成功");
        }catch (Exception e){
            return R.error();
        }
    }

    //取消发布商品
    @PutMapping("/cancle")
    @CrossOrigin
    public R<String> cancleCommodity(){
        commodityServiceImpl.cancleImg();
        return R.success("取消成功");
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

    //查询我发布的商品
    @GetMapping("/getMyCommodity")
    @CrossOrigin
    public R<List<CommodityVO>> getCommodityByUser(){
        try {
            List<CommodityVO> myCommodityService = commodityServiceImpl.getMyCommodityService();
            return R.success(myCommodityService);
        }catch (Exception e){
            return R.error();
        }
    }

    //查看推荐的商品
    @GetMapping("/getRecCommodity")
    @CrossOrigin
    public R<List<CommodityVO>> getRecCommodity(){
        try {
            List<CommodityVO> recCommodityService = commodityServiceImpl.getRecCommodityService();
            return R.success(recCommodityService);
        }catch (Exception e){
            return R.error();
        }
    }

}
