package com.QA.mapper;

import com.QA.po.User;
import com.QA.po.UserPermission;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/7/15.
 */
public interface UserMapper {

    User getUserByName(String username);

    void insertUserPermission(UserPermission userPermission);

    void insertUser(User user);

    User getUserById(int id);

    void updateProfilePhoto(@Param("id") int id, @Param("url") String url);

    void updateFansNumById(int id);

    void updateQuestionNumById(int id);

    void updateAnswerNumById(int id);

    void updateStarNumById(int id);

    void updateCommentNumById(int id);
}
