package com.example.modules.wall.service;

import com.example.modules.wall.entity.dto.PostFileDTO;

public interface PostFileService {
    PostFileDTO getPostFile(String postId);

    void insert(String postId, String url);

    void deleteFileDate(String postId);
}
