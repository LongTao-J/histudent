package com.example.modules.walls.service;

import com.example.modules.walls.model.WallPostFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 表白墙文件表 服务类
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
public interface WallPostFileService extends IService<WallPostFile> {

    void insertImgList(String wallPostId, List<String> images);

    void deleteFilesByPostId(String postId);

    List<WallPostFile> selectListByPostId(String postId);
}
