package com.QA.service.impl;

import com.QA.po.User;
import com.QA.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml",
        "classpath:com/QA/mapper/*.xml","classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-transaction.xml"})
public class UserServiceImplTest {

    @Autowired
    public UserService userService;

    @Ignore
    @Test
    public void findUserByName() throws Exception {
        User user = userService.findUserByName("mike");
        System.out.println(user);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("22","123");
        userService.addUser(user);
    }
}