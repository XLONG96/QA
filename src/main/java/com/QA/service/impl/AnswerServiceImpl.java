package com.QA.service.impl;

import com.QA.mapper.AnswerMapper;
import com.QA.po.Answer;
import com.QA.po.Paging;
import com.QA.service.AnswerService;
import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public class AnswerServiceImpl implements AnswerService{

    @Autowired
    AnswerMapper answerMapper;
    public void setAnswerMapper(AnswerMapper answerMapper){
        this.answerMapper = answerMapper;
    }

    @Cacheable(cacheName = "answerListCache")
    public List<Answer> findAnswerListByQueId(int id, int startNum, int perNum) {
        System.out.println("===========test==>>>>");
        List<Answer> list = answerMapper.getAnswerList(id, startNum, perNum);

        return list;
    }

    public int findQueAnswerCount(int id) {
        int count = answerMapper.getQueAnswerCount(id);
        return count;
    }

    @CachePut("answerListCache")
    public void saveAnswer(Answer answer) {
        answerMapper.insertAnswer(answer);
    }

    public List<Answer> findAnswerListByUserId(int id, int startNum, int perNum) {
        return null;
    }

    public void dropAnswerById(int id) {

    }
}
