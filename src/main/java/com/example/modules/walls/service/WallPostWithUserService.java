package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPostWithUser;

import java.util.List;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
public interface WallPostWithUserService {
    List<WallPostWithUser> selectWallPostWithUsersByRecommended();
}
