package com.android.pcs.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CardVO {
    /** card id*/
    private Integer cardId;

    /** 打卡时间*/
    private Date createTime;

    /** 打卡内容*/
    private String content;

    /** 感受*/
    private String feel;

    /** 打卡地址*/
    private String address;

    /** 打卡分类*/
    private String categoryName;

    /** 用户名*/
    private String username;
}
