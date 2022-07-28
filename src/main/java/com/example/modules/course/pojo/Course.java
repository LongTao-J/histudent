package com.example.modules.course.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_course_scheduling")
public class Course {
    @JsonProperty(value = "id")
    @TableId(value="id",type = IdType.ASSIGN_UUID)
    private String id;

    @JsonProperty(value = "userId")
    @TableField("user_id")
    private String userId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "courseType")
    @TableField("course_type")
    private String courseType;

    @JsonProperty(value = "isExam")
    @TableField("is_exam")
    private String isExam;

    @JsonProperty(value = "classroom")
    private String classroom;

    @JsonProperty(value = "weekly")
    private String weekly;

    @JsonProperty(value = "period")
    private String period;

    @TableField("week_num")
    @JsonProperty(value = "weekNum")
    private String weekNum;

    public Course(String userId, String name, String courseType, String isExam, String classroom, String weekly, String period, String weekNum) {
        this.userId = userId;
        this.name = name;
        this.courseType = courseType;
        this.isExam = isExam;
        this.classroom = classroom;
        this.weekly = weekly;
        this.period = period;
        this.weekNum = weekNum;
    }
}
