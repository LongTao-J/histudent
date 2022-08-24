package com.example.modules.market.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private String commentId;

    private String comment;

    private String userId;

    private String userName;

    private String headImg;
}
