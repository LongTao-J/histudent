package com.example.modules.wall.service.impl;

import com.example.modules.wall.entity.dto.PostFileDTO;
import com.example.modules.wall.entity.po.PostFile;
import com.example.modules.wall.mapper.PostFileMapper;
import com.example.modules.wall.service.PostFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostFileServiceImpl implements PostFileService {
    @Autowired
    PostFileMapper postFileMapper;

    @Override
    public PostFileDTO getPostFile(String postId) {
        List<String> postFiles = postFileMapper.selectFileAllByPostId(postId);
        PostFileDTO postFileDTO = new PostFileDTO();
        postFileDTO.setFiles(postFiles);
        return postFileDTO;
    }

    @Override
    public void insert(String postId, String url) {
        PostFile postFile = new PostFile();
        postFile.setPostId(postId);
        postFile.setUrl(url);
        postFileMapper.insert(postFile);
    }
}
