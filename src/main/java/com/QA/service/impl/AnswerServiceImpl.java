package com.QA.service.impl;

import com.QA.mapper.AnswerMapper;
import com.QA.po.Answer;
import com.QA.service.AnswerService;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class AnswerServiceImpl implements AnswerService{

    @Autowired
    AnswerMapper answerMapper;
    public void setAnswerMapper(AnswerMapper answerMapper){
        this.answerMapper = answerMapper;
    }

    @Cacheable(value = "answerCache", key="#startNum")
    public List<Answer> findAnswerListByQueId(int id, int startNum, int perNum) {
        System.out.println("===========answer==>>>>");
        List<Answer> list = answerMapper.getAnswerListByQueId(id, startNum, perNum);

        return list;
    }

    public int findQueAnswerCount(int id) {
        int count = answerMapper.getQueAnswerCount(id);
        return count;
    }

    @CacheEvict(value="answerCache",allEntries=true)
    public void saveAnswer(Answer answer) {
        answerMapper.insertAnswer(answer);
    }

    public List<Answer> findAnswerListByUserName(String username, int startNum, int perNum) {
        List<Answer> list = answerMapper.getAnswerListByUserName(username,startNum,perNum);
        return list;
    }

    public void dropAnswerById(int id) {
        answerMapper.deleteAnswerById(id);
    }

    @CacheEvict(value="answerCache",allEntries=true)
    public void saveProfilePhoto(String username, String url) {
        answerMapper.updateProfilePhoto(username,url);
    }
}
