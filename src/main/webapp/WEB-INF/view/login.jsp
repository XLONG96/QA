<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/15
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common.jsp"%>
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta name="author" content="DeathGhost" />
    <style>
        body{height:100%;background:#16a085;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
    <script src="${baseurl}js/Particleground.js"></script>
    <script>
        $(document).ready(function() {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });

            $(".login_img").click(function(){
                var imgSrc = $(".login_img");
                var src = imgSrc.attr("src");
                imgSrc.attr("src",chgUrl(src));
            });

            function chgUrl(url){
                var timestamp = (new Date()).valueOf();
                url = url + "?timestamp=" + timestamp;
                return url;
            }

        });
    </script>
    <style type="text/css">
        .tb960x90 {display:none!important;display:none}
    </style>
</head>

<body>
<dl class="admin_login">
    <dt>
        <strong>站点后台管理系统</strong>
        <em>Management System</em>
    </dt>

    <form action="${baseurl}login" method="post">
        <dd class="user_icon">
            <input type="text" placeholder="账号" name="username" class="login_txtbx"/>
        </dd>

        <dd class="pwd_icon">
            <input type="password" placeholder="密码" name="password" class="login_txtbx"/>
        </dd>

        <dd class="val_icon">
            <div class="checkcode">
                <input type="text" name="randomcode" placeholder="验证码" maxlength="4" class="login_txtbx">
            </div>
            <img src="${baseurl}validate" class="login_img" alt="点击下一张"/>
            <!--<input type="button" value="验证码核验" class="ver_btn" onClick="validate();">-->
        </dd>

        <dd>
            <input type="checkbox" name="rememberMe"/>记住我
            <a href="${baseurl}register" class="re">注 册</a>
            <a href="${baseurl}#" class="fo">忘记密码？</a>
        </dd>

        <div class="msg">
            <p>${message}</p>
        </div>

        <dd>
            <input type="submit" value="立即登陆" class="submit_btn"/>
        </dd>
    </form>

    <dd class="buttom_box">
        <p>© 2017-2018 xlong 版权所有</p>
        <p>粤备2B-888666-1</p>
    </dd>

</dl>
</body>
</html>