package com.qin_kai.punchcard.model;

import java.util.Date;

/**
 * 用户打开信息
 */
public class Card {

    private Integer cardId;

    /** 打卡时间*/
    private Date createTime;

    /** 打卡内容*/
    private String content;

    /** 打卡感想*/
    private String feel;

    /** 打卡地址*/
    private String address;

    /** 打卡分类*/
    private Integer categoryId;

    /** 用户id*/
    private Integer userId;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", feel='" + feel + '\'' +
                ", address='" + address + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }
}
