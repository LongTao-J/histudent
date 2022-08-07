package com.example.modules.walls.service.impl;

import com.example.modules.walls.model.WallPostComments;
import com.example.modules.walls.mapper.WallPostCommentsMapper;
import com.example.modules.walls.service.WallPostCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙帖子评论表 服务实现类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@Service
public class WallPostCommentsServiceImpl extends ServiceImpl<WallPostCommentsMapper, WallPostComments> implements WallPostCommentsService {
    @Autowired
    WallPostCommentsMapper wallPostCommentsMapper;

    @Override
    public int insertWallPostComments(String userId, String wallPostId, String context) {
        WallPostComments wallPostComments = new WallPostComments();
        wallPostComments.setWallPostId(wallPostId);
        wallPostComments.setUserId(userId);
        wallPostComments.setContent(context);
        return wallPostCommentsMapper.insert(wallPostComments);
    }

    @Override
    public int deleteWallPostComment(String wall_post_comments_id) {
        return wallPostCommentsMapper.deleteById(wall_post_comments_id);
    }

    @Override
    public List<WallPostComments> selectWallPostCommentsAllByWallPostId(String wall_post_id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("wall_post_id", wall_post_id);
        return wallPostCommentsMapper.selectByMap(map);
    }
}
