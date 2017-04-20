<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">配置文件内容</h3>
	</div>
	<div class="panel-body">
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12" >
		<form id="stockistForm" name="stockistForm" class="form-horizontal" role="form" method="post">
		   <div class="form-group">
			   ${content} 
		    </div>  



		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>

<div class="center">

	
</div>

</div>
</div>

<script type="text/javascript">

</script>