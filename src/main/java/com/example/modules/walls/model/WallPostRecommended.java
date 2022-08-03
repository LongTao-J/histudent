package com.example.modules.walls.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 表白墙帖子推荐表
 * </p>
 *
 * @author mushan
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WallPostRecommended implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表白墙推荐表编号
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 表白墙编号
     */
    private String wallPostId;


}
