package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.repository.CommodityImgRepository;
import com.example.modules.market.service.CommodityImageService;
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

    @Autowired
    CommodityImgRepository commodityImgRepositoryImpl;

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    //发布商品
    @PostMapping("/issue")
    @CrossOrigin
    public R<String> addCommodity(@RequestBody CommodityDTO commodityDTO){
        try {

            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);

            Commodity commodity=new Commodity();
            commodity.setCount(commodityDTO.getCount());
            commodity.setTitle(commodityDTO.getTitle());
            commodity.setPrice(commodityDTO.getPrice());
            commodity.setIntroduce(commodityDTO.getIntroduce());
            commodity.setUserId(userId);

            commodityImgRepositoryImpl.issuePost(commodity);
            return R.success("发布商品成功");
        }catch (Exception e){
            return R.error();
        }
    }

    //取消发布商品
    @PutMapping("/cancle")
    @CrossOrigin
    public R<String> cancleCommodity(){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);
        commodityImgRepositoryImpl.unissuePost(userId);
        return R.success("取消成功");
    }

    //查询所有商品
    @GetMapping("/getAll")
    @CrossOrigin
    public R<List<CommodityVO>> getAllCommodity(){
        try {
            List<CommodityVO> voList=commodityServiceImpl.getAllCommodityService();
            for (int i=0;i<voList.size();i++){
                List<String> allImg= commodityImageServiceImpl.getAllImgService(voList.get(i).getId());
                voList.get(i).setAllImg(allImg);
                voList.get(i).setTotalImage(allImg.get(0));
            }
            return R.success(voList,"查询所有商品成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

}
