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

            $(".submit_bt").click(function(){
                $.ajax({
                    type:"POST",
                    url:"user/login.do?",
                    data:$("form").serialize(),
                    dataType:"json",
                    success:callback ,
                    error:function(xhr,info){
                        alert(xhr.status+" "+xhr.statusText+" "+info);
                    }
                });

                function callback(data){
                    alert(data.username+" "+data.password);
                }
            });

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
            <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
            <!--<input type="image" url=""/>-->
        </div>
        <!--<input type="button" value="验证码核验" class="ver_btn" onClick="validate();">-->
    </dd>

    <div>
        <input type="checkbox" name="rememberMe"/>记住我
    </div>

    <div>
        <p>${message}</p>
    </div>

    <dd>
        <input type="submit" value="立即登陆" class="submit_btn"/>
    </dd>
    </form>

    <dd class="buttom_box">
        <p>© 2016-2017 xlong 版权所有</p>
        <p>粤备B2-8998988-1</p>
    </dd>

</dl>
</body>
</html>