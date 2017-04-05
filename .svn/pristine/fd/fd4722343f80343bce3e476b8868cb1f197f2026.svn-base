<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/common/common.jspf" %>
<style>
body {
	background: #FFF;
}
</style>
<div class="page-content">
	<div id="block_1">
		<form action="" name="check_phone_form" role="form" autocomplete="off" id="storeForm">
			<div class="form-group">
				<label for="checkPhone">手机号码</label> <input class="form-control"
					id="phone" type="text" name="phone" placeholder="手机号码">
			</div>
			<div class="form-group">
				<input class="btn btn-primary col-xs-12" id="checkPhoneBtn"
					type="button" onclick="submits()" value="下一步">
			</div>
		</form>

	</div>
</div>
<script>
var validateForm= $(function() {
		$('#storeForm').validate(
				{
					errorElement : 'div',
					errorClass : 'help-block',
					focusInvalid : false,
					ignore : "",
					rules : {
						phone : {
							required : true
						}
					},
					messages : {
						phone : "请填写手机号",
					},
					highlight : function(e) {
						$(e).closest('.form-group').removeClass('has-info')
								.addClass('has-error');
					},
					success : function(e) {
						$(e).closest('.form-group').removeClass('has-error')
								.addClass('has-success');
						$(e).remove();
					},
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					},
					 submitHandler: function (form) {
						 submits();
		            } 
				});

	});
var submits=function (){
	 
	 var url="${ctx}/user/bindPhoneUi.html";
	 var phone =$("#phone").val();   
	 debugger
	 $.ajax({
        type: "POST",
        url: url,
        data:{"phone":phone},
        dataType: "json",
        success: function (msg) {
       	 if (msg.success==true){
	       		layer.open({
	    	        type: 2,
	    	        title:'完善用户信息',
	    	        area: ['80%','80%'],
	    	        fixed: false, //不固定
	    	        maxmin: true,
	    	        content: sys.rootPath + '/user/bindPhoneEdit.html?phone'+phone
	    	});
       	 }else {
       		 layer.msg(msg.message, {icon: 5,time:500});
       	 }
        }
        
    }); 
}
</script>