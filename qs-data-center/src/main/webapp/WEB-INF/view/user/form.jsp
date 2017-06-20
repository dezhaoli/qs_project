<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
	    webside.form.user.validateUserForm();
	});
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty userEntity}">
		新增用户
		</c:if>
		<c:if test="${!empty userEntity}">
		编辑用户
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="userForm" name="userForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty userEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="userId" value="${userEntity.id }">
			<input type="hidden" name="userInfo.id" value="${userEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="accountName">账户</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input <c:if test="${!empty userEntity}">readonly</c:if> class="form-control" name="accountName" id="accountName" 
		           value="${userEntity.accountName }" placeholder="用户登录系统的账户..."/>
		      </div>
		      </div>
		   </div>
		   <c:if test="${empty userEntity}">
			<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="password">密码</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			         <input class="form-control" name="password" id="password" type="password"
			          placeholder="密码..."/>
		      	</div>
		      	</div>
		    </div>   
		    <div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="repassword">确认密码</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			         <input class="form-control" name="repassword" id="repassword" type="password"
			          placeholder="确认密码..."/>
		      	</div>
		      	</div>
		    </div>
		   </c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="userName">真实姓名</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="userName" id="userName" type="text"
		         value="${userEntity.userName }" placeholder="真实姓名..."/>
		      </div>
		      </div>
		   </div>   
		   
		   		   
		     <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="deleteStatus">状态</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      
		  	<select class="form-control" id="deleteStatus" name="deleteStatus" style="width: 100%">
						<option value="0" <c:if test="${userEntity.deleteStatus eq 0 }">selected="selected"</c:if>>启用</option>
						<option value="1" <c:if test="${userEntity.deleteStatus eq 1 }">selected="selected"</c:if>>禁用</option>
					</select>
		      </div>
		      </div>
		   </div>   
		   
		     <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="deleteStatus">是否商务</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		      
		  			<select class="form-control" id="ifBusiness" name="ifBusiness" style="width: 100%" onchange="getValue(this.value)">
						<option value="0" <c:if test="${userEntity.ifBusiness eq false }">selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${userEntity.ifBusiness eq true }">selected="selected"</c:if>>是</option>
					</select>
		      </div>
		      </div>
		   </div> 
		   
		    <div class="form-group" style="display:none" id="businessIdDiv">
		      <label class="control-label col-sm-1 no-padding-right" for="description">商务关联</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <select class="form-control" id="businessId" name="businessId" style="width: 100%">
				 </select>
		      </div>
		      </div>
		   </div> 
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="roleId">所属角色</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		        <select <c:if test="${userSession.role.name eq '超级管理员'}">readonly</c:if> class="form-control" name="role.id" id="roleId" style="width: 100%" >
					<option value=""></option>
					<c:choose>
						<c:when test="${!empty userEntity}">
							<c:forEach var="role" items="${roleList }">
								<c:choose>
									<c:when test="${userSession.role.name eq '超级管理员'}">
										<c:if test="${role.name ne '超级管理员'}">
											<option value="${role.id }" <c:if test="${userEntity.role.id eq role.id}">selected="selected"</c:if>>${role.name }</option>
										</c:if>
									</c:when>
									<c:when test="${userSession.role.name eq '管理员'}">
										<c:if test="${role.name ne '超级管理员' && role.name ne '管理员'}">
											<option value="${role.id }" <c:if test="${userEntity.role.id eq role.id}">selected="selected"</c:if>>${role.name }</option>
										</c:if>
									</c:when>
									<c:otherwise>
										<option value="${role.id }" <c:if test="${userEntity.role.id eq role.id}">selected="selected"</c:if>>${role.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>							
						</c:when>
						<c:otherwise>
							<c:forEach var="role" items="${roleList }">
								<c:choose>
									<c:when test="${userSession.role.name eq '超级管理员'}">
										<c:if test="${role.name ne '超级管理员'}">
											<option value="${role.id }">${role.name }</option>
										</c:if>
									</c:when>
									<c:when test="${userSession.role.name eq '管理员'}">
										<c:if test="${role.name ne '超级管理员' && role.name ne '管理员'}">
											<option value="${role.id }" >${role.name }</option>
										</c:if>
									</c:when>
									<c:otherwise>
										<option value="${role.id }">${role.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</select>
				</div>
		      </div>
		   </div> 
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">用户描述</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="description" id="description" type="text"
		         value="${userEntity.description }" placeholder="用户描述..."/>
		      </div>
		      </div>
		   </div> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#userForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty userEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty userEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/user/listUI.html<c:if test="${!empty userEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>


<script>
function getValue(_val){
	if(_val == 1){
		$('#businessIdDiv').css('display','block'); 
	}
	if(_val == 0){
		$('#businessIdDiv').css('display','none'); 
	}
}

$.ajax({
	type : "POST",
	url : sys.rootPath+"/user/businessList.html",
	dataType : "json",
	success : function(data) {
		$('#businessId').empty();   //清空resText里面的所有内容
        var a = "${userEntity.businessId}";
        var html = '<option value="" >请选择...</option>'; 
        $.each(data, function(commentIndex, comment){
        	if(a==comment.id){
        		html += '<option value="'+comment.id+'" selected="selected">' + comment.businessName
                + '</option>'; 
        	}else{
        		html += '<option value="'+comment.id+'">' + comment.businessName
                + '</option>'; 
        	}
             
        });
        $('#businessId').html(html);
     }

});
$(function(){ 
	var a = "${userEntity.ifBusiness}";
	if(a == 'true'){
		$('#businessIdDiv').css('display','block'); 
	}
})
</script>