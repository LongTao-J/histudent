package com.example.modules.walls.controller;


import com.example.modules.user.utils.Consts;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.service.WallPostLikeService;
import com.example.modules.walls.service.WallPostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 表白墙帖子喜欢表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/api/walls/wall-post-like")
public class WallPostLikeController {
    @Autowired
    private WallPostLikeService wallPostLikeServiceImpl;
    @Autowired
    private WallPostService wallPostServiceImpl;
    //redis
    @Autowired
    private RedisTemplate redisTemplate;



    @PutMapping("/put/{wall_post_id}")
    @CrossOrigin
    public R<Object> addWallPostLike(@PathVariable("wall_post_id") String wallPostId){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        int code = wallPostLikeServiceImpl.insertWallPostLike(userId, wallPostId);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    @DeleteMapping("/delete/{wall_post_id}")
    @CrossOrigin
    public R<Object> deleteWallPostLike(@PathVariable("wall_post_id") String wallPostId){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        int code = wallPostLikeServiceImpl.deleteWallPostLike(userId, wallPostId);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    /**
     * 获取用户点赞的所有帖子
     */
    @GetMapping("/get/list/user/{user_id}")
    @CrossOrigin
    public R<Object> getWallPostListForUserLike(@PathVariable("user_id") String userId){
        List<WallPostLike> wallPostLikes = wallPostLikeServiceImpl.selectWallPostLikeByUserId(userId);
        if(wallPostLikes.isEmpty()) return R.error();
        else return R.success(wallPostLikes);
    }

    @GetMapping("/get/list/post/{wall_post_id}")
    @CrossOrigin
    public R<Object> getWallPostListForPost(@PathVariable("wall_post_id") String wallPostId){
        List<WallPostLike> wallPostLikes = wallPostLikeServiceImpl.selectWallPostLikeByWallPostId(wallPostId);
        if(wallPostLikes.isEmpty()) return R.error();
        else return R.success(wallPostLikes);
    }

    @GetMapping("/get/islike/{wall_post_id}")
    @CrossOrigin
    public R<Object> getIsLike(@PathVariable("wall_post_id") String wallPostId){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        Boolean code = wallPostLikeServiceImpl.selectIsLike(userId, wallPostId);
        return R.success(code);
    }
}

