package com.QA.mapper;

import com.QA.po.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface AnswerMapper {
    List<Answer> getAnswerListByQueId(@Param("id") int id, @Param("startNum") int startNum, @Param("perNum") int perNum);

    int getQueAnswerCount(int id);

    void insertAnswer(Answer answer);

    List<Answer> getAnswerListByUserName(@Param("username") String username, @Param("startNum") int startNum,@Param("perNum") int perNum);

    void deleteAnswerById(int id);

    void updateProfilePhoto(@Param("username") String username, @Param("url") String url);
}
