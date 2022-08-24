package com.example.modules.market.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WantStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;
}
