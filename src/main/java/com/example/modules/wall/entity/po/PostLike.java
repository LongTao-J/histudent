package com.example.modules.wall.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.modules.wall.enums.LikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {
    //主键id
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    //点赞的用户的id
    private String userId;

    //被点赞的帖子的id
    private String postId;

    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public PostLike(String userId, String postId, Integer status) {
        this.userId = userId;
        this.postId = postId;
        this.status = status;
    }
}
