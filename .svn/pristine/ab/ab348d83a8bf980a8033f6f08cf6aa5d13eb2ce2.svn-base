<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />


<html>
<head>
	<meta charset="utf-8"> 
	<title></title>
</head>
<body>
<div class="table-responsive">
  <table class="table">
	<thead>
		<tr>
			<th>游戏ID</th>
			<th>游戏类型</th>
			<th>编码</th>
			<th>内容</th>
			<th>时间</th>
		</tr>
	</thead>
	<tbody>
	
	 <c:forEach  var="item" items="${resultList}">
		<tr>
			<td>${item.mid }</td>
			<td>
			  <c:if test="${item.gameType eq '2' }">牵手跑得快/跑胡子</c:if>
			  <c:if test="${item.gameType eq '3' }">疯狂斗牛OL</c:if>
			  <c:if test="${item.gameType eq '4' }">牵手湖南麻将</c:if>
			  <c:if test="${item.gameType eq '5' }">金溪麻将</c:if>
			  <c:if test="${item.gameType eq '9' }">牵手木虱</c:if>

			</td>
			<td>${item.code }</td>
		    <td>${item.desc }</td>
		    <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
		    
		</tr>
		
     </c:forEach>  

	</tbody>
</table>
</div>

</body>
</html>