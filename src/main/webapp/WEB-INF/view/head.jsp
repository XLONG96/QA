<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/3
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="head">
    <nav class="navbar" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Q  A</a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${baseurl}home/que"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                    <li><a href="${baseurl}quiz">提问</a></li>
                    <li><a href="#">发现</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${loginUser==null}">
                        <li><a href="${baseurl}register"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 快速登录</a></li>
                    </c:when>

                    <c:otherwise>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img src="${baseurl}${user.profilePhoto}" class="head-img" /> <!--<b class="caret"></b>-->
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${baseurl}people"><span class="glyphicon glyphicon-user"> 我的主页</span></a></li>
                                <li><a href="${baseurl}setting"><span class="glyphicon glyphicon-cog"> 设置</span></a></li>
                                <li><a href="${baseurl}logout"><span class="glyphicon glyphicon-off"> 退出</span></a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
                </ul>

                <div class="search">
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" size="40" maxlength="38" placeholder="输入关键词...">
                        </div>
                        <button type="submit" class="btn btn-success">搜索</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
</div>