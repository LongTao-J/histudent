package com.example.modules.walls.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 表白墙帖子表
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WallPost implements Serializable {

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
     * HeadImg
     */
    private String headImg;

    /**
     * 乐观锁
     */
    @Version
    @JSONField(name = "version")
    private Integer version;

    /**
     * 创建时间
     */
    @JSONField(name = "gmt_create")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JSONField(name = "gmt_modified")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
