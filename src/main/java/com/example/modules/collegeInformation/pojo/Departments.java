package com.example.modules.collegeInformation.pojo;

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
@TableName("departments")
public class Departments {
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String sch_id;
    private String name;
    @Version
    private Integer version;

    public Departments(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Departments(String name) {
        this.name = name;
    }

    public Departments(String id, String sch_id, String name) {
        this.id = id;
        this.sch_id = sch_id;
        this.name = name;
    }
}
