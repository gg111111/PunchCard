package com.qin_kai.punchcard.model;


import java.io.Serializable;
import java.util.Date;

public class CardVO implements Serializable {

    /** Id*/
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
    private String categoryName;

    /** 用户名*/
    private String username;


    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "CardVO{" +
                "createTime=" + createTime +
                ", content='" + content + '\'' +
                ", feel='" + feel + '\'' +
                ", address='" + address + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
