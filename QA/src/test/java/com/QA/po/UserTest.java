package com.QA.po;

import org.junit.Test;

/**
 * Created by Administrator on 2017/7/14.
 */
public class UserTest {
    @Test
    public void toStringTest(){
        User user = new User("xlong","123");
        for (int i = 0; i < 5; i++) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void getNameTest(){
        User user = new User("xlong2","123");
        System.out.println(user.getUsername());
    }
}