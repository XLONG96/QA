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
                }
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

            //首先将#back-to-top隐藏
            //$(".back-to-top").hide();

            //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
            $(window).scroll(function(){

                if ($(window).scrollTop()>100){
                    $(".back-to-top").fadeIn(1500);
                }
                else
                {
                    $(".back-to-top").fadeOut(1500);
                }
            });

            //当点击跳转链接后，回到页面顶部位置
            /*$(".back-to-top").click(function(){
                console.log($("body,html").scrollTop())
                //alert($("body,html").scrollTop(12)+" px");
                $('html, body').animate({
                    scrollTop: $('html').offset().top
                }, 500, 'easeInOutExpo');
                return false;
            });*/
            /*$('.back-to-top img').on('click', function(event){
                alert("hello");
                event.preventDefault();

                $('html, body').animate({
                    scrollTop: $('html').offset().top
                }, 500, 'easeInOutExpo');

                return false;
            });*/
            /*$(".back-to-top").goToTop();
            $(window).bind('scroll resize',function(){
                $(".back-to-top").goToTop();
            });*/
        });

    </script>
</head>
<body style="overflow:scroll">
<%@ include file="/WEB-INF/view/head.jsp"%>
<a href="#top" class="back-to-top" target="_self"><img src="${baseurl}images/temp.jpg"/></a>

<div id="top" class="que-main">
    <div class="que-head">
        <p class="que-title"><strong>${que.title}</strong></p>
    </div>
    <div class="que-other">
        <p><span class="glyphicon glyphicon-thumbs-up"></span> 1024赞</p>
        <p><span class="glyphicon glyphicon-comment"></span> ${que.replyNum}评论</p>
        <p><span class="glyphicon glyphicon-star"></span> ${que.starNum}收藏</p>
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
                <p><span class="glyphicon glyphicon-thumbs-up"></span> ${ans.agreeNum}赞</p>
                <p><span class="glyphicon glyphicon-comment"></span> ${ans.commentNum}评论</p>
                <p><span class="glyphicon glyphicon-star"></span> ${ans.starNum}收藏</p>
                <p><span class="glyphicon glyphicon-bell"></span> 举报</p>
                <p><span class="glyphicon glyphicon-calendar"></span> ${ans.answerTime}</p>
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

    <br/>

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

    <div class="ui-state-default ui-corner-all" title=".ui-icon-arrowthick-1-n">
        <span class="ui-icon ui-icon-arrowthick-1-n"></span>
    </div>

    <!-- ui-dialog -->
    <div id="dialog" title="">
        <p>回答问题成功，按“确认/取消”键后刷新页面...</p>
    </div>
</div>
<a href="#" target="_self">返回顶部</a>
</body>
</html>

