<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common.jsp"%>
    <title>我的主页</title>
    <script>
        $(function(){
            $(".meun li").hover(
                function(){
                    $(this).css("background-color","#ade3dd");
                },
                function(){
                    $(this).css("background-color","white");
                }
            );
        });
    </script>
</head>
<body style="overflow:scroll">
    <%@ include file="/WEB-INF/view/head.jsp"%>
    <div class="profile-box">
        <div class="warpaper">
            <img src="${baseurl}images/warpaper01.jpg"/>
        </div>

        <%--<div class="head-name">
            <p><strong>${user.username}</strong></p>
        </div>--%>

        <%--<div class="head-box">
            <div class="headimg">
                <img src="${baseurl}${user.profilePhoto}"/>
            </div>
        </div>--%>

        <div class="selector">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#"> 我的问题 </a></li>
                <li><a href="#"> 我的回答 </a></li>
                <li><a href="#"> 我的评论 </a></li>
                <li><a href="#"> 我的收藏 </a></li>
                <li><a href="#"> 我的动态 </a></li>
                <li><a href="#"> 修改资料 </a></li>
            </ul>

            <div class="content">

            </div>
        </div>
    </div>

    <div class="profile-infobox">
        <div class="container" id="crop-avatar">

            <div class="avatar-view" title="修改头像">
                <img src="${baseurl}${user.profilePhoto}"/>
            </div>

            <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <form class="avatar-form" action="${baseurl}headimgupload" enctype="multipart/form-data" method="post">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal" type="button">&times;</button>
                                <h4 class="modal-title" id="avatar-modal-label">上传头像</h4>
                            </div>
                            <div class="modal-body">
                                <div class="avatar-body">

                                    <!-- Upload image and data -->
                                    <div class="avatar-upload">
                                        <input class="avatar-src" name="avatar_src" type="hidden">
                                        <input class="avatar-data" name="avatar_data" type="hidden">
                                        <label for="avatarInput">本地图片上传</label>
                                        <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                                    </div>

                                    <!-- Crop and preview -->
                                    <div class="row">
                                        <div class="col-md-9">
                                            <div class="avatar-wrapper"></div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="avatar-preview preview-lg"></div>
                                            <div class="avatar-preview preview-md"></div>
                                            <div class="avatar-preview preview-sm"></div>
                                        </div>
                                    </div>

                                    <div class="row avatar-btns">
                                        <div class="col-md-3" style="left:550px">
                                            <button class="btn btn-primary btn-block avatar-save" type="submit">保 存</button>
                                        </div>
                                        <div class="col-md-3" style="left:550px">
                                            <button class="btn btn-primary" data-dismiss="modal" type="button"> 取 消 </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div><!-- /.modal -->

            <!-- Loading state -->
            <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
        </div>

        <div class="name">
            <p>${user.username}</p>
        </div>

        <div class="info-detail">
            <ul class="meun">
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-question-sign">  我的问题</span>
                        <span class="badge" style="margin-left:120px">${user.questionNum}</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <sapn class="glyphicon glyphicon-pencil">  我的回答</sapn>
                        <span class="badge" style="margin-left:120px">${user.answerNum}</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-globe">  我的关注</span>
                        <span class="badge" style="margin-left:120px">${user.starNum}</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-heart">  我的粉丝</span>
                        <span class="badge" style="margin-left:120px">${user.fansNum}</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="footer">
            <p>© 2017-2018 xlong 版权所有</p>
            <p>联系我 @1073501156qq.com</p>
            <p> <a href="#">关于本站</a> @QA在线问答平台</p>
        </div>
    </div>
</body>
</html>
