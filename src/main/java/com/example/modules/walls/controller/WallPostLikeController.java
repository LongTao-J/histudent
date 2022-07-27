package com.example.modules.walls.controller;


import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.service.WallPostLikeService;
import com.example.modules.walls.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 表白墙帖子喜欢表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/walls/wall-post-like")
public class WallPostLikeController {
    @Autowired
    private WallPostLikeService wallPostLikeServiceImpl;


}

