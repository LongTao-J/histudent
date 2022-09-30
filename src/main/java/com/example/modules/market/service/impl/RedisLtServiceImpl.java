package com.example.modules.market.service.impl;

import com.example.modules.market.entity.dto.WantLikeCountDTO;
import com.example.modules.market.entity.po.CommodityWant;
import com.example.modules.market.enums.WantStatusEnum;
import com.example.modules.market.service.RedisLtService;
import com.example.modules.market.utils.CommodityRedisKeyUtil;
import com.example.modules.wall.utils.WallRedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RedisLtServiceImpl implements RedisLtService {

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void saveLikedRedis(String UserId, String commodityId) {
        String key = CommodityRedisKeyUtil.getLikedKey(UserId, commodityId);
        redisTemplate.opsForHash().put(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key, WantStatusEnum.ISLIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String UserId, String commodityId) {
        String key = CommodityRedisKeyUtil.getLikedKey(UserId, commodityId);
        redisTemplate.opsForHash().put(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key, WantStatusEnum.ISUNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String UserId, String commodityId) {
        String key = CommodityRedisKeyUtil.getLikedKey(UserId, commodityId);
        redisTemplate.opsForHash().delete(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(String UserId) {
        redisTemplate.opsForHash().increment(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, UserId, 1);
    }

    @Override
    public void decrementLikedCount(String UserId) {
        redisTemplate.opsForHash().increment(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, UserId, -1);
    }

    @Override
    public void setLikeCountFromRedis(String commodityId, Integer value) {
        redisTemplate.opsForHash().put(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, commodityId, value);
    }

    @Override
    public Integer getIsLikeFromRedis(String userId, String commodityId) {
        String key = CommodityRedisKeyUtil.getLikedKey(userId, commodityId);
        Object o = redisTemplate.opsForHash().get(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key);
        if(o == null) return 0;
        else return (Integer) o;
    }

    @Override
    public Integer getLikedCountFromRedisByPostId(String commodityId) {
        return (Integer) redisTemplate.opsForHash().get(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, commodityId);

    }

    @Override
    public List<CommodityWant> getLikedDataWithRemoveFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<CommodityWant> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，postId
            String[] split = key.split("::");
            String userId = split[0];
            String commodityId = split[1];
            Integer status = (Integer) entry.getValue();
            // 封装对象
            CommodityWant commodityWant=new CommodityWant();
            commodityWant.setUserId(userId);
            commodityWant.setCommodityId(commodityId);
            commodityWant.setStatus(status);
            list.add(commodityWant);
            // 存到List后从Redis中删除
            redisTemplate.opsForHash().delete(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key);
        }
        cursor.close();
        return list;
    }

    @Override
    public List<WantLikeCountDTO> getLikedCountWithRemoveFromRedis() {

        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<WantLikeCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            // 封装对象存储在List
            String key = (String)map.getKey();
            WantLikeCountDTO dto = new WantLikeCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            // 从Redis中删除记录
            redisTemplate.opsForHash().delete(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, key);
        }
        cursor.close();
        return list;
    }

    @Override
    public void clearPostLikeCountCache(String commodityId) {
        redisTemplate.opsForHash().delete(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED_COUNT, commodityId);
    }

    @Override
    public void clearPostLikeCache(String commodityId) {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，postId
            String[] split = key.split("::");
            if(commodityId.equals(split[1])){
                // 从Redis中删除
                redisTemplate.opsForHash().delete(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, key);
            }
        }
        cursor.close();
    }

    //获取所有点赞数据
    @Override
    public List<CommodityWant> getAllWantFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(CommodityRedisKeyUtil.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<CommodityWant> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，postId
            String[] split = key.split("::");
            String userId = split[0];
            String commodityId = split[1];
            Integer status = (Integer) entry.getValue();
                // 封装对象
                CommodityWant commodityWant=new CommodityWant();
                    commodityWant.setUserId(userId);
                    commodityWant.setCommodityId(commodityId);
                    commodityWant.setStatus(status);
                list.add(commodityWant);

        }
        cursor.close();
        return list;
    }

    @Override
    public void addCommodityImagetoRedis(String userId, String url) {
        redisTemplate.opsForList().rightPush(userId, url);
    }

    @Override
    public void clearCommodityImage(String userid) {
        redisTemplate.delete(userid);
    }

    @Override
    public List<String> getCommodityAllImgFromRedis(String userId) {
        return redisTemplate.opsForList().range(userId, 0, -1);
    }



}


