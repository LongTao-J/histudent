package com.example.modules.wall.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostFileDTO {
    String postId;
    List<String> files;
}
