package com.example.modules.wall.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mushan
 * @date 7/24/2022
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallPostLike {
    private String userId;
    private String wallPostId;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
}
