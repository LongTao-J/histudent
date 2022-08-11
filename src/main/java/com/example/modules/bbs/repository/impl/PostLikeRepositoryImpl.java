package com.example.modules.bbs.repository.impl;

import com.example.modules.bbs.entity.po.Post;
import com.example.modules.bbs.entity.po.PostLike;
import com.example.modules.bbs.repository.PostLikeRepository;
import com.example.modules.bbs.service.PostLikeService;
import com.example.modules.bbs.service.PostService;
import com.example.modules.bbs.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostLikeRepositoryImpl implements PostLikeRepository {
    @Autowired
    PostLikeService postLikeServiceImpl;
    @Autowired
    RedisService redisServiceImpl;
    @Autowired
    PostService postServiceImpl;

    @Override
    public void savelike(String userId, String postId) {
        Integer like = isLike(userId, postId);
        if(like == null || like == 0){
            redisServiceImpl.savelikeFromRedis(userId, postId);
            // 搜索redis有没有存数量, 存了直接加一, 没存在数据库获取后加一
            Integer count = redisServiceImpl.getLikedCountFromRedisByPostId(postId);
            if(count == null){
                Post post = postServiceImpl.getPostById(postId);
                Integer likeCount = post.getLikeCount();
                redisServiceImpl.setLikeCountFromRedis(postId, likeCount);
            }
            redisServiceImpl.incrementLikedCount(postId);
        }
    }

    @Override
    public void unlike(String userId, String postId) {
        Integer like = isLike(userId, postId);
        if(like != null && like == 1){
            redisServiceImpl.unlikeFromRedis(userId, postId);
            // 搜索redis有没有存数量, 存了直接减一, 没存在数据库获取后减一
            Integer count = redisServiceImpl.getLikedCountFromRedisByPostId(postId);
            if(count == null){
                Post post = postServiceImpl.getPostById(postId);
                Integer likeCount = post.getLikeCount();
                redisServiceImpl.setLikeCountFromRedis(postId, likeCount);
            }
            redisServiceImpl.decrementLikedCount(postId);
        }
    }

    @Override
    public Integer isLike(String userId, String postId) {
        // 从redis中查找数据
        Integer isLikeFromRedis = redisServiceImpl.getIsLikeFromRedis(userId, postId);
        // 如果redis中存在直接获取
        if(isLikeFromRedis != null){
            return isLikeFromRedis;
        }else{
            // redis中不存在从mysql中获取并存储在redis中
            PostLike likeDataFromMySql = postLikeServiceImpl.getLikeByUserIdAndPostId(userId, postId);
            if(likeDataFromMySql == null) return null;
            else {
                if (likeDataFromMySql.getStatus() == 1) {
                    redisServiceImpl.savelikeFromRedis(userId, postId);
                    return 1;
                }
                else {
                    redisServiceImpl.unlikeFromRedis(userId, postId);
                    return 0;
                }
            }
        }
    }

    @Override
    public Integer getLikeCount(String postId) {
        Integer countFromRedis = redisServiceImpl.getLikedCountFromRedisByPostId(postId);
        if(countFromRedis == null){
            Post post = postServiceImpl.getPostById(postId);
            if(post == null) return null;
            // 更新redis
            redisServiceImpl.setLikeCountFromRedis(postId, post.getLikeCount());
            return post.getLikeCount();
        }else return countFromRedis;
    }
}
