package com.example.modules.bbs.service;


import com.example.modules.bbs.entity.po.Post;

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
}
