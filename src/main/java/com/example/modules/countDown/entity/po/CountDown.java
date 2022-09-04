package com.example.modules.countDown.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDown {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String content;

    private String address;

    private String remindtime;

    private String userId;

    private String subjectTime;
}
