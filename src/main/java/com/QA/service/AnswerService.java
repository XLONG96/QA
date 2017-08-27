package com.QA.service;

import com.QA.po.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAnswerListByQueId(int id, int startNum, int perNum);

    int findQueAnswerCount(int id);

    void saveAnswer(Answer answer);

    List<Answer> findAnswerListByUserName(String username, int startNum, int perNum);

    void dropAnswerById(int id);

    void saveProfilePhoto(String username, String url);
}
