package com.QA.service;

import com.QA.po.User;
import com.QA.po.UserPermission;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface UserService {
     User findUserByName(String username);

     List<UserPermission> findPermissionListByUserId(int id);

     void addUser(User user);

     User findUserById(int id);

     void saveProfilePhoto(int id, String url);

     //void dropUserById(int id);

     void addFansNumById(int id);

     void addQuestionNumById(int id);

     void addAnswerNumById(int id);

     void addStarNumById(int id);

     void addCommentNumById(int id);
}
