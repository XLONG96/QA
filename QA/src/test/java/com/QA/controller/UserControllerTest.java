package com.QA.controller;

import com.QA.po.User;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Administrator on 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml","classpath:com/QA/mapper/*.xml",
        "classpath:spring/applicationContext-service.xml","classpath:spring/springmvc.xml",
        "classpath:spring/applicationContext-shiro.xml"})
public class UserControllerTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockMvc mockMvc;

    @Autowired
    private  UserController controller;
    @Autowired
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        ThreadContext.bind(securityManager);
    }

    @Ignore
    @Test
    public void loginByGet() throws Exception {
        this.mockMvc.perform(get("/user/login.do"))
                .andExpect(view().name("login"));
    }

    @Test
    public void login() throws Exception {
        /*ResultAction是用来模拟Browser发送FORM表单请求的。post()是请求的地址，accept()请求的内容
            param()请求的键值对，如果有多个参数可以后缀调用多个param()
            MvcResult是获得服务器的Response内容。*/
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/user/login.do")
                .param("username","long").param("password","123")
        )
                .andExpect(view().name("redirect:home/que.do"));
    }

    @Ignore
    @Test
    public void register() throws Exception {
        User user = new User();
        String json = JSON.toJSONString(user);
        /*ResultAction是用来模拟Browser发送FORM表单请求的。post()是请求的地址，accept()请求的内容
            param()请求的键值对，如果有多个参数可以后缀调用多个param()
            MvcResult是获得服务器的Response内容。*/
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/user/register.do")
                .accept(MediaType.APPLICATION_JSON)
                .param("username","long").param("password","123")
                .param("sex","mike").param("email","123")
                .param("profilePhoto","mike").param("phone","123")
        )
                .andExpect(status().isOk()).andDo(print());
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=====客户端获得反馈数据:" + result);

    }

}