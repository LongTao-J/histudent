package com.example.modules.wall.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    //收藏编号
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    //帖子编号
    private String postId;

    //用户编号
    private String userId;


}
