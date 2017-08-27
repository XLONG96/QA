package com.QA.service;

import com.QA.po.Question;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface QuestionService {
    List<Question> findQuestionList(int startNum,int perNum);

    List<Question> findAllQuestionList();

    void saveQuestion(Question question);

    Question findQusetionById(int id);

    int findQuestionCount();

    void addReplyNumById(int id);

    void addStarNumById(int id);

    List<Question> findQuestionListByUserName(String username, int startNum, int perNum);

    List<Question> findStarQuestionListByUserName(String username, int startNum, int perNum);

    void dropQuestionById(int id);

    void saveProfilePhoto(String username, String url);
}
