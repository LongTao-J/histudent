package com.example.modules.walls.controller;


import com.alibaba.fastjson.JSON;
import com.example.modules.user.pojo.User;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.service.WallPostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/walls/wall-post")
public class WallPostController {
    @Autowired
    private WallPostService wallPostServiceImpl;

    @DeleteMapping("/delete/{id}")
    public R<Object> deleteWallPost(@PathVariable("id") String id){
        int code = wallPostServiceImpl.deleteWallPostById(id);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    @GetMapping("/get/list/{title}")
    public R<Object> queryWallPostListByTitle(@PathVariable("title") String title){
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByTitle(title);
        return R.success(wallPosts);
    }

    @GetMapping("/get/list/{user_id}")
    public R<Object> queryWallPostListByUserId(@PathVariable("user_id") String userId){
        List<WallPost> wallPosts = wallPostServiceImpl.selectWallPostListByUserId(userId);
        return R.success(wallPosts);
    }

    @PutMapping("/put/add_post")
    public R<Object> addWallPost(@RequestBody User user, @RequestBody String title,@RequestBody String content){
        WallPost wallPost = new WallPost();
        wallPost.setUserId(user.getId());
        wallPost.setTitle(title);
        wallPost.setContent(content);
        int code = wallPostServiceImpl.insertWallPost(wallPost);
        return R.success(null);
    }
}
