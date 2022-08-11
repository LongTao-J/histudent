package com.example.modules.bbs.entity.dto;

import java.util.List;

import com.example.modules.bbs.entity.po.Post;
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
