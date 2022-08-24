package com.example.modules.market.controller;

import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market/want")
public class CommodityWantController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CommodityWantRepository commodityWantRepositoryImpl;

    //点击我想要
    @PutMapping("/addWant/{commodityId}")
    @CrossOrigin
    public R<String> addWantCommodity(@PathVariable("commodityId") String commodityId){
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            commodityWantRepositoryImpl.savelike(userId,commodityId);
            return R.success(null,"点击我想要成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

    //取消我想要
    @PutMapping("/cancelWant/{commodityId}")
    @CrossOrigin
    public R<String> cancelWantCommodity(@PathVariable("commodityId") String commodityId){
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            commodityWantRepositoryImpl.unlike(userId,commodityId);
            return R.success(null,"取消我想要成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

    //是否想要
    @GetMapping("/getWantStu/{commodityId}")
    @CrossOrigin
    public R<Integer> getWantStuLt(@PathVariable("commodityId") String commodityId){
        try{
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            Integer want=commodityWantRepositoryImpl.isLike(userId,commodityId);
            return R.success(want);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    //想要的人数
    @GetMapping("/getWantCount/{commodityId}")
    @CrossOrigin
    public R<Integer> getWantCountLt(@PathVariable("commodityId") String commodityId){
        try{
            Integer count = commodityWantRepositoryImpl.getLikeCount(commodityId);
            return R.success(count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
