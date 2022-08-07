package com.example.modules.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuInfo {
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    private String id;
    private String stuNum;
    private String stuName;
    private String schId;
    private String depId;
    private String profId;
    private int grade;
    private int myClass;
    @Version
    private Integer version;
}
