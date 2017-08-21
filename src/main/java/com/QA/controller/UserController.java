package com.QA.controller;

import com.QA.po.User;
import com.QA.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Administrator on 2017/7/15.
 */
@Controller
//@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value="/login", method=GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/login", method=POST)
    public String login(HttpServletRequest request, HttpSession session, Model model){
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("login");

        String msg = "";
        boolean rememberMe = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String srememberMe = request.getParameter("rememberMe");
        String randomcode = request.getParameter("randomcode").toUpperCase(); //取出页面的验证码
        String validateCode= (String) session.getAttribute("validateCode"); //取出session中的正确验证码

        System.out.println(username);
        System.out.println(password);
        System.out.println(srememberMe);
        System.out.println("r:"+randomcode);
        System.out.println("v:"+validateCode);

        if(randomcode==""||randomcode==null){
            msg = "验证码不能为空";
            model.addAttribute("message", msg);
            System.out.println(msg);
            return "login";
        }
        else if(randomcode!=null&&validateCode!=null&&!randomcode.equals(validateCode)) {
            msg = "验证码错误";
            model.addAttribute("message", msg);
            System.out.println(msg);
            return "login";
        }

        if(srememberMe != null && srememberMe.equals("on")){
            rememberMe = true;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);

            if (subject.isAuthenticated()) {

                System.out.println("shiro:"+ SecurityUtils.getSubject().getPrincipal());
                User user = userService.findUserByName(username);
                System.out.println(user);
                request.getSession().setAttribute("loginUser", user.getId());

                System.out.println("save session");

                return "redirect:home/que";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            System.out.println(msg);
        }
        return "login";
    }

    @RequestMapping(value="/register", method=GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value="/register", method=POST)
    public String register(HttpServletRequest request, Model model){
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("Register");

        User user = new User();
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        System.out.println(sex);
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String profilePhone = request.getParameter("profilePhone");
        String password = request.getParameter("password");

        if(username != null){
            user.setUsername(username);
        }

        if(sex != null){
            user.setSex(sex);
        }

        if(email != null){
            user.setEmail(email);
        }

        if(phone != null){
            user.setPhone(phone);
        }

        if(profilePhone != null){
            user.setProfilePhoto(profilePhone);
        }else{
            user.setProfilePhoto("images/temp.jpg");
        }

        if(password != null){
            user.setPassword(password);
        }

        userService.addUser(user);

        return "redirect:login";
    }

    @RequestMapping("/setting")
    public String setting(){
        return "setting";
    }
}
