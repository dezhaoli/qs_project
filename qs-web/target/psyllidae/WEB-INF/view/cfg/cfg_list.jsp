<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">生成配置文件</h3>
	</div>
	<div class="panel-body">
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12" >
		<form id="stockistForm" name="stockistForm" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="id" value="${stockist.id }">
		
		
				<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="bankName">房间配置</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			       <label class="control-label">
			       		<a href="javascript:void(0)" class="btn btn-link" 
			       			onclick="webside.common.addModel('/cfg/configFile/add.html')">
								点击生成
							</a>
			       </label> 
		      	</div>
		      	</div>
		    </div>  
            
            <div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="bankName">常用配置</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			       <label class="control-label">
			       
			       	<a href="javascript:void(0)" class="btn btn-link" 
			       			onclick="webside.common.addModel('/cfg/configFile/add.html')">
								点击生成
				</a>
							
		      	</div>
		      	</div>
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