package com.android.pcs.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    INNER_ERROR(100, "系统内部错误"),
    PUNCH_ERROR(101, "打卡失败"),
    USERNMAE_OR_PASSWORD_ERROR(102, "用户名或密码错误"),
    USERNAME_IS_EXIST(103, "用户名已存在"),
    ;

    private int code;

    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

