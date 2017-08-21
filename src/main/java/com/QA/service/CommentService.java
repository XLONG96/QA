package com.QA.service;

import com.QA.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommnetListByUserId(int id, int startNum, int perNum);

    List<Comment> findCommentListByQusId(int id, int startNum, int perNum);

    void saveComment(Comment comment);

    void dropCommentById(int id);
}
