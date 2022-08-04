package com.example.modules.walls.service.impl;

import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostLike;
import com.example.modules.walls.mapper.WallPostLikeMapper;
import com.example.modules.walls.service.WallPostLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.walls.service.WallPostService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    WallPostMapper wallPostMapper;

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
    @Transactional
    public int insertWallPostLike(String userId, String wallPostId) {
        WallPostLike wallPostLike = new WallPostLike();
        wallPostLike.setWallPostId(wallPostId);
        wallPostLike.setUserId(userId);
        int code = wallPostLikeMapper.insert(wallPostLike);
        if(code == 0) return 0;
        else{
            WallPost wallPost = wallPostMapper.selectById(wallPostId);
            wallPost.setLikeCount(wallPost.getLikeCount() + 1);
            return wallPostMapper.updateById(wallPost);
        }
    }

    @Override
    @Transactional
    public int deleteWallPostLike(String userId, String wallPostId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("wall_post_id", wallPostId);
        int code = wallPostLikeMapper.deleteByMap(map);
        if(code == 0) return 0;
        else{
            WallPost wallPost = wallPostMapper.selectById(wallPostId);
            wallPost.setLikeCount(wallPost.getLikeCount() - 1);
            return wallPostMapper.updateById(wallPost);
        }
    }


}
