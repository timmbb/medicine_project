package com.example.springproje.enums;

import lombok.Getter;

@Getter
public enum LikeStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "未点赞/取消点赞");

    private final Integer code;
    private final String msg;

    LikeStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

