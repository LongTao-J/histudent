package com.example.modules.walls.controller;


import com.alibaba.fastjson.JSON;
import com.example.modules.user.pojo.User;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostComments;
import com.example.modules.walls.model.WallPostRecommended;
import com.example.modules.walls.model.WallPostWithUser;
import com.example.modules.walls.service.WallPostRecommendedService;
import com.example.modules.walls.service.WallPostService;
import com.example.modules.walls.service.WallPostWithUserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.loading.PrivateClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 表白墙帖子推荐表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/api/walls/wall-post-recommended")
public class WallPostRecommendedController {
    @Autowired
    private WallPostRecommendedService wallPostRecommendedServiceImpl;
    @Autowired
    private WallPostWithUserService wallPostWithUserServiceImpl;

    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public R<Object> deleteRecommended(@PathVariable("id") String id){
        int code = wallPostRecommendedServiceImpl.deleteRecommendedById(id);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    @GetMapping("/get/all")
    @CrossOrigin
    public R<Object> queryAllRecommended(){
        List<WallPostWithUser> wallPostWithUsers = wallPostWithUserServiceImpl.selectWallPostWithUsersByRecommended();
        return R.success(wallPostWithUsers);
    }

    @PutMapping("/put/add/{wall_post_id}")
    @CrossOrigin
    public R<Object> addRecommended(@PathVariable("wall_post_id") String wallPostId){
        WallPostRecommended wallPostRecommended = new WallPostRecommended();
        wallPostRecommended.setWallPostId(wallPostId);
        int code = wallPostRecommendedServiceImpl.insert(wallPostRecommended);
        if(code != 0) return R.success(null);
        else return R.error();
    }
}

