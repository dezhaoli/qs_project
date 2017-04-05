<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<style>
body {
	background: #FFF;
}
</style>
<div class="page-content">
            <form action="" name="bind_phone_form" role="form" autocomplete="off">
                <div class="form-group col-xs-12">
                    <div class="row">
                        <label class="col-xs-3 col-sm-2" for="bindPhone" style="padding-right: 8px;line-height: 34px;">手机号码</label>
                        <div class="col-xs-4 col-sm-2" style="padding-left: 0px;padding-right: 2px;">
                            <input class="form-control captcha-phone" id="bindPhone" type="text" name="bind_phone" style="padding: 6px;" readonly="readonly">
                        </div>
                        <div class="col-xs-5 col-sm-1" style="padding-left: 10px;line-height: 34px;">
                            <input class="btn btn-primary table-btn" type="button" onclick="getPhoneCaptcha(this)" value="获取验证码">
                        </div> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindCaptcha">短信验证码</label>
                    <input class="form-control" id="bindCaptcha" type="text" name="bind_captcha" placeholder="短信验证码">
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindPasswd">密码</label>
                    <input class="form-control" id="bindPasswd" type="password" name="bind_passwd" placeholder="长度8-20位，不能含有空格">
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindRPasswd">确认新密码</label>
                    <input class="form-control" id="bindRPasswd"type="password" name="bind_rpasswd" placeholder="长度8-20位，不能含有空格">
                </div>
                <div class="form-group">
                    <input class="col-sm-12 col-xs-12 btn control-btn" id="bindPhoneBtn" type="button" value="绑定手机">
                </div>
            </form>
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
	 $.ajax({
        type: "POST",
        url: url,
        data:memberAgents,
        dataType: "json",
        success: function (msg) {
       	 if (msg.success==true){
	       		layer.open({
	    	        type: 2,
	    	        title:'完善用户信息',
	    	        area: ['90%','90%'],
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