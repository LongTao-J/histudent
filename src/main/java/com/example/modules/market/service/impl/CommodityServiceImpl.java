package com.example.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.market.entity.dto.CommodityDTO;
import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.mapper.CommodityMapper;
import com.example.modules.market.service.CommodityImageService;
import com.example.modules.market.service.CommodityService;
import com.example.modules.user.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    CommodityImageService commodityImageServiceImpl;

    @Autowired
    RedisLtServiceImpl redisLtServiceImpl;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean issueCommodity(CommodityDTO commodityDTO) {
        try {
            ValueOperations<String,String> redis = redisTemplate.opsForValue();
            String userId = redis.get(Consts.REDIS_USER);

            Commodity commodity=new Commodity();
            commodity.setCount(commodityDTO.getCount());
            commodity.setTitle(commodityDTO.getTitle());
            commodity.setPrice(commodityDTO.getPrice());
            commodity.setIntroduce(commodityDTO.getIntroduce());
            commodity.setUserId(userId);
            commodityMapper.insert(commodity);
            List<String> imgs = redisLtServiceImpl.getCommodityAllImgFromRedis(userId);
            for (String img:imgs){
                commodityImageServiceImpl.insertImg(commodity.getId(),img);
            }
            redisLtServiceImpl.clearCommodityImage(commodity.getUserId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<CommodityVO> getAllCommodityService() {
        List<CommodityVO> commodityVOList=new ArrayList<>();
        commodityVOList=commodityMapper.getAllCommodityVo();
        for (int i=0;i<commodityVOList.size();i++){
            List<String> allImg= commodityImageServiceImpl.getAllImgService(commodityVOList.get(i).getId());
            commodityVOList.get(i).setAllImg(allImg);
            commodityVOList.get(i).setTotalImage(allImg.get(0));
            System.out.println(allImg.get(0));
        }

        return commodityVOList;
    }

    @Override
    public boolean cancleImg() {
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId = redis.get(Consts.REDIS_USER);

        redisLtServiceImpl.clearCommodityImage(userId);
        return true;
    }

    @Override
    public Commodity getCommodityById(String id) {
        return commodityMapper.selectById(id);
    }


    @Override
    public Boolean updateCommodityById(Commodity commodity) {
        try{
            commodityMapper.updateById(commodity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
