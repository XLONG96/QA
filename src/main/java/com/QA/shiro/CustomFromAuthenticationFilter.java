package com.QA.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/16.
 */
public class CustomFromAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {

        System.out.println("This is filter");

        //在这里进行验证码的校验
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpSession session=httpServletRequest.getSession();
        //取出session中的正确验证码
        String validateCode= (String) session.getAttribute("validateCode");
        System.out.println("v:"+validateCode);
        //取出页面的验证码
        String randomcode=httpServletRequest.getParameter("randomcode");
        System.out.println("r:"+randomcode);
        if(randomcode==null){
            System.out.println("code must be not null");
        }
        if (randomcode!=null&&validateCode!=null&&!randomcode.equals(validateCode))
        {
            System.out.println("===fail===");
            //如果校验失败，将验证码错误的失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("message","验证码错误");

            //拒绝访问，不再校验账号和密码
            return false;

        }

        //继续验证
        return super.onAccessDenied(request, response);
    }
}
