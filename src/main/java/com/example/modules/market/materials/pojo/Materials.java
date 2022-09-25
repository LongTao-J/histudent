package com.example.modules.market.materials.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("materials")
public class Materials {
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    private String id;
    private String title;
    private String tag;
    private String name;
    private String url;
    private String icon;
    @TableField("userId")
    private String userId;

    @TableField(value = "createTime",fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    public Materials(String title, String tag, String name, String url, String icon, String userId) {
        this.title = title;
        this.tag = tag;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.userId = userId;
    }
}
