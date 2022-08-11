package com.example.modules.wall.entity.dto;

import java.util.List;

import com.example.modules.wall.entity.po.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Post post;
    private List<String> images;
}
