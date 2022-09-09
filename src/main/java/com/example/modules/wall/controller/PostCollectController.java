package com.example.modules.wall.controller;


import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.service.PostCollectService;
import com.example.modules.wall.service.PostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/post-collect")
public class PostCollectController {
    @Autowired
    PostService postServiceImpl;
    @Autowired
    PostCollectService postCollectServiceImpl;
    @Autowired
    UserService userServiceImpl;

    @PutMapping("/put/collect/{post-id}")
    @CrossOrigin
    public R<Object> collect(@PathVariable("post-id")String postId){
        try{
            // redis获取当前用户id
            String userId = userServiceImpl.getTokenUser().getId();
            postCollectServiceImpl.addCollect(userId, postId);
            Post post = postServiceImpl.getPostById(postId);
            post.setCollectCount(post.getCollectCount() + 1);
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/uncollect/{post-id}")
    @CrossOrigin
    public R<Object> uncollect(@PathVariable("post-id")String postId){
        try{
            // redis获取当前用户id
            String userId = userServiceImpl.getTokenUser().getId();
            postCollectServiceImpl.deleteCollect(userId, postId);
            Post post = postServiceImpl.getPostById(postId);
            post.setCollectCount(post.getCollectCount() - 1);
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }
}

