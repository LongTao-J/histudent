package com.example.modules.walls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.service.WallPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙帖子表 服务实现类
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@Service
public class WallPostServiceImpl extends ServiceImpl<WallPostMapper, WallPost> implements WallPostService {
    @Autowired
    WallPostMapper wallPostMapper;

    @Override
    public WallPost selectWallPostById(String id) {
        return wallPostMapper.selectById(id);
    }

    @Override
    public List<WallPost> selectWallPostListByUserId(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        return wallPostMapper.selectByMap(map);
    }

    @Override
    public List<WallPost> selectWallPostListByTitle(String title) {
        QueryWrapper<WallPost> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        return wallPostMapper.selectList(wrapper);
    }

    @Override
    public int deleteWallPostById(String id) {
        return wallPostMapper.deleteById(id);
    }

    @Override
    public int insertWallPost(WallPost wallPost) {
        return wallPostMapper.insert(wallPost);
    }
}
