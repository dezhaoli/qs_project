<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn" xmlns="http://www.w3.org/1999/xhtml">
<head>
   <%@include file="common/common.jspf" %>
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
            <h4 style="    font-size: 2em;">数据分析管理系统</h4>
        </div><div class="container">
    <h2 class="game-title" style="font-size: 1.5em;">请选择游戏</h2>
</div>
<div class="container">
    <ul class="nav menu">
      <c:forEach var="gameList"   items="${gameList }"  varStatus="s">
    		<li>
                <a href="${ctx}/refreshCache.html?gameType=${gameList.extend1 }&gameCode=${gameList.code }">${gameList.name }</a>
            </li>
    </c:forEach>
            <!--   <li>
                <a href="http://qsadmin.jiaheyx.com/runfast/adminagents/?c=Game&a=gameChoice&game=majiang">湖南麻将</a>
            </li> -->
            </ul>
</div>
</div>
</div>
 <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
					<span class="bigger-110"> <span class="blue bolder">版权所有&copy; 乐玩游戏网络技术有限公司</span>
					</span> &nbsp; &nbsp; <span class="action-buttons"> 
					</span>
            </div>
        </div>
    </div>
<!-- <div class="footer" style="">
    <div class="copyright">版权所有&copy;深圳市牵手互动网络科技有限公司  粤网文[2015]2099-443号</div>
</div> -->
</body>

</html>
<script>
</script>