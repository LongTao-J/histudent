package com.example.modules.walls.service.impl;

import com.example.modules.walls.mapper.WallPostWithUserMapper;
import com.example.modules.walls.model.WallPostWithUser;
import com.example.modules.walls.service.WallPostWithUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
@Service
public class WallPostWithUserServiceImpl implements WallPostWithUserService {

    @Autowired
    WallPostWithUserMapper wallPostWithUserMapper;

    @Override
    public List<WallPostWithUser> selectWallPostWithUsersByRecommended() {
        return wallPostWithUserMapper.selectWallPostWithUsersByRecommended();
    }
}
