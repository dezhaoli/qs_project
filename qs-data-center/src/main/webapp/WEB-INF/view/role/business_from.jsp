<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">
/* $(function() {
    webside.form.role.validateRoleForm();
}); */
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty businessEntity}">
		新增商务
		</c:if>
		<c:if test="${!empty businessEntity}">
		编辑商务
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="businessForm" name="businessForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty businessEntity}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="roleId" value="${businessEntity.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="name">商务编码</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="businessId" id="businessId" type="text" 
		           value="${businessEntity.businessId }" placeholder="商务编码..."/>
		      </div>
		      </div>
		   </div>
			<div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="key">商务名称</label>
			      <div class="col-sm-10">
			      <div class="clearfix">
			         <input class="form-control" name="businessName" id="businessName" type="text"
			          value="${businessEntity.businessName }" placeholder="商务名称..."/>
		      	</div>
		      	</div>
		    </div>  
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">游戏名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="description" id="description" type="text"
		         value="" placeholder="游戏名称..."/>
		      </div>
		      </div>
		   </div> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#businessForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty businessEntity}">
		添加
		</c:if>
	  	<c:if test="${!empty businessEntity}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/sysBusiness/toBusinessListUi.html<c:if test="${!empty businessEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script>


$('#businessForm').validate({
    errorElement : 'div',
    errorClass : 'help-block',
    focusInvalid : false,
    ignore : "",
    rules : {
    	businessId : {
            required : true,
            number : true
        },
        businessName : {
            required : true,
        }
    },
    messages : {
    	businessId : {
            required : "请填写商务ID",
            number : "请输入数字"
        },
        businessName : {
            required : "请填写商务名称",
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
            url = '/sysBusiness/edit.html';
        } else {
            url = '/sysBusiness/add.html';
        }
        webside.common.commit('businessForm', url, '/sysBusiness/toBusinessListUi.html');
    }
});

</script>