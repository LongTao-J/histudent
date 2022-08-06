package com.example.modules.walls.controller;


import com.alibaba.fastjson.JSON;
import com.example.modules.user.pojo.FileUploadResponse;
import com.example.modules.user.pojo.User;
import com.example.modules.user.utils.Consts;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.service.WallPostFileService;
import com.example.modules.walls.service.WallPostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表白墙帖子表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/api/walls/wall-post")
public class WallPostController {
    @Autowired
    private WallPostService wallPostServiceImpl;
    @Autowired
    private WallPostFileService wallPostFileServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;

    private List<String> images = new ArrayList<>();

    @DeleteMapping("/delete/by-id/{id}")
    @CrossOrigin
    public R<Object> deleteWallPost(@PathVariable("id") String id){
        try {
            wallPostServiceImpl.deleteWallPostById(id);
            wallPostFileServiceImpl.deleteFilesByPostId(id);
            return R.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    @GetMapping("/get/list/by-title/{title}")
    @CrossOrigin
    public R<Object> queryWallPostListByTitle(@PathVariable("title") String title){
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByTitle(title);
        return R.success(wallPosts);
    }

    @GetMapping("/get/list/by-user/")
    @CrossOrigin
    public R<Object> queryWallPostListByUserId(){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByUserId(userId);
        return R.success(wallPosts);
    }

    @PostMapping("/post/upload/file/{url}")
    @CrossOrigin
    public R<Object> uploadFile(@PathVariable("url") String url){
        try{
            images.add(url);
            return R.success(images,"图像上传成功",200);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/add_post/{title}/{content}")
    @CrossOrigin
    public R<Object> addWallPost(@PathVariable("title") String title,@PathVariable("content") String content){
        ValueOperations<String,String> redis = redisTemplate.opsForValue();
        String userId=redis.get(Consts.REDIS_USER);
        WallPost wallPost = new WallPost();
        wallPost.setUserId(userId);
        wallPost.setTitle(title);
        wallPost.setContent(content);
        wallPostServiceImpl.insertWallPost(wallPost);
        String wallPostId = wallPost.getId();
        wallPostFileServiceImpl.insertImgList(wallPostId, images);
        if(!images.isEmpty()){
            wallPost.setHeadImg(images.get(0));
            wallPostServiceImpl.updateById(wallPost);
        }
        return R.success(null);
    }
}
