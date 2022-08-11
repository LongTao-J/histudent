package com.example.modules.bbs.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    // 用户编号
    private String userId;
    // 标题
    private String title;
    // 内容
    private String content;
    // 点赞数量
    private Integer likeCount;
    // 评论数量
    private Integer commentsCount;
    // 是否推荐
    private Boolean isRec;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
