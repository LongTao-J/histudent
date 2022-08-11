package com.example.modules.bbs.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostFileDTO {
    String postId;
    List<String> files;
}
