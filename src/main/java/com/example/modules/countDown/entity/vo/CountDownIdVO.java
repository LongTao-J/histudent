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

    private Date remindtime;
}
