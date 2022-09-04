package com.example.modules.countDown.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDownIdVO {
    private String id;

    private String content;

    private String address;

    private String remindtime1;//某一天

    private String remindtime2;//周几 + 几点

    private String remindtime3;//剩几天

    private String subjectTime;
}
