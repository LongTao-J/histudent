package com.example.modules.wall.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikedCountDTO {
    private String postId;
    private Integer count;
}
