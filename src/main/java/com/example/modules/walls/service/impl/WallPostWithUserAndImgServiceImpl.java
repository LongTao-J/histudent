package com.example.modules.walls.service.impl;

import com.example.modules.walls.mapper.WallPostWithUserAndImgMapper;
import com.example.modules.walls.model.WallPostWithUserAndImg;
import com.example.modules.walls.service.WallPostWithUserAndImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
@Service
public class WallPostWithUserAndImgServiceImpl implements WallPostWithUserAndImgService {

    @Autowired
    WallPostWithUserAndImgMapper wallPostWithUserAndImgMapper;

    @Override
    public List<WallPostWithUserAndImg> selectWallPostWithUsersByRecommended() {
        return wallPostWithUserAndImgMapper.selectWallPostWithUsersByRecommended();
    }
}
