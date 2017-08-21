package com.QA.service.impl;

import com.QA.mapper.UserMapper;
import com.QA.po.User;
import com.QA.po.UserPermission;
import com.QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public class UserServiceImpl implements UserService{
    @Autowired
    public UserMapper userMapper;

    public User findUserByName(String username){
        User user = userMapper.getUserByName(username);
        return user;
    }

    public List<UserPermission> findPermissionListByUserId(int id){
        return null;
    }

    public void addUser(User user){
        userMapper.insertUser(user);
        //初始化用户默认权限
        UserPermission userPermission = new UserPermission();
        int userId = userMapper.getUserByName(user.getUsername()).getId();
        userPermission.setUserId(userId);
        userPermission.setPermission("user:default");
        userMapper.insertUserPermission(userPermission);
    }

    public User findUserById(int id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    public void saveProfilePicture(int id, String url) {

    }

    public void addFansNumById(int id) {

    }

    public void addQuestionNumById(int id) {

    }

    public void addAnswerNumById(int id) {

    }

    public void addStarNumById(int id) {

    }

    public void addCommentNumById(int id) {

    }
}
