package com.QA.service.impl;

import com.QA.mapper.QuestionMapper;
import com.QA.po.Question;
import com.QA.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionMapper questionMapper;

    @Cacheable(value = "questionCache",key="#startNum")
    public List<Question> findQuestionList(int startNum, int perNum) {
        System.out.println("====>>question>>==");
        List<Question> questionList = questionMapper.getQuestionList(startNum,perNum);
        return questionList;
    }

    public List<Question> findAllQuestionList(){
        return questionMapper.getAllQuestionList();
    }

    @CacheEvict(value="questionCache",allEntries=true)
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
        questionMapper.updateReplyNumById(id);
    }

    public void addStarNumById(int id) {
        questionMapper.updateStarNumById(id);
    }

    public List<Question> findQuestionListByUserName(String username, int startNum, int perNum) {
        List<Question> list = questionMapper.getQuestionListByUserName(username,startNum,perNum);
        return list;
    }

    public List<Question> findStarQuestionListByUserName(String username, int startNum, int perNum) {
        List<Question> list = questionMapper.getStarQuestionListByUserName(username,startNum,perNum);
        return list;
    }

    public void dropQuestionById(int id) {
        questionMapper.deleteQuestionById(id);
    }

    @CacheEvict(value="questionCache",allEntries=true)
    public void saveProfilePhoto(String username, String url) {
        questionMapper.updateProfilePhoto(username,url);
    }

}
