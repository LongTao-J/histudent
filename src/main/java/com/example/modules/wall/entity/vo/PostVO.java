package com.example.modules.wall.entity.vo;

import com.example.modules.wall.entity.po.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVO {
    Post post;
    List<String> images;
    Boolean userIsLike;
    Boolean userIsCollect;
    String userHead;
    String userNickname;
}
