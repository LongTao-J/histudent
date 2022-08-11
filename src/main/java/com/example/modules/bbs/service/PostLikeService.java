package com.example.modules.bbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.modules.bbs.entity.po.PostLike;


import java.util.List;

public interface PostLikeService {
    /**
     * 保存点赞记录
     * @param postLike
     * @return
     */
    Boolean save(PostLike postLike);

    /**
     * 批量保存或修改
     * @param list
     */
    Boolean saveList(List<PostLike> list);

    /**
     * 根据postId查询点赞列表（即查询都谁给这帖子点赞过）
     * @param postId 被点赞帖子的id
     * @param page
     * @return
     */
    List<PostLike> getLikeListByPostId(String postId, IPage<PostLike> page);

    /**
     * 根据点赞人的userId查询点赞列表（即查询这个人都给那些帖子赞过）
     * @param userId
     * @param page
     * @return
     */
    List<PostLike> getLikeListByUserId(String userId, IPage<PostLike> page);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param userId
     * @param postId
     * @return
     */
    PostLike getLikeByUserIdAndPostId(String userId, String postId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis0DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis0DB();
}
