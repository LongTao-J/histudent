package com.example.modules.countDown.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDownDTO {

    private String content;

    private String address;

    private String remindtime;

    private String subjectTime;
}
