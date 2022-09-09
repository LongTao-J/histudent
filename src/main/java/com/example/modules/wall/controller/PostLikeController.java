package com.example.modules.wall.controller;

import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.modules.wall.repository.PostLikeRepository;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/postlike")
public class PostLikeController {
    @Autowired
    private PostLikeRepository postLikeRepositoryImpl;
    @Autowired
    private UserService userServiceImpl;

    @PutMapping("/put/like/{post-id}")
    @CrossOrigin
    public R<Object> like(@PathVariable("post-id") String postId){
        try{
            // 暂定为1
            String userId = userServiceImpl.getTokenUser().getId();
            postLikeRepositoryImpl.savelike(userId, postId);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @PutMapping("/put/unlike/{post-id}")
    @CrossOrigin
    public R<Object> unlike(@PathVariable("post-id") String postId){
        try{
            // 暂定为1
            String userId = userServiceImpl.getTokenUser().getId();
            postLikeRepositoryImpl.unlike(userId, postId);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/get/islike/{post-id}/{user-id}")
    @CrossOrigin
    public R<Object> getIsLike(@PathVariable("post-id") String postId, @PathVariable("user-id") String userId){
        try{
            Integer like = postLikeRepositoryImpl.isLike(userId, postId);
            return R.success(like);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/get/like-count/{post-id}")
    @CrossOrigin
    public R<Object> getLikeCount(@PathVariable("post-id") String postId){
        try{
            Integer count = postLikeRepositoryImpl.getLikeCount(postId);
            return R.success(count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
