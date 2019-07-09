package com.android.pcs.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户打开信息
 */
@Data
public class Card {

    private Integer cardId;

    /** 打卡时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 打卡内容*/
    private String content;

    /** 感受*/
    private String feel;

    /** 打卡地址*/
    private String address;

    /** 打卡分类*/
    private Integer categoryId;

    /** 用户id*/
    private Integer userId;

}
