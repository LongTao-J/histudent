package com.example.modules.wall.controller;


import com.example.modules.wall.service.PostCollectService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/post-collect")
public class PostCollectController {

    @Autowired
    PostCollectService postCollectServiceImpl;

    @PutMapping("/put/collect/{post-id}")
    @CrossOrigin
    public R<Object> collect(@PathVariable("post-id")String postId){
        try{
            // redis获取当前用户id
            String userId = "1";    // 暂定为1
            postCollectServiceImpl.addCollect(userId, postId);
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
            String userId = "1";    // 暂定为1
            postCollectServiceImpl.deleteCollect(userId, postId);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }
}

