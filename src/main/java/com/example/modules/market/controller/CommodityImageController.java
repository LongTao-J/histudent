package com.example.modules.market.controller;

import com.example.modules.market.entity.dto.ImageDTO;
import com.example.modules.market.repository.CommodityImgRepository;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.user.utils.Consts;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/market/image")
public class CommodityImageController {

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    @Autowired
    CommodityImgRepository commodityImgRepositoryImpl;

    @Autowired
    RedisTemplate redisTemplate;

    //上传商品的照片
    @PostMapping("/addImg")
    public R<String> addImage(@RequestBody ImageDTO imageDTO){
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);
            if (imageDTO.getImgurl()==null || imageDTO.getImgurl()==""){
                return R.error();
            }
            commodityImgRepositoryImpl.uploadReleasePostFile(userId,imageDTO.getImgurl());
            return R.success("上传成功");
        }catch (Exception e){
            return R.error();
        }
    }

}
