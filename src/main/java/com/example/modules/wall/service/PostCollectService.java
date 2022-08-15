package com.example.modules.wall.service;

import com.example.modules.wall.entity.po.PostCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PostCollectService extends IService<PostCollect> {

    void addCollect(String userId, String postId);

    void deleteCollect(String userId, String postId);

    List<PostCollect> getUserCollectPostList(String userId);

    Boolean isCollect(String userId, String postId);
}
