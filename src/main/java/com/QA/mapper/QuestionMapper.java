package com.QA.mapper;

import com.QA.po.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface QuestionMapper {
    List<Question>  getQuestionList(@Param("startNum")int startNum, @Param("perNum")int perNum);

    List<Question> getAllQuestionList();

    void insertQuestion(Question question);

    Question getQuestionById(int id);

    int getQuestionCount();

    void updateReplyNumById(int id);

    void updateStarNumById(int id);

    List<Question>  getQuestionListByUserName(@Param("username") String username, @Param("startNum")int startNum, @Param("perNum")int perNum);

    List<Question>  getStarQuestionListByUserName(@Param("username") String username, @Param("startNum")int startNum, @Param("perNum")int perNum);

    void deleteQuestionById(int id);

    void updateProfilePhoto(@Param("username") String username, @Param("url") String url);
}
