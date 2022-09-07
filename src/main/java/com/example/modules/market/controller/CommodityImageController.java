package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.ImageDTO;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.RedisLtService;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market/image")
public class CommodityImageController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisLtService redisLtServiceImpl;

    @Autowired
    UserService userServiceImpl;

    //上传商品的照片
    @PostMapping("/addImg")
    @CrossOrigin
    public R<String> addImage(@RequestBody ImageDTO imageDTO){
        try {
            String userId = userServiceImpl.getTokenUser().getId();
            if (imageDTO.getImgurl()==null || imageDTO.getImgurl()==""){
                return R.error();
            }
            redisLtServiceImpl.addCommodityImagetoRedis(userId, imageDTO.getImgurl());
            return R.success(imageDTO.getImgurl(),"上传成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

}
