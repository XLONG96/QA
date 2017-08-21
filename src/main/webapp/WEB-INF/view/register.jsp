<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/16
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/view/common.jsp"%>
    <title>注册</title>
    <script src="${baseurl}js/Particleground.js"></script>
    <script>
        $(document).ready(function() {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });

            $(".btn").click(function(){
                $( "#dialog" ).dialog( "open" );
            });

            $( "#dialog" ).dialog({
                autoOpen: false,
                width: 400,
                draggable: false,
                modal: true,
                buttons: [
                    {
                        text: "确定",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    },
                    {
                        text: "取消",
                        click: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                ]
            });
        })
    </script>
    <style>
        body{height:100%;background:#f5f5f5;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
</head>
<body style="overflow-x: hidden;overflow-y: auto">
<div class="register-box">
    <form class="form-horizontal" action="${baseurl}register" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">昵 称</label>
            <div class="col-sm-10">
                <input type="text" name="username" class="form-control" id="username" placeholder="请输入昵称..."/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">性 别</label>
            <div class="col-sm-10">
                <div class="radio-inline">
                    <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="option1" checked> 男
                </div>
                <div class="radio-inline">
                    <input type="radio" name="optionsRadiosinline" id="optionsRadios2"  value="option2"> 女
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密 码</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码..."/>
            </div>
        </div>

        <div class="form-group">
            <label for="passwordAgain" class="col-sm-2 control-label">   </label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="passwordAgain" placeholder="请再次输入密码..."/>
            </div>
        </div>

        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮 箱</label>
            <div class="col-sm-10">
                <input type="text" name="email" class="form-control" id="email" placeholder="请输入邮箱..."/>
            </div>
        </div>

        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">手 机</label>
            <div class="col-sm-10">
                <input type="text" name="phone" class="form-control" id="phone" placeholder="请输入手机..."/>
            </div>
        </div>

        <div class="checkbox">
            <input type="checkbox"/><a href="#">我已阅读并同意QA平台的所有无理要求</a>
        </div>

        <input type="submit" value="立即注册" class="btn btn-success"/>
    </form>

    <!-- ui-dialog -->
    <div id="dialog" title="">
        <p>恭喜！您已注册成功！按“确认/取消”键后将为您自动转到QA的登录页面...</p>
    </div>
</div>
</body>
</html>
