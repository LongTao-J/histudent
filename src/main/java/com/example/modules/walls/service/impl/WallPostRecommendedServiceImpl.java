package com.example.modules.walls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.walls.mapper.WallPostMapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostRecommended;
import com.example.modules.walls.mapper.WallPostRecommendedMapper;
import com.example.modules.walls.service.WallPostCommentsService;
import com.example.modules.walls.service.WallPostRecommendedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.walls.service.WallPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙帖子推荐表 服务实现类
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
@Service
public class WallPostRecommendedServiceImpl extends ServiceImpl<WallPostRecommendedMapper, WallPostRecommended> implements WallPostRecommendedService {
    @Autowired
    WallPostService wallPostServiceImpl;
    @Autowired
    WallPostRecommendedMapper wallPostRecommendedMapper;

    @Override
    public int deleteRecommendedById(String id) {
        return wallPostRecommendedMapper.deleteById(id);
    }

    @Override
    public List<WallPost> getAllRecommendedPost() {
        List<WallPostRecommended> wallPostRecommendeds = wallPostRecommendedMapper.selectByMap(new HashMap<>());
        List<WallPost> wallPosts = new ArrayList<>();
        for(WallPostRecommended it : wallPostRecommendeds){
            wallPosts.add(wallPostServiceImpl.selectWallPostById(it.getWallPostId()));
        }
        return wallPosts;
    }

    @Override
    public int insert(WallPostRecommended wallPostRecommended) {
        return wallPostRecommendedMapper.insert(wallPostRecommended);
    }
}
