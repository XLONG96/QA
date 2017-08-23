package com.QA.service;

import com.QA.po.Question;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface QuestionService {
    List<Question> findQuestionList(int startNum,int perNum);

    void saveQuestion(Question question);

    Question findQusetionById(int id);

    int findQuestionCount();

    void addReplyNumById(int id);

    void addStarNumById(int id);

    List<Question> findQuestionListByUserId(int id, int startNum, int perNum);

    List<Question> findStarQuestionListByUserId(int id, int startNum, int perNum);

    void dropQuestionById(int id);
}
