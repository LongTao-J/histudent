package com.example.modules.walls.controller;


import com.example.modules.walls.model.WallPostRecommended;
import com.example.modules.walls.model.WallPostWithUserAndImg;
import com.example.modules.walls.service.WallPostRecommendedService;
import com.example.modules.walls.service.WallPostWithUserAndImgService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private WallPostWithUserAndImgService wallPostWithUserAndImgServiceImpl;

    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public R<Object> deleteRecommended(@PathVariable("id") String id){
        int code = wallPostRecommendedServiceImpl.deleteRecommendedById(id);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    @GetMapping("/get/post_list")
    @CrossOrigin
    public R<Object> queryAllRecommended(){
        List<WallPostWithUserAndImg> wallPostWithUserAndImgs = wallPostWithUserAndImgServiceImpl.selectWallPostWithUsersByRecommended();
        return R.success(wallPostWithUserAndImgs);
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

