package com.example.modules.market.entity.vo;

import com.example.modules.market.entity.po.CommodityComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private CommodityCommentVVo comment;

    private String userHead;

    private String userNickname;

}
