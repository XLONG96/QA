package com.QA.po;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/18.
 */
public class Comment implements Serializable{
    private int id;
    private String detail;
    private int answererId;
    private int respondentId;
    private int replyId;
    private int agreeNum;
    private Date commentTime;

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

    public int getAnswererId() {
        return answererId;
    }

    public void setAnswererId(int answererId) {
        this.answererId = answererId;
    }

    public int getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(int respondentId) {
        this.respondentId = respondentId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(int agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}
