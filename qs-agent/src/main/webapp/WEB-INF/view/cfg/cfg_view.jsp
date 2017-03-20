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
			<input type="hidden" name="id" id="id" value="">
		
		
				<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="bankName">房间配置</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			       <label class="control-label">
						   	<a href="${ctx}/cfg/configFile/downloadUI.html?fileName=${roomData}" class="btn btn-link">
							      下载 ${roomData}
							</a>   
								<br>
							<a href="${ctx}/cfg/configFile/downloadUI.html?fileName=${configXml}" class="btn btn-link">
							      下载 ${configXml}
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
			       
			       		   	<a href="${ctx}/cfg/configFile/downloadUI.html?fileName=${commonData}" class="btn btn-link">
							      下载 ${commonData}
							</a>   
								<br>
							<a href="${ctx}/cfg/configFile/downloadUI.html?fileName=${commonXml}" class="btn btn-link">
							      下载 ${commonXml}
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