<%@ page pageEncoding="UTF-8"%>
<%
 String service=request.getParameter("service");
 String url="http://"+request.getHeader("host")+"/cas/login?service="+service;
 System.out.println("cas logout url==========::"+url);
 String referer = request.getHeader("Referer");
 response.sendRedirect(url);
%>
<body style="background-color:#CBE0C9;">
	<span style="color:red; font-size:64px; font-weight:bold;">登出成功</span>
</body>