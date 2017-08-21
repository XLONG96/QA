package com.QA.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2017/7/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml","classpath:com/QA/mapper/*.xml",
        "classpath:spring/applicationContext-service.xml","classpath:spring/springmvc.xml"})
public class HomeControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private  HomeController controller;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void home() throws Exception {
        /*ResultAction是用来模拟Browser发送FORM表单请求的。post()是请求的地址，accept()请求的内容
            param()请求的键值对，如果有多个参数可以后缀调用多个param()
            MvcResult是获得服务器的Response内容。*/
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/home.do")
                .accept(MediaType.APPLICATION_JSON)

        )
                .andExpect(status().isOk()).andDo(print());
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);
    }

}