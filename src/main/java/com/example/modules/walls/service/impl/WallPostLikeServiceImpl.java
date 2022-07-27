package com.example.modules.walls.service.impl;

import com.example.modules.user.pojo.User;
import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.mapper.WallPostLikeMapper;
import com.example.modules.walls.service.WallPostLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙帖子喜欢表 服务实现类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@Service
public class WallPostLikeServiceImpl extends ServiceImpl<WallPostLikeMapper, WallPostLike> implements WallPostLikeService {
    @Autowired
    WallPostLikeMapper wallPostLikeMapper;

    @Override
    public List<WallPostLike> selectWallPostLikeByUserId(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        return wallPostLikeMapper.selectByMap(map);
    }

    @Override
    public List<WallPostLike> selectWallPostLikeByWallPostId(String wallPostId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("wall_post_id", wallPostId);
        return wallPostLikeMapper.selectByMap(map);
    }

    @Override
    public int insertWallPostLike(WallPostLike wallPostLike) {
        return wallPostLikeMapper.insert(wallPostLike);
    }

    @Override
    public int deleteWallPostLikeById(WallPostLike wallPostLike) {
        return wallPostLikeMapper.deleteById(wallPostLike.getId());
    }

    @Override
    public int deleteWallPostLikeByUserIdAndWallPostId(WallPostLike wallPostLike) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", wallPostLike.getUserId());
        map.put("wallPostId", wallPostLike.getWallPostId());
        return wallPostLikeMapper.deleteByMap(map);
    }


}
