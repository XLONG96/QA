package com.QA.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 */
public class Question implements Serializable{
    private int id;
    private String title;
    private String detail;
    private String authorName;
    private String authorHeadImg;
    private int replyNum;
    private int starNum;
    private Date publicTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorHeadImg() {
        return authorHeadImg;
    }

    public void setAuthorHeadImg(String authorHeadImg) {
        this.authorHeadImg = authorHeadImg;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }
}
