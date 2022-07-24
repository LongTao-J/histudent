package com.example.modules.wall.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class WallPostComments {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String userId;
    private String wallPostId;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
}
