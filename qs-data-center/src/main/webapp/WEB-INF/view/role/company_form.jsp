<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
$(function() {
    webside.form.role.validateRoleForm();
});
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty companyEntity}">
		新增角色
		</c:if>
		<c:if test="${!empty companyEntity}">
		编辑角色
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="companyForm" name="companyForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty companyEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="roleId" value="${companyEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="userGroupName">分公司名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="companyName" id="companyName" type="text" 
		           value="${companyEntity.companyName }" placeholder="分公司名称..."/>
		      </div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#companyForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty companyEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty companyEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/sysCompany/toCompanyListUi.html<c:if test="${!empty companyEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script>


$('#companyForm').validate({
    errorElement : 'div',
    errorClass : 'help-block',
    focusInvalid : false,
    ignore : "",
    rules : {
    	companyName : {
            required : true,
            remote : {
                param : {
                    url : sys.rootPath + '/sysCompany/withoutAuth/validateRoleName.html',
                    cache : false
                }
            }
        },
        key : {
            required : true
        }
    },
    messages : {
    	companyName : {
            required : "请填写分公司名称",
            remote : "该分公司名称已存在"
        }
    },
    highlight : function(e) {
        $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
    },
    success : function(e) {
        $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
        $(e).remove();
    },
    errorPlacement : function(error, element) {
        error.insertAfter(element.parent());
    },
    submitHandler : function(form) {
        var roleId = $("#roleId").val();
        var url = "";
        if (roleId != undefined) {
            url = '/sysCompany/edit.html';
        } else {
            url = '/sysCompany/add.html';
        }
        webside.common.commit('companyForm', url, '/sysCompany/toCompanyListUi.html');
    }
});




</script>