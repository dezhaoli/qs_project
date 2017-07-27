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
		<c:if test="${empty groupEntity}">
		新增分组
		</c:if>
		<c:if test="${!empty groupEntity}">
		编辑分组
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="groupForm" name="groupForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty groupEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="roleId" value="${groupEntity.id }">
		</c:if>
			    <div class="form-group" >
			           	<label class="control-label col-sm-1 no-padding-right">组名称</label>
		                <div class="col-sm-11">
		                    <input class="form-control" name="groupName" id="groupName" type="text"
		                         value="${groupEntity.groupName }"    placeholder="组名称..." required="required"/>
		                </div>
				</div>
			
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#groupForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty groupEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty groupEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/game/toGameGroupListUi.html<c:if test="${!empty groupEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script>


$('#groupForm').validate({
    errorElement : 'div',
    errorClass : 'help-block',
    focusInvalid : false,
    ignore : "",
    rules : {
    	groupName : {
            required : true,
            remote : {
                param : {
                    url : sys.rootPath + '/game/withoutAuth/validateGroupName.html',
                    cache : false
                },
                depends : function() {
                    return typeof ($("#roleId").val()) == "undefined";
                }
            }
        },
        key : {
            required : true
        }
    },
    messages : {
    	groupName : {
            required : "请填写组名称",
            remote : "该组名称已存在"
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
            url = '/game/edit.html';
        } else {
            url = '/game/add.html';
        }
        webside.common.commit('groupForm', url, '/game/toGameGroupListUi.html');
    }
});




</script>