<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/18
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common.jsp"%>
    <script src="${baseurl}js/Particleground.js"></script>
    <script>
        $(document).ready(function() {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#5cbdaa',
                lineColor: '#5cbdaa'
            });

            $("div[name=content]").hover(
                function(){
                    $(this).css("color","gray");
                },
                function(){
                    $(this).css("color","black");
                }
            );

            $(".meun li").hover(
                function(){
                    $(this).css("background-color","#ade3dd");
                },
                function(){
                    $(this).css("background-color","white");
                }
            );

            $(".back-to-top").hover(
                function(){
                    $("#img").attr("src","/QA/images/topb.gif");
                },
                function(){
                    $("#img").attr("src","/QA/images/topw.gif");
                }
            );
        })
    </script>
    <style>
        body{height:100%;background:#f5f5f5;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
    <title>首页</title>
</head>
<body style="overflow:scroll">
    <a href="#top" class="back-to-top" target="_self">
        <img src="${baseurl}images/topw.gif" id="img"/>
    </a>
    <%@ include file="/WEB-INF/view/head.jsp"%>
    <div id="top" class="box-main">
        <div class="box">
            <c:forEach items="${paging.pageContent}" var="que">
                <div class="box-que">
                    <div class="que-detail">
                        <div>
                            <img src="${baseurl}${que.authorHeadImg}" class="que-img" />
                            <strong class="name">${que.authorName}</strong>
                        </div>
                        <div class="que"><strong><a href="${baseurl}answer/${que.id}" target="_blank">${que.title}</a></strong></div>
                        <div name="content" class="content">${que.detail}</div>
                        <div class="que-all"><span class="glyphicon glyphicon-chevron-down"></span> 阅读全文</div>
                        <div class="que-all-close" style="display:none"><span class="glyphicon glyphicon-chevron-up"></span> 收起</div>
                        <div class="que-bottom">
                            <p><span class="glyphicon glyphicon-comment"></span> ${que.replyNum} 回答</p>
                            <p><span class="glyphicon glyphicon-bullhorn"></span> 分享</p>
                            <p><span class="glyphicon glyphicon-star"></span> ${que.starNum} 收藏</p>
                            <p><span class="glyphicon glyphicon-bell"></span> 举报</p>
                            <p><span class="glyphicon glyphicon-calendar"></span>
                                <fmt:formatDate value="${que.publicTime}" type="both"/>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${paging.totalPage>1}">
                <ul class="pagination">
                    <c:if test="${paging.currentPage>1}">
                        <li><a href="${baseurl}home/${que.id}?cp=${paging.currentPage-1}">&laquo;</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${paging.currentPage eq 1}">
                            <li class="active"><a href="${baseurl}home/${que.id}?cp=1">1</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${baseurl}home/${que.id}?cp=1">1</a></li>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${paging.totalPage<=6}">
                            <c:set var="begin" value="2"/>
                            <c:set var="end" value="${paging.totalPage-1}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${paging.currentPage-1}"/>
                            <c:set var="end" value="${paging.currentPage+1}"/>
                            <%--头溢出--%>
                            <c:if test="${begin<3}">
                                <c:set var="begin" value="2"/>
                                <c:set var="end" value="5"/>
                            </c:if>
                            <%--尾溢出--%>
                            <c:if test="${end>paging.totalPage-2}">
                                <c:set var="begin" value="${paging.totalPage-4}"/>
                                <c:set var="end" value="${paging.totalPage-1}"/>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${paging.currentPage-2>1&&paging.totalPage>6}">
                        <li><a href="#">...</a></li>
                    </c:if>

                    <c:forEach var="i" begin="${begin}" end="${end}">
                        <c:choose>
                            <c:when test="${i eq paging.currentPage}">
                                <li class="active"><a href="${baseurl}home/${que.id}?cp="${i}>${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${baseurl}home/${que.id}?cp=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${paging.currentPage+2<paging.totalPage&&paging.totalPage>6}">
                        <li><a href="#">...</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${paging.currentPage eq paging.totalPage}">
                            <li class="active"><a href="${baseurl}home/${que.id}?cp=${paging.totalPage}">${paging.totalPage}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${baseurl}home/${que.id}?cp=${paging.totalPage}">${paging.totalPage}</a></li>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${paging.currentPage<paging.totalPage}">
                        <li><a href="${baseurl}home/${que.id}?cp=${paging.currentPage+1}">&raquo;</a></li>
                    </c:if>
                </ul>
            </c:if>
        </div>

        <div class="box-info">
            <div class="avatar">
                <img src="${baseurl}${user.profilePhoto}"/>
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

    </div>
</body>
</html>
