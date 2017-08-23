package com.QA.controller;

import com.QA.po.*;
import com.QA.service.AnswerService;
import com.QA.service.CommentService;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class ProfileController {
    protected Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/people")
    public String profile(HttpSession session, Model model){
        int uId = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uId);
        model.addAttribute("user",user);

        return "profile";
    }

    @RequestMapping("/headimgupload")
    public @ResponseBody  void HeadImgUpload(@RequestParam("avatar_file") MultipartFile profilePicture,
                         HttpServletRequest request, HttpSession session){
        int uid = (Integer)session.getAttribute("loginUser");
        String filedir = request.getSession().getServletContext().getRealPath("/")+
                "upload/images/headImg";
        String filename = uid+".jpg";
        if(!profilePicture.isEmpty()){
            try {
                //如果目录不存在就创建
                File dir = new File(filedir);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                profilePicture.transferTo(new File(filedir+"/"+filename));

                userService.saveProfilePhoto(uid, "upload/images/headImg/"+filename);

            } catch (IOException e) {
                log.error("Unable to save the profilePicture");
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/myquestion")
    public String myQuestion(@RequestParam("cp") String cp, HttpSession session, Model model){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);

        Paging<Question> page = new Paging();
        page.setPerNum(10);

        if(cp == null || cp.equals("")){
            page.setCurrentPage(1);
        }
        else{
            page.setCurrentPage(Integer.parseInt(cp));
        }

        page.setTotalNum(user.getQuestionNum());
        int totalPage = page.getTotalNum() / page.getPerNum();

        page.setTotalPage(page.getTotalNum() % page.getPerNum() == 0 ? totalPage : totalPage + 1);

        int startNum = ( page.getCurrentPage()-1 ) * page.getPerNum();
        page.setPageContent(questionService.findQuestionListByUserId(uid,
                startNum, page.getPerNum()));

        System.out.println(page);
        model.addAttribute("paging",page);

        return "myquesiton";
    }

    @RequestMapping("/myanswer")
    public String myAnswer(@RequestParam("cp") String cp, HttpSession session, Model model){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);

        Paging<Answer> page = new Paging();
        page.setPerNum(10);

        if(cp == null || cp.equals("")){
            page.setCurrentPage(1);
        }
        else{
            page.setCurrentPage(Integer.parseInt(cp));
        }

        page.setTotalNum(user.getAnswerNum());
        int totalPage = page.getTotalNum() / page.getPerNum();

        page.setTotalPage(page.getTotalNum() % page.getPerNum() == 0 ? totalPage : totalPage + 1);

        int startNum = ( page.getCurrentPage()-1 ) * page.getPerNum();
        page.setPageContent(answerService.findAnswerListByUserId(uid,
                startNum, page.getPerNum()));

        System.out.println(page);
        model.addAttribute("paging",page);

        return "myanswer";
    }

    @RequestMapping("/mycomment")
    public String myComment(@RequestParam("cp") String cp, HttpSession session, Model model){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);

        Paging<Comment> page = new Paging();
        page.setPerNum(10);

        if(cp == null || cp.equals("")){
            page.setCurrentPage(1);
        }
        else{
            page.setCurrentPage(Integer.parseInt(cp));
        }

        page.setTotalNum(user.getCommentNum());
        int totalPage = page.getTotalNum() / page.getPerNum();

        page.setTotalPage(page.getTotalNum() % page.getPerNum() == 0 ? totalPage : totalPage + 1);

        int startNum = ( page.getCurrentPage()-1 ) * page.getPerNum();
        page.setPageContent(commentService.findCommnetListByUserId(uid,
                startNum, page.getPerNum()));

        System.out.println(page);
        model.addAttribute("paging",page);

        return "mycomment";
    }

    @RequestMapping("/mystar")
    public String myStar(@RequestParam("cp") String cp, HttpSession session, Model model){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        model.addAttribute("user",user);

        Paging<Question> page = new Paging();
        page.setPerNum(10);

        if(cp == null || cp.equals("")){
            page.setCurrentPage(1);
        }
        else{
            page.setCurrentPage(Integer.parseInt(cp));
        }

        page.setTotalNum(user.getStarNum());
        int totalPage = page.getTotalNum() / page.getPerNum();

        page.setTotalPage(page.getTotalNum() % page.getPerNum() == 0 ? totalPage : totalPage + 1);

        int startNum = ( page.getCurrentPage()-1 ) * page.getPerNum();
        page.setPageContent(questionService.findStarQuestionListByUserId(uid,
                startNum, page.getPerNum()));

        System.out.println(page);
        model.addAttribute("paging",page);

        return "mystar";
    }

    @RequestMapping("/editprofile")
    public String editPrifile(HttpServletRequest request, HttpSession session){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        //model.addAttribute("user",user);
        return null;
    }

}
