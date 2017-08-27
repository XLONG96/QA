package com.QA.controller;

import com.QA.po.*;
import com.QA.service.AnswerService;
import com.QA.service.CommentService;
import com.QA.service.QuestionService;
import com.QA.service.UserService;
import com.QA.util.ImageCut;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value="/headimgupload", method=POST)
    public @ResponseBody String HeadImgUpload(MultipartFile avatar_file,String avatar_src,String avatar_data,
                         HttpServletRequest request, HttpSession session){
        int uid = (Integer)session.getAttribute("loginUser");
        User user = userService.findUserById(uid);
        String filedir = request.getSession().getServletContext().getRealPath("/")+
                "upload/images/headImg";
        String filename = UUID.randomUUID().toString()+".jpg";
        Map<String,String> map = new HashMap<String,String>();
        String msg = "";

        //String name = avatar_file.getOriginalFilename();
        //判断文件的MIMEtype
        String type = avatar_file.getContentType();
        if(type==null || !type.toLowerCase().startsWith("image/")){
            msg = "类型错误，应该为image";
            map.put("message",msg);
            msg = JSON.toJSONString(map);
            return msg;
        }
        log.info("file type:"+type);

        JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
        // 用户经过剪辑后的图片的大小
        float x = joData.getFloatValue("x");
        float y = joData.getFloatValue("y");
        float w =  joData.getFloatValue("width");
        float h =  joData.getFloatValue("height");

        //开始上传
        File targetFile = new File(filedir, filename);
        //保存
        try {
            if(!targetFile.exists()){
                targetFile.mkdirs();
                InputStream is = avatar_file.getInputStream();
                ImageCut.cut(is, targetFile, (int)x,(int)y,(int)w,(int)h);
                is.close();

                //更新用户
                userService.saveProfilePhoto(uid, "upload/images/headImg/"+filename);
                //更新问题
                answerService.saveProfilePhoto(user.getUsername(), "upload/images/headImg/"+filename);
                //更新回答
                questionService.saveProfilePhoto(user.getUsername(), "upload/images/headImg/"+filename);
                //更新评论
            }
        } catch (Exception e) {
            log.error("Unable to save the profilePicture");
            e.printStackTrace();
            msg = "上传失败，请稍后再试";
            map.put("message",msg);
            msg = JSON.toJSONString(map);
            return msg;
        }

        msg = "头像上传成功";
        map.put("message",msg);
        msg = JSON.toJSONString(map);
        return msg;
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
        page.setPageContent(questionService.findQuestionListByUserName(user.getUsername(),
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
        page.setPageContent(answerService.findAnswerListByUserName(user.getUsername(),
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
        page.setPageContent(questionService.findStarQuestionListByUserName(user.getUsername(),
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
        return "editprofile";
    }

}
