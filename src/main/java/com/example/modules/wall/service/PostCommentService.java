package com.example.modules.wall.service;

import com.example.modules.wall.entity.po.PostComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PostCommentService extends IService<PostComment> {

    List<PostComment> getListByPostId(String postId);

    void addComment(String userId, String postId, String content);
}
