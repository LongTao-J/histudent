package com.example.modules.course.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_course_scheduling")
public class Course {
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("user_id")
    private String userId;

    private String name;

    @TableField("course_type")
    private String courseType;

    @TableField("is_exam")
    private String isExam;

    private String classroom;

    private String weekly;

    private String row;

    private String col;
}
