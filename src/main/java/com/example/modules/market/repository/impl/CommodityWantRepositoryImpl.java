package com.example.modules.market.repository.impl;

import com.example.modules.market.entity.po.Commodity;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.market.entity.vo.CommodityVO;
import com.example.modules.market.enums.WantStatusEnum;
import com.example.modules.market.repository.CommodityWantRepository;
import com.example.modules.market.service.CommodityService;
import com.example.modules.market.service.CommodityWantService;
import com.example.modules.market.service.RedisLtService;
import com.example.modules.market.utils.CommodityRedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommodityWantRepositoryImpl implements CommodityWantRepository {

    @Autowired
    CommodityWantService commodityWantServiceImpl;
    @Autowired
    RedisLtService redisLtServiceImpl;
    @Autowired
    CommodityService commodityServiceImpl;

    @Override
    public void savelike(String userId, String commodityId) {
        Integer like = isLike(userId, commodityId);
        if(like == null || like == 0){
            redisLtServiceImpl.saveLikedRedis(userId, commodityId);
            // 搜索redis有没有存数量, 存了直接加一, 没存在数据库获取后加一
            Integer count = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityId);
            if(count == null){
                Commodity commodity = commodityServiceImpl.getCommodityById(commodityId);
                Integer likeCount = commodity.getWant();
                redisLtServiceImpl.setLikeCountFromRedis(commodityId, likeCount);
            }
            redisLtServiceImpl.incrementLikedCount(commodityId);
        }
    }

    @Override
    public void unlike(String userId, String commodityId) {
        Integer like = isLike(userId, commodityId);
        if(like != null && like == 1){
            redisLtServiceImpl.unlikeFromRedis(userId, commodityId);
            // 搜索redis有没有存数量, 存了直接减一, 没存在数据库获取后减一
            Integer count = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityId);
            if(count == null){
                Commodity commodity = commodityServiceImpl.getCommodityById(commodityId);
                Integer likeCount = commodity.getWant();
                redisLtServiceImpl.setLikeCountFromRedis(commodityId, likeCount);
            }
            redisLtServiceImpl.decrementLikedCount(commodityId);
        }
    }

    @Override
    public Integer isLike(String userId, String commodityId) {
        try {
            // 从redis中查找数据
            Integer isLikeFromRedis = redisLtServiceImpl.getIsLikeFromRedis(userId, commodityId);
            // 如果redis中存在直接获取
            if(isLikeFromRedis != null&& isLikeFromRedis==1){
                return isLikeFromRedis;
            }else{
                // redis中不存在从mysql中获取并存储在redis中
                CommodityWant likeDataFromMySql=commodityWantServiceImpl.getWantByUserIdAndCommodityId(userId,commodityId);
                if(likeDataFromMySql == null) return null;
                else {
                    if (likeDataFromMySql.getStatus() == 1) {
                        redisLtServiceImpl.saveLikedRedis(userId, commodityId);
                        return 1;
                    }
                    else {
                        redisLtServiceImpl.unlikeFromRedis(userId, commodityId);
                        return 0;
                    }
                }
            }
        }catch (Exception e){
            return 0;
        }
    }


    @Override
    public Integer getLikeCount(String commodityId) {
        Integer countFromRedis = redisLtServiceImpl.getLikedCountFromRedisByPostId(commodityId);
        if(countFromRedis == null){
            Commodity commodity = commodityServiceImpl.getCommodityById(commodityId);
            if(commodity == null) return null;
            // 更新redis
            redisLtServiceImpl.setLikeCountFromRedis(commodityId, commodity.getWant());
            return commodity.getWant();
        }else return countFromRedis;
    }

    //查询我想要的商品
    @Override
    public List<CommodityVO> getMyWantCommodity(String userId) {
        List<CommodityVO> commodityVOList=new ArrayList<>();
        //从数据库中找
        List<String> allWant = commodityWantServiceImpl.getAllWant(userId);
        if (allWant.size()!=0){
            for (int i=0;i<allWant.size();i++){
                //数据库查
                CommodityVO myCommodityByCidSerlt = commodityServiceImpl.getMyWantCommodityByCidSer(allWant.get(0));
                commodityVOList.add(myCommodityByCidSerlt);
            }
        }

        //先在redis中找
        List<CommodityWant> commodityWants=redisLtServiceImpl.getAllWantFromRedis();
        if (commodityWants.size()!=0){
            for (int i=0;i<commodityWants.size();i++){
                if (commodityWants.get(i).getUserId()==userId){
                    //数据库查
                    CommodityVO myCommodityByCidSer = commodityServiceImpl.getMyWantCommodityByCidSer(commodityWants.get(i).getCommodityId());
                    commodityVOList.add(myCommodityByCidSer);
                }
            }
        }

        return commodityVOList;
    }
}
