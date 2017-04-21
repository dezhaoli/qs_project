<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/resources/css/customer/commons.css"/>
    <script src="${ctx}/resources/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
</head>
<style>
    body {
        height: 100%;
        font-size: 12px;
        color: #2c3e50;
        background: #DBEDF9;
        overflow: hidden;
    }
</style>
<body>
<div class="wrap">
    <div class="main">
        <div class="top-header">
            <h4>乐玩互娱商务后台</h4>
        </div>
        <div class="container">
            <h4 class="game-title">请选择游戏</h4>
        </div>
        <div class="container">
            <ul class="nav menu">
                <c:forEach items="${games}" var="game">
                    <li><!-- gameCode:sc_majiang , gameType:5 -->
                        <a href="${href}?gameCode=${game.gameCode}&gameType=${game.gameType}">${game.gname}</a>
                    </li>
                </c:forEach>
                <%--<li>
                    <a href="http://qsadmin.jiaheyx.com/runfast/adminagents/?c=Game&a=gameChoice&game=runfast">跑得快/跑胡子</a>
                </li>
                <li>
                    <a href="http://qsadmin.jiaheyx.com/runfast/adminagents/?c=Game&a=gameChoice&game=majiang">湖南麻将</a>
                </li>--%>
            </ul>
        </div>
    </div>
</div>
<div class="footer">
    <div class="footer-inner">
        <div class="footer-content">
            <span class="bigger-110">
                <span class="blue bolder">版权所有&copy; 乐玩游戏网络技术有限公司</span>
            </span> &nbsp; &nbsp;
            <span class="action-buttons"> </span>
        </div>
    </div>
</div>
</body>

</html>