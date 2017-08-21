package com.QA.mapper;

import com.QA.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface CommentMapper {
    List<Comment> getCommnetListByUserId(@Param("id") int id, @Param("startNum") int startNum, @Param("perNum") int perNum);

    List<Comment> getCommentListByQusId(@Param("id") int id, @Param("startNum") int startNum, @Param("perNum") int perNum);

    void insertComment(Comment comment);

    void deleteCommentById(int id);
}
