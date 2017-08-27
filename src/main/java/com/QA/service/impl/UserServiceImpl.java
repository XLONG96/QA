package com.QA.service.impl;

import com.QA.mapper.UserMapper;
import com.QA.po.User;
import com.QA.po.UserPermission;
import com.QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public class UserServiceImpl implements UserService{
    @Autowired
    public UserMapper userMapper;

    @Cacheable(value="userCache")
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

    @Cacheable(value="userCache",key="#id")
    public User findUserById(int id) {
        System.out.println("====>>user>>>===");
        User user = userMapper.getUserById(id);
        return user;
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void saveProfilePhoto(int id, String url) {
        userMapper.updateProfilePhoto(id,url);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void addFansNumById(int id) {
        userMapper.updateFansNumById(id);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void addQuestionNumById(int id) {
        userMapper.updateQuestionNumById(id);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void addAnswerNumById(int id) {
        userMapper.updateAnswerNumById(id);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void addStarNumById(int id) {
        userMapper.updateStarNumById(id);
    }

    @CacheEvict(value="userCache",allEntries=true)
    public void addCommentNumById(int id) {
        userMapper.updateCommentNumById(id);
    }
}
