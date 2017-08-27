package com.QA.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 */
public class Answer implements Serializable{
    private int id;
    private String detail;
    private String authorName;
    private String authorHeadImg;
    private int questionId;
    private int agreeNum;
    private int disagreeNum;
    private int starNum;
    private int commentNum;
    private Date answerTime;

    public Answer() {
    }

    public Answer(String detail, String authorName, String authorHeadImg, int questionId, Date answerTime) {
        this.detail = detail;
        this.authorName = authorName;
        this.authorHeadImg = authorHeadImg;
        this.questionId = questionId;
        this.answerTime = answerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    public int getDisagreeNum() {
        return disagreeNum;
    }

    public void setDisagreeNum(int disagreeNum) {
        this.disagreeNum = disagreeNum;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }
}
