<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
$(function() {
    //webside.form.role.validateRoleForm();
    
     $("#btnAdd").click(function(){
 		var list=$.map($("input[name=param]"), function(v, i) {  
			 var $v = $(v);
			  return {id:$v.attr("idvalue"),value:$v.val()}
		});   
 		$.ajax({  
		    type: "POST",  
		    url: "${ctx}/baseparam/updateBatch.html",  
		    data: JSON.stringify(list),//将对象序列化成JSON字符串  
		    dataType:"json",  
		    contentType : 'application/json;charset=utf-8', //设置请求头信息  
		    success: function(data){  
		    	layer.msg('保存成功', {
	    			icon : 5
	    		}); 
		    	webside.common.loadPage('/baseparam/baseparam.html');
		    	
		    },  
		    error: function(res){  
		      
		    }  
		});  	
 		
     });
    
});
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">运营参数配置</h3>
	</div>
	<div class="panel-body">
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		
		
			<form id="baseparamForm" name="baseparamForm" class="form-horizontal" role="form" method="post">
		    <c:forEach  var="item" items="${list}">
			<div class="form-group">
			      <label class="control-label col-sm-3 no-padding-right" for="name">${item.name}：</label>
			      <div class="col-sm-5">
			      <div class="clearfix">
			         <input class="form-control" name="param" id="param" type="text" idvalue="${item.id}"
			          value="${item.value}" />
		      	  </div>
		      	</div>
		    </div>  
		  </c:forEach>  
		</form>

		<div class="hr hr-dotted"></div>
	</div>
</div>

<div class="center">
	<button id="btnAdd" type="button"  class="btn btn-success btn-sm">
		保存
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/baseparam/baseparam.html');" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
</div>
</div>