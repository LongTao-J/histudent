package com.example.modules.collegeInformation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import java.util.UUID;

public class Departments {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private UUID id;
    private UUID dep_id;
    private String name;
    @Version
    private Integer version;

    public Departments(String name) {
        this.name = name;
    }
}
