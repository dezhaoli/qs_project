<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE HTML>
<html lang="en" ng-app="app">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content="QS">
	<meta name="description" content="QS">
	<title>AUSP</title>
	<!-- 这部分是UI用到的样式start-->
	<link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet">
	<link href="${ctx}/assets/css/bootstrap-theme.css" rel="stylesheet">
	<link href="${ctx}/assets/css/login.css" rel="stylesheet">
	<!-- 这部分是UI用到的样式end-->
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	  <script src="assets/js/html5shiv.min.js"></script>
	  <script src="assets/js/respond.min.js"></script>
	<![endif]-->
	<!--[if lt IE 9]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->

	<link rel="shortcut icon" href="${ctx}/assets/customize/favicon.ico">
	<%-- <link rel="icon" type="image/x-icon" href="${ctx}/favicon.ico"/> --%>
	<script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/cas.js"></script>
	<!--[if lt IE 9]>
		<script src="${ctx}/js/html5shiv-3.7.2.min.js" type="text/javascript"></script>
	<![endif]-->
	
<style type="text/css">
body {background-color: #CBE0C9;}
#msg {padding:20px; margin-bottom:10px;}
#msg.errors {border:1px dotted #BB0000; color:#BB0000; padding-left:100px; background:url(${ctx}/images/error.gif) no-repeat 20px center;}

.signinpanel .captcha {
	background:#fff url(${ctx}/assets/img/locked.png) no-repeat 95% center;
	color:#333
}
</style>

<script>
    if(window.top!==window.self){window.top.location=window.location};
</script>
</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
              <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>QS</h1>
                    </div>
                    <div class="m-b"></div>
                <h4>欢迎使用 <strong>QS AMS</strong></h4>
                    <ul class="m-b">
                        <li>介绍文字</li>
                        <li>介绍文字</li>
                        <li>介绍文字</li>
                        <li>介绍文字</li>
                        <li>介绍文字</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                    
				<form:form method="post" commandName="${commandName}" htmlEscape="true">
	                <h4 class="no-margins">登录：</h4>
	                <p class="m-t-md">登录到QS AMS</p>
					<!-- 
					cssClass用于指定表单元素CSS样式名,相当于HTML元素的class属性
					cssStyle用于指定表单元素样式,相当于HTML元素的style属性
					cssErrorClass用于指定表单元素发生错误时对应的样式
					path属性用于绑定表单对象的属性值,它支持级联属性,比如path="user.userName"将调用表单对象getUser.getUserName()绑定表单对象的属性值
					 -->
					<form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false"/>
					<input type="hidden" name="lt" value="${loginTicket}"/>
					<input type="hidden" name="execution" value="${flowExecutionKey}"/>
					<input type="hidden" name="_eventId" value="submit"/>
	                    
					<c:if test="${not empty sessionScope.openIdLocalId}">
						<strong>${sessionScope.openIdLocalId}</strong>
						<input type="hidden" name="username" value="${sessionScope.openIdLocalId}"/>
					</c:if>
					<c:if test="${empty sessionScope.openIdLocalId}">
						<form:input tabindex="1" path="username" class="form-control uname" placeholder="用户名" htmlEscape="true" maxlength="16" size="25" />
					</c:if>
					<form:password tabindex="2" path="password" class="form-control pword m-b" placeholder="密码" htmlEscape="true" maxlength="16" size="25"/>
	                <div class="col-xs-8" style="padding-left:0px;">
					<form:input tabindex="4" path="captcha" class="form-control captcha" placeholder="验证码" htmlEscape="true" maxlength="4" size="15"/>	
					</div>  
					<div class="col-xs-4" style="line-height:50px;padding:0px;">
					<img style="cursor:pointer;height:27px; vertical-align: bottom; margin-bottom:6px;" src="captcha.jsp" onClick="this.src='captcha.jsp?time'+Math.random();">
					</div>
					
	                <a href="#">忘记密码了？</a>
	                <button class="btn btn-primary btn-block">登录</button>
				</form:form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2016 All Rights Reserved. QS</div>
        </div>
    </div>
</body>
</html>