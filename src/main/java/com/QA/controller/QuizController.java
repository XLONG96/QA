package com.QA.controller;

import com.QA.po.Question;
import com.QA.po.User;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public String quiz(){
        return "quiz";
    }

    @RequestMapping(value="/quiz", method=POST)
    public String quiz(HttpServletRequest request, Model model){
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");

        int id = (Integer)request.getSession().getAttribute("loginUser");

        User user = userService.findUserById(id);

        Question question = new Question();
        String msg = "";
        Date date = new Date();

        System.out.println(user);

        question.setTitle(title);
        question.setDetail(detail);
        question.setAuthorName(user.getUsername());
        question.setAuthorHeadImg(user.getProfilePhoto());
        question.setReplyNum(0);
        question.setStarNum(0);
        question.setPublicTime(date);
        //保存问题
        questionService.saveQuestion(question);

        msg = "问题保存成功";
        model.addAttribute("msg",msg);
        return msg;
    }
}
