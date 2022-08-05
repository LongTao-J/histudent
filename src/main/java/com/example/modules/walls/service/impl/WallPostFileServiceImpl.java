package com.example.modules.walls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.modules.walls.model.WallPost;
import com.example.modules.walls.model.WallPostFile;
import com.example.modules.walls.mapper.WallPostFileMapper;
import com.example.modules.walls.service.WallPostFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 表白墙文件表 服务实现类
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
@Service
public class WallPostFileServiceImpl extends ServiceImpl<WallPostFileMapper, WallPostFile> implements WallPostFileService {

    @Autowired
    WallPostFileMapper wallPostFileMapper;

    @Override
    @Transactional
    public void insertImgList(String wallPostId, List<String> images) {
        try {
            WallPostFile wallPostFile = new WallPostFile();
            for(String it : images){
                wallPostFile.setWallPostId(wallPostId);
                wallPostFile.setUrl(it);
                wallPostFileMapper.insert(wallPostFile);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void deleteFilesByPostId(String postId) {
        try {
            QueryWrapper<WallPostFile> wrapper = new QueryWrapper<>();
            wrapper.eq("wall_post_id", postId);
            wallPostFileMapper.delete(wrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<WallPostFile> selectListByPostId(String postId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("post_id", postId);
        return wallPostFileMapper.selectByMap(map);
    }
}
