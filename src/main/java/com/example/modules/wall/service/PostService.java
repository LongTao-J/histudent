package com.example.modules.wall.service;


import com.example.modules.wall.entity.po.Post;

import java.util.List;

public interface PostService {
    /**
     * 查找帖子通过id
     * @param id
     * @return
     */
    Post getPostById(String id);

    /**
     *  修改帖子通过id
     * @param post
     * @return
     */
    Boolean updatePostById(Post post);

    /**
     * 查找推荐列表
     * @return
     */
    List<Post> getPostListRec();

    /**
     * 创建帖子
     * @param post 创建者用户编号
     * @return postId
     */
    String createPost(Post post);

    /**
     * 通过用户id获取用户发布的所有帖子
     * @param userId
     * @return
     */
    List<Post> getPostListUserIssued(String userId);

    /**
     * 连表删除帖子
     * @param postId
     */
    void deletePost(String postId);
}
