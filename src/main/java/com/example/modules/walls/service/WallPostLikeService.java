package com.example.modules.walls.service;

import com.example.modules.user.pojo.User;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostComments;
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
     * 根据帖子编号获取喜欢列表
     * @param wallPostId
     * @return
     */
    public List<WallPostLike> selectWallPostLikeByWallPostId(String wallPostId);

    /**
     * 插入新的喜欢
     * @param wallPostLike
     */
    public int insertWallPostLike(WallPostLike wallPostLike);

    /**
     * 取消喜欢通过编号
     * @param wallPostLike
     */
    public int deleteWallPostLikeById(WallPostLike wallPostLike);

    /**
     * 取消喜欢通过用户编号和帖子编号
     * @param wallPostLike
     */
    public int deleteWallPostLikeByUserIdAndWallPostId(WallPostLike wallPostLike);
}
