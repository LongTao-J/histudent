package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPostComments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 表白墙帖子评论表 服务类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
public interface WallPostCommentsService extends IService<WallPostComments> {

    int insertWallPostComments(String userId, String wallPostId, String context);

    int deleteWallPostComment(String wall_post_comments_id);

    List<WallPostComments> selectWallPostCommentsAllByWallPostId(String wall_post_id);
}
