package com.example.modules.wall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.wall.entity.po.PostComment;
import com.example.modules.wall.mapper.PostCommentMapper;
import com.example.modules.wall.service.PostCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements PostCommentService {
    @Autowired
    PostCommentMapper postCommentMapper;

    @Override
    public List<PostComment> getListByPostId(String postId) {
        QueryWrapper<PostComment> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        return postCommentMapper.selectList(wrapper);
    }

    @Override
    public void addComment(String userId, String postId, String content) {
        PostComment postComment = new PostComment();
        postComment.setPostId(postId);
        postComment.setUserId(userId);
        postComment.setContent(content);
        postCommentMapper.insert(postComment);
    }

    @Override
    public Integer getCommentCount(String postId) {
        QueryWrapper<PostComment> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        return postCommentMapper.selectCount(wrapper);
    }

    @Override
    public PostComment getComment(String commentId) {
        return postCommentMapper.selectById(commentId);
    }

    @Override
    public void deleteComment(String commentId) {
        postCommentMapper.deleteById(commentId);
    }

    @Override
    public void deleteCommentDate(String postId) {
        QueryWrapper<PostComment> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id", postId);
        postCommentMapper.delete(wrapper);
    }
}
