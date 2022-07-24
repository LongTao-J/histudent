package com.example.modules.collegeInformation.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("profession")
public class Profession {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "dep_id")
    private String dep_id;
    @JSONField(name = "name")
    private String name;
    @Version
    @JSONField(serialize = false)
    private Integer version;

    public Profession(String name) {
        this.name = name;
    }

    public Profession(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Profession(String id, String dep_id, String name) {
        this.id = id;
        this.dep_id = dep_id;
        this.name = name;
    }
}
