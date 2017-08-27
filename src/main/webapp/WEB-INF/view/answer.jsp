<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/3
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${que.title}</title>
    <%@ include file="/WEB-INF/view/common.jsp"%>
    <script>
        $(function(){
            $(".btn").click(function(){
                $.ajax({
                    type:"POST",
                    url:"/QA/answer",
                    data:$("form").serialize(),
                    dataType:"json",
                    success:callback ,
                    error:function(xhr,info){
                        alert(xhr.status+" "+xhr.statusText+" "+info);
                    }
                });

                function callback(data){
                    $( "#dialog" ).dialog( "open" );
                    if(data.msg!=null){
                        $("#dialog").text(data.msg+" 按“确认/取消”键后刷新页面...");
                    }
                    else{
                        $("#dialog").text("提交失败，请稍后再试...");
                    }
                }
            });

            $( "#dialog" ).dialog({
                autoOpen: false,
                width: 400,
                draggable: false,
                modal: true,
                close: function() {
                    location.reload();
                },
                buttons: [
                    {
                        text: "确定",
                        click: function() {
                            $( this ).dialog( "close" );
                            location.reload();
                        }
                    },
                    {
                        text: "取消",
                        click: function() {
                            $( this ).dialog( "close" );
                            location.reload();
                        }
                    }
                ]
            });

            $(".pagination li").click(function(){
               $("li.active").removeClass("active");
               $(this).addClass("active");
            });

            $(".back-to-top").hover(
                function(){
                    $("#img").attr("src","/QA/images/topb.gif");
                },
                function(){
                    $("#img").attr("src","/QA/images/topw.gif");
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
        });
    </script>
</head>
<body style="overflow:scroll">
<%@ include file="/WEB-INF/view/head.jsp"%>
<a href="#top" class="back-to-top" target="_self">
    <img src="${baseurl}images/topw.gif" id="img"/>
</a>

<div id="top" class="que-main">
    <div class="que-head">
        <p class="que-title"><strong>${que.title}</strong></p>
    </div>
    <div class="que-other">
        <p><span class="glyphicon glyphicon-comment"></span> ${que.replyNum} 回答</p>
        <p><span class="glyphicon glyphicon-bullhorn"></span> 分享</p>
        <p><span class="glyphicon glyphicon-star"></span> ${que.starNum} 收藏</p>
        <p><span class="glyphicon glyphicon-bell"></span> 举报</p>
        <p><span class="glyphicon glyphicon-calendar"></span>
            <fmt:formatDate value="${que.publicTime}" type="both"/>
        </p>
    </div>
</div>

<div class="ans">
    <c:forEach items="${paging.pageContent}" var="ans">
    <div class="box-ans">
        <div class="ans-detail">
            <div>
                <img src="${baseurl}${ans.authorHeadImg}" class="ans-img" />
                <strong class="name">${ans.authorName}</strong>
            </div>
            <div class="ans-content">${ans.detail}</div>
            <div class="ans-bottom">
                <p><span class="glyphicon glyphicon-thumbs-up"></span> ${ans.agreeNum} 赞</p>
                <p><span class="glyphicon glyphicon-comment"></span> ${ans.commentNum} 评论</p>
                <p><span class="glyphicon glyphicon-star"></span> ${ans.starNum} 收藏</p>
                <p><span class="glyphicon glyphicon-bell"></span> 举报</p>
                <p><span class="glyphicon glyphicon-calendar"></span>
                    <fmt:formatDate value="${ans.answerTime}" type="both"/></p>
            </div>
        </div>
    </div>
    </c:forEach>

    <c:if test="${paging.totalPage>1}">
        <ul class="pagination">
            <c:if test="${paging.currentPage>1}">
                <li><a href="${baseurl}answer/${que.id}?cp=${paging.currentPage-1}">&laquo;</a></li>
            </c:if>

            <c:choose>
                <c:when test="${paging.currentPage eq 1}">
                    <li class="active"><a href="${baseurl}answer/${que.id}?cp=1">1</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${baseurl}answer/${que.id}?cp=1">1</a></li>
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
                        <li class="active"><a href="${baseurl}answer/${que.id}?cp="${i}>${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${baseurl}answer/${que.id}?cp=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${paging.currentPage+2<paging.totalPage&&paging.totalPage>6}">
                <li><a href="#">...</a></li>
            </c:if>

            <c:choose>
                <c:when test="${paging.currentPage eq paging.totalPage}">
                    <li class="active"><a href="${baseurl}answer/${que.id}?cp=${paging.totalPage}">${paging.totalPage}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${baseurl}answer/${que.id}?cp=${paging.totalPage}">${paging.totalPage}</a></li>
                </c:otherwise>
            </c:choose>

            <c:if test="${paging.currentPage<paging.totalPage}">
                <li><a href="${baseurl}answer/${que.id}?cp=${paging.currentPage+1}">&raquo;</a></li>
            </c:if>
        </ul>
    </c:if>

    <hr/>

    <div class="ans-editor">
        <p class="title"><strong>写下你的回答</strong></p>
        <form action="answers?questionId=${que.id}" method="post">
            <!-- 加载编辑器的容器 -->
            <script id="container" name="detail" type="text/plain"></script>
            <script src="${baseurl}js/ansEditor.js"></script>
            <input type="hidden"  name="questionId" value="${que.id}"/>
            <input type="button" value="提交回答" class="btn btn-success"/>
        </form>
    </div>

    <!-- ui-dialog -->
    <div id="dialog" title=""></div>
</div>

<div class="answer-box-info">
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
</body>
</html>

