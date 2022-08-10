package com.example.modules.walls.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author mushan
 * @date 8/4/2022
 * @apiNote
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WallPostWithUserAndImg {
    private static final long serialVersionUID = 1L;

    /**
     * 帖子编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @JSONField(name = "id")
    private String id;

    /**
     * 用户编号
     */
    @JSONField(name = "user_id")
    private String userId;

    private String headaddress;

    private String nickname;

    /**
     * 标题
     */
    @JSONField(name = "title")
    private String title;

    /**
     * 内容
     */
    @JSONField(name = "content")
    private String content;

    /**
     * HeadImg
     */
    private String headImg;

    /**
     * 喜欢数量
     */
    @JSONField(name = "like_count")
    private Integer likeCount;

    /**
     * 评论数量
     */
    @JSONField(name = "comments_count")
    private Integer commentsCount;

    /**
     * 乐观锁
     */
    @Version
    @JSONField(name = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
