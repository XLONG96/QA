package com.QA.controller;

import com.QA.po.Question;
import com.QA.po.User;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 保存问题的标题和具体内容到数据库
 */
@Controller
public class QuizController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/quiz",method=GET)
    public String quiz(HttpSession session, Model model){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);
        return "quiz";
    }

    @RequestMapping(value="/quiz", method=POST)
    public @ResponseBody String quiz(HttpServletRequest request, HttpSession session){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);

        String title = request.getParameter("title");
        String detail = request.getParameter("detail");

        Question question = new Question();
        String msg = "";
        Date date = new Date();

        question.setTitle(title);
        question.setDetail(detail);
        question.setAuthorName(user.getUsername());
        question.setAuthorHeadImg(user.getProfilePhoto());
        question.setReplyNum(0);
        question.setStarNum(0);
        question.setPublicTime(date);
        //保存问题
        questionService.saveQuestion(question);
        userService.addQuestionNumById(uid);

        Map<String,String> map = new HashMap<String,String>();
        msg = "提问成功";
        map.put("msg",msg);
        msg = JSON.toJSONString(map);
        return msg;
    }
}
