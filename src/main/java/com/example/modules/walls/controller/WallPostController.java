package com.example.modules.walls.controller;


import com.alibaba.fastjson.JSON;
import com.example.modules.user.pojo.User;
import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙帖子表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/walls/wall-post")
public class WallPostController {
    @Autowired
    private WallPostService wallPostServiceImpl;

    @DeleteMapping("/delete/{id}")
    public String deleteWallPost(@PathVariable("id") String id){
        int code = wallPostServiceImpl.deleteWallPostById(id);
        return "{\"code\":" + code + "}";
    }

    @GetMapping("/get/list/{title}")
    public String queryWallPostListByTitle(@PathVariable("title") String title){
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByTitle(title);
        return JSON.toJSONString(wallPosts);
    }

    @GetMapping("/get/list/{user_id}")
    public String queryWallPostListByUserId(@PathVariable("user_id") String userId){
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByUserId(userId);
        return JSON.toJSONString(wallPosts);
    }

    @PutMapping("/put/add_post")
    public String addWallPost(User user, String title, String content){
        WallPost wallPost = new WallPost();
        wallPost.setUserId(user.getId());
        wallPost.setTitle(title);
        wallPost.setContent(content);
        int code = wallPostServiceImpl.insertWallPost(wallPost);
        return "{\"code\":" + code + "}";
    }
}
