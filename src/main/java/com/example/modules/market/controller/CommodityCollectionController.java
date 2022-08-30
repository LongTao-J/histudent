package com.example.modules.market.controller;

import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.service.CommodityCollectionService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market/collection")
public class CommodityCollectionController {

    @Autowired
    CommodityCollectionService commodityCollectionServiceImpl;

    //点击收藏
    @PutMapping("/addCollection/{commodityId}")
    @CrossOrigin
    public R<String> addCollectionConr(@PathVariable("commodityId") String commodityId){
        try {
            boolean b = commodityCollectionServiceImpl.addCollectionSer(commodityId);
            if (b==true){
                return R.success("收藏成功");
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //取消收藏
    @PutMapping("/cancleCollection/{commodityId}")
    @CrossOrigin
    public R<String> cancleCollectionConr(@PathVariable("commodityId") String commodityId){
        try {
            boolean b = commodityCollectionServiceImpl.cancleCollectionSer(commodityId);
            if (b==true){
                return R.success("取消收藏成功");
            }else {
                return R.error();
            }
        }catch (Exception e){
            return R.error();
        }
    }

    //查看我的收藏
    @GetMapping("/getAll")
    @CrossOrigin
    public R<List<CommodityVO>> getAll(){
        try {
            List<CommodityVO> allSer = commodityCollectionServiceImpl.getAllSer();
            return R.success(allSer,"成功",200);
        }catch (Exception e){
            return R.error();
        }
    }


}
