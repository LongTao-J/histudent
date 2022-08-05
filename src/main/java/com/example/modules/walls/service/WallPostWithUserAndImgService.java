package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPostWithUserAndImg;

import java.util.List;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
public interface WallPostWithUserAndImgService {
    List<WallPostWithUserAndImg> selectWallPostWithUsersByRecommended();
}
