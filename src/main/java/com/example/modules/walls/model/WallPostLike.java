package com.example.modules.walls.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 表白墙帖子喜欢表
 * </p>
 *
 * @author mushan
 * @since 2022-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WallPostLike implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 喜欢编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 帖子编号
     */
    @TableField(value = "wall_post_id")
    private String wallPostId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;


}
