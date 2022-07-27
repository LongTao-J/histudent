package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPost;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 表白墙帖子表 服务类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
public interface WallPostService extends IService<WallPost> {
    /**
     * 根据编号获取帖子
     * @param id
     * @return
     */
    public WallPost selectWallPostById(String id);

    /**
     * 通过用户编号批量获取帖子
     * @param userId
     * @return
     */
    public List<WallPost> selectWallPostListByUserId(String userId);

    /**
     * 通过标题批量获取帖子
     * @param title
     * @return
     */
    public List<WallPost> selectWallPostListByTitle(String title);

    /**
     * 根据编号删除帖子
     * @param id
     */
    public int deleteWallPostById(String id);

    /**
     * 创建贴子
     * @param wallPost
     */
    public int insertWallPost(WallPost wallPost);
}
