package com.example.modules.wall.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mushan
 * @date 7/23/2022
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallPost {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private Long userId;
    private String title;
    private String content;
    private Integer likeCount;
    @Version
    private Boolean version;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
