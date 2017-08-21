package com.QA.controller;

import com.QA.po.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml","classpath:com/QA/mapper/*.xml",
        "classpath:spring/applicationContext-service.xml","classpath:spring/springmvc.xml"
        })
public class QuizControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private  QuizController controller;
    private User user = new User();

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        user.setProfilePhoto("http://temp.jpg");
        user.setUsername("mike");
    }

    @Test
    public void quiz() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/quiz.do")
                .param("title","who is?").param("detail","测试测试")
                .sessionAttr("loginUser",user)
        ).andExpect(view().name("quiz"));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);
    }

}