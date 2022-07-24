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
@TableName("school")
public class School {
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    @Version
    private Integer version;

    public School(String name) {
        this.name = name;
    }
    public School(String id,String name) {
        this.id = id;
        this.name = name;
    }
}
