<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/3
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/view/common.jsp"%>
    <title>提问</title>
    <script>
        $(function(){
            $(".btn").click(function(){
                $.ajax({
                    type:"POST",
                    url:"quiz",
                    data:$("form").serialize(),
                    dataType:"json",
                    success:callback ,
                    error:function(xhr,info){
                        alert(xhr.status+" "+xhr.statusText+" "+info);
                    }
                });

                function callback(data){
                    //$( "#dialog" ).dialog( "open" );
                    if(data.msg!=null){
                        //$("#dialog p").text(data.msg+" 按“确认/取消”键后刷新页面...");
                        alert(data.msg+" 按“确认/取消”键后刷新页面...");
                    }
                    else{
                        //$("#dialog p").text("提交失败，请稍后再试...");
                        alert("提交失败，请稍后再试...");
                    }
                }
            });

            $( "#dialog" ).dialog({
                autoOpen: false,
                width: 400,
                draggable: false,
                modal: true,
                close: function() {
                    location.href = "home/que";
                },
                buttons: [
                    {
                        text: "确定",
                        click: function() {
                            $( this ).dialog( "close" );
                            location.href = "home/que";
                        }
                    },
                    {
                        text: "取消",
                        click: function() {
                            $( this ).dialog( "close" );
                            location.href = "home/que";
                        }
                    }
                ]
            });

            $("#qsubmit").click(function(){
                $( "#dialog" ).dialog( "open" );
            });
        });
    </script>
</head>
<body style="overflow-x: hidden;overflow-y: auto">
    <%@ include file="/WEB-INF/view/head.jsp"%>
    <div class="editor">
        <p class="title"><strong>写下你的问题</strong></p>
        <form action="quiz" method="post">
            <p class="ed-que">请输入你的问题</p>
            <input type="text" name="title" size="45" maxlength="48" class="form-control"/>

            <p class="ed-que">请输入问题详情</p>
            <!-- 加载编辑器的容器 -->
            <script id="container" name="detail" type="text/plain"></script>
            <script src="${baseurl}js/queEditor.js"></script>

            <input type="button" value="提交问题" class="btn btn-success" id="qsubmit"/>
        </form>

        <div id="dialog" title="">
            <p></p>
        </div>
    </div>
</body>
</html>
