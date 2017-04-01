<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
$(function() {
  

});
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">运营参数配置</h3>
	</div>
	<div class="panel-body">
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12" >
		<form id="baseparamForm" name="baseparamForm" class="form-horizontal" role="form" method="post">
		    <c:forEach  var="item" items="${list}">
			<div class="form-group">
			      <label class="control-label col-sm-3 no-padding-right" for="name">${item.name}：</label>
			      <div class="col-sm-5">
			      <div class="clearfix">
			       <label class="control-label" onclick="">${item.value }</label> 
		      	  </div>
		      	</div>
		    </div>  
		  </c:forEach>  
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>


<div class="center">
	<button id="btnParamEidt" type="button"  onclick="webside.common.addModel('/baseparam/editUI.html');" class="btn btn-success btn-sm">
		修改参数配置
	
	</button>

</div>

</div>
</div>