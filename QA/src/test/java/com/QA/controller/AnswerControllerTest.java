package com.QA.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml","classpath:com/QA/mapper/*.xml",
        "classpath:spring/applicationContext-service.xml","classpath:spring/springmvc.xml"})
public class AnswerControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private AnswerController controller;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Ignore
    @Test
    public void getAnswer() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/answer/1")

        ).andExpect(view().name("answer"));

    }

    //@Ignore
    @Test
    public void answer() throws Exception {
        int id = 11;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/answer")
                .param("questionId","5").param("detail","<p>水一水</p>")
                .sessionAttr("loginUser",id)
        );
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);
    }

}