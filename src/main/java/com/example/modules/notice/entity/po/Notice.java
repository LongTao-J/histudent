package com.example.modules.notice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String title;

    private String name;

    private String content;

    private String headimg;

    private String img;
}
