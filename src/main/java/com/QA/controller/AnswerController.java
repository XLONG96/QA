package com.QA.controller;

import com.QA.po.Answer;
import com.QA.po.Paging;
import com.QA.po.Question;
import com.QA.po.User;
import com.QA.service.AnswerService;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据question id 搜索相对应问题，再搜索相关的回答列表
 */
@Controller
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @RequestMapping("/answer/{questionId}")
    public String getAnswer(@PathVariable int questionId, HttpServletRequest request, Model model, HttpSession session){
        int uId = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uId);
        model.addAttribute("user",user);

        System.out.println("id="+questionId);
        Question question = questionService.findQusetionById(questionId);
        model.addAttribute("que",question);

        Paging<Answer> page = new Paging();
        page.setPerNum(10);

        String cp = request.getParameter("cp");
        if(cp == null || cp.equals("")){
            page.setCurrentPage(1);
        }
        else{
            page.setCurrentPage(Integer.parseInt(cp));
        }

        page.setTotalNum(answerService.findQueAnswerCount(questionId));
        int totalPage = page.getTotalNum() / page.getPerNum();

        page.setTotalPage(page.getTotalNum() % page.getPerNum() == 0 ? totalPage : totalPage + 1);

        int startNum = ( page.getCurrentPage()-1 ) * page.getPerNum();
        page.setPageContent(answerService.findAnswerListByQueId(questionId,
                startNum, page.getPerNum()));

        System.out.println(page);
        model.addAttribute("paging",page);
        return "answer";
    }

    /**
     * 回答对应问题并存入数据库
     * @return
     */
    @RequestMapping(value="/answer")
    public @ResponseBody String answer(HttpSession session,HttpServletRequest request){
        Answer answer = new Answer();
        //保存问题
        String questionId = request.getParameter("questionId");
        int qId = Integer.parseInt(questionId);
        questionService.addReplyNumById(qId);//对应问题的回复数加一
        answer.setQuestionId(qId);
        //保存回答
        String detail = request.getParameter("detail");
        answer.setDetail(detail);
        Date date = new Date();
        answer.setAnswerTime(date);
        //保存回答者信息
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        userService.addAnswerNumById(uid);
        answer.setAuthorHeadImg(user.getProfilePhoto());
        answer.setAuthorName(user.getUsername());

        answerService.saveAnswer(answer);

        String msg = "提交成功！";
        Map<String,String> map = new HashMap<String,String>();
        map.put("msg",msg);
        msg = JSON.toJSONString(map);

        return msg;
    }
}
