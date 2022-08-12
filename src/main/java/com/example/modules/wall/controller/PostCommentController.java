package com.example.modules.wall.controller;


import com.example.modules.wall.entity.po.PostComment;
import java.util.List;

import com.example.modules.wall.service.PostCommentService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/comment")
public class PostCommentController {
    @Autowired
    PostCommentService postCommentServiceImpl;

    @GetMapping("/get/all/post-comments/{post-id}")
    @CrossOrigin
    public R<Object> getPostCommentAll(@PathVariable("post-id")String postId){
        try{
            List<PostComment> postComments = postCommentServiceImpl.getListByPostId(postId);
            return R.success(postComments);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/issue-comment/{post-id}")
    @CrossOrigin
    public R<Object> issueComment(@PathVariable("post-id")String postId, @RequestBody String content){
        try{
            // redis获取当前用户id
            String userId = "1";    // 暂定为1
            postCommentServiceImpl.addComment(userId, postId, content);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }
}

