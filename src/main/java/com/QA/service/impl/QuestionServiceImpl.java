package com.QA.service.impl;

import com.QA.mapper.QuestionMapper;
import com.QA.po.Question;
import com.QA.service.QuestionService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionMapper questionMapper;

    @Cacheable(cacheName = "questionList")
    public List<Question> findQuestionList(int startNum, int perNum) {
        List<Question> questionList = questionMapper.getQuestionList(startNum,perNum);
        return questionList;
    }

    public void saveQuestion(Question question){
        questionMapper.insertQuestion(question);
    }

    public Question findQusetionById(int id) {
        Question question = questionMapper.getQuestionById(id);
        return question;
    }

    public int findQuestionCount() {
        return questionMapper.getQuestionCount();
    }

    public void addReplyNumById(int id) {

    }

    public void addStarNumById(int id) {

    }

    public List<Question> findQuestionListByUserId(int id, int startNum, int perNum) {
        return null;
    }

    public List<Question> findStarQuestionListByUserId(int id, int startNum, int perNum) {
        return null;
    }

    public void dropQuestionById(int id) {

    }

    public void addReplyNum(int id) {
        questionMapper.updateReplyNumById(id);
    }
}
