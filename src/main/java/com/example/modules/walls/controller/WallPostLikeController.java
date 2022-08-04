package com.example.modules.walls.controller;


import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.service.WallPostLikeService;
import com.example.modules.walls.service.WallPostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 表白墙帖子喜欢表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/api/walls/wall-post-like")
public class WallPostLikeController {
    @Autowired
    private WallPostLikeService wallPostLikeServiceImpl;
    @Autowired
    private WallPostService wallPostServiceImpl;

    @PutMapping("/put/{wall_post_id}/{user_id}")
    public R<Object> addSWallPostLike(@PathVariable("wall_post_id") String wallPostId, @PathVariable("user_id") String userId){
        WallPostLike wallPostLike = new WallPostLike();
        wallPostLike.setWallPostId(wallPostId);
        wallPostLike.setUserId(userId);
        int code = wallPostLikeServiceImpl.insertWallPostLike(wallPostLike);
        if(code != 0) {
            WallPost wallPost = new WallPost();
            wallPost.setId(wallPostId);
            wallPost.setLikeCount(wallPost.getLikeCount() + 1);
            wallPostServiceImpl.updateById(wallPost);
            return R.success(null);
        }
        else return R.error();
    }

    @DeleteMapping("/delete/{wall_post_id}/{user_id}")
    public R<Object> deleteWallPostLike(@PathVariable("wall_post_id") String wallPostId, @PathVariable("user_id") String userId){
        WallPostLike wallPostLike = new WallPostLike();
        wallPostLike.setWallPostId(wallPostId);
        wallPostLike.setUserId(userId);
        int code = wallPostLikeServiceImpl.deleteWallPostLikeByUserIdAndWallPostId(wallPostLike);
        if(code != 0) {
            WallPost wallPost = new WallPost();
            wallPost.setId(wallPostId);
            wallPost.setLikeCount(wallPost.getLikeCount() - 1);
            wallPostServiceImpl.updateById(wallPost);
            return R.success(null);
        }
        else return R.error();
    }

    /**
     * 获取点赞列表
     */
    @GetMapping("/get/{user_id}")
    public R<Object> getWallPostListForUserLike(@PathVariable("user_id") String userId){
        List<WallPostLike> wallPostLikes = wallPostLikeServiceImpl.selectWallPostLikeByUserId(userId);
        if(wallPostLikes.isEmpty()) return R.error();
        else return R.success(wallPostLikes);
    }

}

