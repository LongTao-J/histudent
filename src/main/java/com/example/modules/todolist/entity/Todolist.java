package com.example.modules.todolist.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todolist  {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String userId;
    private String title;
    private boolean completed;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
