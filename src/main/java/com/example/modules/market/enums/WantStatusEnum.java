package com.example.modules.market.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WantStatusEnum {
    ISLIKE(1, "点赞"),
    ISUNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;
}
