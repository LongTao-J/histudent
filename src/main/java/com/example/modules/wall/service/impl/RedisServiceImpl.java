package com.example.modules.wall.service.impl;


import com.example.modules.wall.entity.dto.PostLikedCountDTO;
import com.example.modules.wall.entity.po.PostLike;
import com.example.modules.wall.enums.LikedStatusEnum;
import com.example.modules.wall.service.RedisService;
import com.example.modules.wall.utils.WallRedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void savelikeFromRedis(String userId, String postId) {
        String key = WallRedisKeyUtils.getLikeKey(userId, postId);
        redisTemplate.opsForHash().put(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String userId, String postId) {
        String key = WallRedisKeyUtils.getLikeKey(userId, postId);
        redisTemplate.opsForHash().put(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String userId, String postId) {
        String key = WallRedisKeyUtils.getLikeKey(userId, postId);
        redisTemplate.opsForHash().delete(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key);
    }

    @Override
    public void incrementLikedCount(String postId) {
        redisTemplate.opsForHash().increment(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, postId, 1);
    }

    @Override
    public void decrementLikedCount(String postId) {
        redisTemplate.opsForHash().increment(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, postId, -1);
    }

    @Override
    public void setLikeCountFromRedis(String postId, Integer value) {
        redisTemplate.opsForHash().put(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, postId, value);
    }

    @Override
    public Integer getIsLikeFromRedis(String userId, String postId) {
        String key = WallRedisKeyUtils.getLikeKey(userId, postId);
        Object o = redisTemplate.opsForHash().get(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key);
        if(o == null) return null;
        else return (Integer) o;
    }

    @Override
    public Integer getLikedCountFromRedisByPostId(String postId) {
        return (Integer) redisTemplate.opsForHash().get(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, postId);
    }

    @Override
    public List<PostLike> getLikedDataWithRemoveFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(WallRedisKeyUtils.HASH_KEY_USER_LIKE, ScanOptions.NONE);
        List<PostLike> list = new ArrayList<>();
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，postId
            String[] split = key.split("::");
            String userId = split[0];
            String postId = split[1];
            Integer status = (Integer) entry.getValue();
            // 封装对象
            PostLike postLike = new PostLike(userId, postId, status);
            list.add(postLike);

            // 存到List后从Redis中删除
            redisTemplate.opsForHash().delete(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key);
        }
        cursor.close();
        return list;
    }

    @Override
    public List<PostLikedCountDTO> getLikedCountWithRemoveFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, ScanOptions.NONE);
        List<PostLikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            // 封装对象存储在List
            String key = (String)map.getKey();
            PostLikedCountDTO dto = new PostLikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            // 从Redis中删除记录
            redisTemplate.opsForHash().delete(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, key);
        }
        cursor.close();
        return list;
    }

    @Override
    public void lpushPostFileCache(String postId, String url) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        redisTemplate.opsForList().leftPush(key, url);
    }

    @Override
    public void rpushPostFileCache(String postId, String url) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        redisTemplate.opsForList().rightPush(key, url);
    }

    @Override
    public void lpopPostFileCache(String postId) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public void rpopPostFileCache(String postId) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long getPostFileCacheSize(String postId) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public String getPostFileCacheByIndex(String postId, Long index) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        return (String) redisTemplate.opsForList().index(key, index);
    }

    @Override
    public List<String> getPostFileCacheListByRange(String postId, Long start, Long end) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        List<String> list = redisTemplate.opsForList().range(key, start, end);
        return list;
    }

    @Override
    public List<String> getPostAllFileCache(String postId) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        List<String> list = redisTemplate.opsForList().range(key, 0, -1);
        return list;
    }

    @Override
    public void clearPostFileCache(String postId) {
        String key = WallRedisKeyUtils.getPostFileListKey(postId);
        redisTemplate.delete(key);
    }

    @Override
    public void addReleasePostFileFromRedis(String userId, String url) {
        String key = WallRedisKeyUtils.getReleasePostFileListKey(userId);
        redisTemplate.opsForList().rightPush(key, url);
    }

    @Override
    public List<String> getReleasePostFileAllFromRedis(String userId) {
        String key = WallRedisKeyUtils.getReleasePostFileListKey(userId);
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public String getReleasePostFileByIndexFromRedis(String userId, Long index) {
        String key = WallRedisKeyUtils.getReleasePostFileListKey(userId);
        return (String) redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long getReleasePostFileListSizeFromRedis(String userId) {
        String key = WallRedisKeyUtils.getReleasePostFileListKey(userId);
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public void clearReleasePostFileFromRedis(String userId) {
        String key = WallRedisKeyUtils.getReleasePostFileListKey(userId);
        System.out.println(key);
        redisTemplate.delete(key);
    }

    @Override
    public void deleteRedisPostDate(String postId) {
        clearPostFileCache(postId);
        clearPostLikeCache(postId);
        clearPostLikeCountCache(postId);
    }

    @Override
    public void clearPostLikeCountCache(String postId) {
        redisTemplate.opsForHash().delete(WallRedisKeyUtils.HASH_KEY_POST_LIKED_COUNT, postId);
    }

    @Override
    public void clearPostLikeCache(String postId) {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(WallRedisKeyUtils.HASH_KEY_USER_LIKE, ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 userId，postId
            String[] split = key.split("::");
            if(postId.equals(split[1])){
                // 从Redis中删除
                redisTemplate.opsForHash().delete(WallRedisKeyUtils.HASH_KEY_USER_LIKE, key);
            }
        }
        cursor.close();
    }
}
