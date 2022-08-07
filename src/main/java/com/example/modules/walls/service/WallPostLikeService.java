package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPostLike;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.*;

/**
 * <p>
 * 表白墙帖子喜欢表 服务类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
public interface WallPostLikeService extends IService<WallPostLike> {

    /**
     * 根据用户编号获取喜欢列表
     * @param userId
     * @return
     */
    public List<WallPostLike> selectWallPostLikeByUserId(String userId);

    /**
     *
     * @param wallPostId
     * @return
     */
    public List<WallPostLike> selectWallPostLikeByWallPostId(String wallPostId);

    /**
     *
     * @param userId
     * @param wallPostId
     * @return
     */
    public int insertWallPostLike(String userId, String wallPostId);


    /**
     *
     * @param userId
     * @param wallPostId
     * @return
     */
    public int deleteWallPostLike(String userId, String wallPostId);

    Boolean selectIsLike(String userId, String wallPostId);
}
