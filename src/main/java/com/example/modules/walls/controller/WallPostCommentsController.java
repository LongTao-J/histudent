package com.example.modules.walls.controller;


import com.example.modules.user.pojo.User;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostComments;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.service.WallPostCommentsService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 表白墙帖子评论表 前端控制器
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/api/walls/wall-post-comments")
public class WallPostCommentsController {
    @Autowired
    WallPostCommentsService wallPostCommentsServiceImpl;

    /**
     * 评论帖子
     * @param user
     * @param wallPost
     * @param context
     * @return
     */
    @PutMapping("/put")
    @CrossOrigin
    public R<Object> addWallPostComments(@RequestBody User user, @RequestBody WallPost wallPost, @RequestBody String context){
        int code = wallPostCommentsServiceImpl.insertWallPostComments(user.getId(), wallPost.getId(), context);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    @DeleteMapping("/delete/{wall_post_comments_id}")
    @CrossOrigin
    public R<Object> deleteWallPostComments(@PathVariable String wall_post_comments_id){
        int code = wallPostCommentsServiceImpl.deleteWallPostComment(wall_post_comments_id);
        if(code != 0) return R.success(null);
        else return R.error();
    }

    /**
     * 获取帖子所有评论
     * @param wall_post_id
     * @return
     */
    @GetMapping("/get/all_comments/{wall_post_id}")
    @CrossOrigin
    public R<Object> getWallPostAllComments(@PathVariable String wall_post_id){
        List<WallPostComments> wallPostComments = wallPostCommentsServiceImpl.selectWallPostCommentsAllByWallPostId(wall_post_id);
        if(wallPostComments.isEmpty()) return R.error();
        else return R.success(wallPostComments);
    }
}

