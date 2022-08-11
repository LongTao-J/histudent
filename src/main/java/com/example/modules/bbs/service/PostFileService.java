package com.example.modules.bbs.service;

import com.example.modules.bbs.entity.dto.PostFileDTO;

public interface PostFileService {
    PostFileDTO getPostFile(String postId);

    void insert(String postId, String url);
}
