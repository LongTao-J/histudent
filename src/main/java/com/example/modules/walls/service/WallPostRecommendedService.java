package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostRecommended;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 表白墙帖子推荐表 服务类
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
public interface WallPostRecommendedService extends IService<WallPostRecommended> {

    int deleteRecommendedById(String id);

    List<WallPost> getAllRecommendedPost();

    int insert(WallPostRecommended wallPostRecommended);
}
