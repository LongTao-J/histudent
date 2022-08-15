package com.example.modules.wall.entity.vo;

import com.example.modules.wall.entity.po.PostComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentVO {
    PostComment comment;
    String userHead;
    String userNickname;
}
