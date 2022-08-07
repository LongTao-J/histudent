package com.example.modules.walls.controller;


import com.example.modules.user.pojo.FileUploadResponse;
import com.example.modules.user.utils.Consts;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostFile;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.service.WallPostFileService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 表白墙文件表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */

@RestController
@RequestMapping("/api/walls/wall-post-file")
public class WallPostFileController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WallPostFileService wallPostFileServiceImpl;

    @GetMapping("/get/files/by-postId/{post_id}")
    @CrossOrigin
    public R<Object> queryFileBy(@PathVariable("post_id") String postId){
        List<WallPostFile> files = wallPostFileServiceImpl.selectListByPostId(postId);
        return R.success(files);
    }
}

