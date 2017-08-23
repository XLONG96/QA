<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/23
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
