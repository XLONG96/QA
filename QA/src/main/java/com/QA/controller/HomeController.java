package com.QA.controller;

import com.QA.po.Question;
import com.QA.po.User;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 返回主页的问题列表，其中的”/asy-que“是用于ajax异步请求
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    protected Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/que","/"}, method=GET)
    public String home(HttpSession session, Model model){
        int perNum = 4;
        int startNum = 0;

        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);

        System.out.println(startNum+" "+perNum);
        List<Question> questionList = questionService.findQuestionList(startNum,perNum);
        model.addAttribute("questionList",questionList);

        return "home";
    }

    @RequestMapping(value="/asy-que", method=GET)
    public @ResponseBody String home(HttpServletRequest request){
        int perNum = 4;
        int startNum;
        String questions;
        String msg;

        String start = (String) request.getSession().getAttribute("startNum");

        if(start != null){
            startNum = Integer.parseInt(start);
        } else{
            startNum = 0;
        }

        System.out.println(startNum+" "+perNum);
        List<Question> questionList = questionService.findQuestionList(startNum,perNum);

        if(questionList == null){
            request.getSession().removeAttribute("startNum");
            msg = "nothing";
            return JSON.toJSONString(msg);
        }

        if( questionList.size() == perNum ){
            startNum = questionList.get(perNum-1).getId();
            request.getSession().setAttribute("startNum",startNum);
        }else{
            request.getSession().removeAttribute("startNum");
        }

        questions = JSON.toJSONString(questionList);

        return questions;

    }
}
