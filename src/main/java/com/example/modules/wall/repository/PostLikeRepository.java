package com.example.modules.wall.repository;

public interface PostLikeRepository {
    /**
     * 点赞
     * @param userId
     * @param postId
     */
    void savelike(String userId, String postId);

    /**
     * 取消点赞
     * @param userId
     * @param postId
     */
    void unlike(String userId, String postId);

    /**
     * 是否点赞
     * @param userId
     * @param postId
     * @return
     */
    Integer isLike(String userId, String postId);

    /**
     * 获取点赞数量
     * @param postId
     * @return
     */
    Integer getLikeCount(String postId);


}
