<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/3
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common.jsp"%>
<html>
<head>
    <title>提问</title>
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

            <input type="submit" value="提交问题" class="btn btn-success"/>
        </form>
    </div>
</body>
</html>
