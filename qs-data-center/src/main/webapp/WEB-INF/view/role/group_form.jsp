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
		新增角色
		</c:if>
		<c:if test="${!empty groupEntity}">
		编辑角色
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
		                    <input class="form-control" name="userGroupName" id="userGroupName" type="text"
		                         value="${groupEntity.userGroupName }"    placeholder="组名称..." required="required"/>
		                </div>
				</div>
			   <div class="form-group" >
			           	<label class="control-label col-sm-1 no-padding-right">游戏类型</label>
		                <div class="col-sm-11">
		                               <select class="form-control" name="gameType" id="gameType" required="required">
		                               		<option value="">请选择...</option>
		                               		<!-- <option value="2">跑得快</option>
		                               		<option value="3">斗牛</option>
		                               		<option value="4">麻将</option>
		                               		<option value="5">金溪麻将</option>
		                               		<option value="9">潮汕木虱</option>
		                               		<option value="105">四川麻将</option>
		                               		<option value="106">广东麻将</option> -->
		                               		<option value="beardkx" <c:if test="${groupEntity.gameType eq 'beardkx' }">selected="selected"</c:if>>开心乐玩</option>
		                               		<option value="gd_majiang_pub" <c:if test="${groupEntity.gameType eq 'gd_majiang_pub' }">selected="selected"</c:if>>广东乐玩</option>
		                               		<option value="majiangjx" <c:if test="${groupEntity.gameType eq 'majiangjx' }">selected="selected"</c:if>>江西乐玩</option>
		                               		<option value="runfast" <c:if test="${groupEntity.gameType eq 'runfast' }">selected="selected"</c:if>>湖南牵手</option>
		                               </select>
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
		<button id="btn" type="button" onclick="webside.common.loadPage('/group/toGroupListUi.html<c:if test="${!empty groupEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
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
    	userGroupName : {
            required : true,
            remote : {
                param : {
                    url : sys.rootPath + '/group/withoutAuth/validateRoleName.html',
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
    	userGroupName : {
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
            url = '/group/edit.html';
        } else {
            url = '/group/add.html';
        }
        webside.common.commit('groupForm', url, '/group/toGroupListUi.html');
    }
});




</script>