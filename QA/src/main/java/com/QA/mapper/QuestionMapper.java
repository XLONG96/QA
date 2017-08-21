package com.QA.mapper;

import com.QA.po.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface QuestionMapper {
    List<Question>  getQuestionList(@Param("startNum")int startNum, @Param("perNum")int perNum);

    void insertQuestion(Question question);

    Question getQuestionById(int id);

    int getQuestionCount(int id);

    void updateReplyNumById(int id);

    void updateStarNumById(int id);

    List<Question>  getQuestionListByUserId(@Param("id") int id, @Param("startNum")int startNum, @Param("perNum")int perNum);

    List<Question>  getStarQuestionListByUserId(@Param("id") int id, @Param("startNum")int startNum, @Param("perNum")int perNum);

    void deleteQuestionById(int id);
}
