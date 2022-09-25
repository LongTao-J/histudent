package com.example.modules.wall.controller;


import com.example.modules.user.pojo.po.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.utils.Consts;
import com.example.modules.wall.entity.dto.PostCommentFromViewDTO;
import com.example.modules.wall.entity.po.Post;
import com.example.modules.wall.entity.po.PostComment;

import java.util.ArrayList;
import java.util.List;

import com.example.modules.wall.entity.vo.PostCommentVO;
import com.example.modules.wall.service.PostCommentService;
import com.example.modules.wall.service.PostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wall/comment")
public class PostCommentController {
    @Autowired
    PostCommentService postCommentServiceImpl;
    @Autowired
    PostService postServiceImpl;
    @Autowired
    UserService userServiceImpl;


    @GetMapping("/get/all/post-comments/{post-id}")
    @CrossOrigin
    public R<Object> getPostCommentAll(@PathVariable("post-id")String postId){
        try{
            List<PostComment> postComments = postCommentServiceImpl.getListByPostId(postId);
            postComments.sort((t1, t2) -> t2.getGmtCreate().compareTo(t1.getGmtCreate()));
            List<PostCommentVO> listVO = new ArrayList<>();
            for(PostComment comment : postComments){
                PostCommentVO vo = new PostCommentVO();
                User issuer = userServiceImpl.getById(comment.getUserId());
                vo.setComment(comment);
                vo.setUserNickname(issuer.getNickname());
                vo.setUserHead(issuer.getHeadaddress());
                listVO.add(vo);
            }
            return R.success(listVO);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/issue-comment/{post-id}")
    @CrossOrigin
    public R<Object> issueComment(@PathVariable("post-id")String postId, @RequestBody PostCommentFromViewDTO comment){
        try{
            // redis获取当前用户id
            String userId = userServiceImpl.getTokenUser().getId();
            postCommentServiceImpl.addComment(userId, postId, comment.getContent());
            Post post = postServiceImpl.getPostById(postId);
            post.setCommentCount(post.getCommentCount() + 1);
            // 更新评论数量
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @PutMapping("/put/delete-comment/{comment-id}")
    @CrossOrigin
    public R<Object> deleteComment(@PathVariable("comment-id") String commentId){
        try{
            // redis获取当前用户id
            String userId = userServiceImpl.getTokenUser().getId();
            PostComment comment = postCommentServiceImpl.getComment(commentId);
            postCommentServiceImpl.deleteComment(commentId);
            String postId = comment.getPostId();
            Post post = postServiceImpl.getPostById(postId);
            post.setCommentCount(post.getCommentCount() - 1);
            // 更新评论数量
            postServiceImpl.updatePostById(post);
            return R.success(null);
        }catch (Exception e){
            return R.error();
        }
    }

    @GetMapping("/get/comment-count/{post-id}")
    @CrossOrigin
    public R<Object> getCommentCount(@PathVariable("post-id") String postId){
        try{
            Integer count = postCommentServiceImpl.getCommentCount(postId);
            return R.success(count);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}

