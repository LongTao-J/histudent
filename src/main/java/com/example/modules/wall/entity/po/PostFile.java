package com.example.modules.wall.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostFile {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    // 帖子编号
    private String postId;
    // 图片地址
    private String url;
}
