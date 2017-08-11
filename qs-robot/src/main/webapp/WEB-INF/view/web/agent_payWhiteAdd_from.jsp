<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<style>
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
.padd{padding-top: 8px}
</style>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
				
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">用户添加</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> 
						<i class="ace-icon fa fa-arrows-alt"></i>
					</a> 
					<a href="#" data-action="collapse" class="green"> 
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>
		</div>
			
	            <form name="bind_phone_form"  id="storeFormEdit" role="form" method="post">
	            <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
	                <div class="form-group">
	                    <label class="control-label" for="bindCaptcha">用户mid</label>
                        <input class="form-control" name="mid" id="mid" type="number"  placeholder="请输入用户MID..." />	                </div>
	                <div class="form-group">
	                    <label class="control-label" for="bindPasswd">用户金额</label>
						<input class="form-control" name="money" id="money" type="number" placeholder="请输入用户金额..." />	                </div>
	                <div class="form-group">
	                    <label class="control-label" for="bindRPasswd">游戏类型</label>
	                    <select class="form-control" name="gameType" id="gameType">
								<option value=" ">-游戏类型-</option>
								<option value="2">牵手跑得快/胡子</option>
								<option value="3">疯狂斗牛OL</option>
								<option value="4">牵手湖南麻将</option>
								<option value="9">牵手木虱</option>
								<option value="5">金溪麻将</option>
						</select>
	                </div>
	                <div class="form-group">
	                    <div  style="width: 100%;height: 50px">
								<button class="btn  btn-primary btn-sm" type="button" id="bindPhoneBtn" 
									onclick="javascript:$('#storeFormEdit').submit();">
									<i class="icon-trash bigger-200"></i>提交
								</button>
							</div>
	                </div>
	            </form>
			
			
	</div>
	
	
</div>
<script>
//提交用户信息
  $(function () {
	  
        $('#storeFormEdit').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	mid: {
                    required: true
                },
                money: {
                    required: true
                },
                gameType: {
                    required: true,
                    number:true
                }
            },
            messages: {
            	mid: "mid不能为空",
            	money: "金额不能为空",
                gameType:{
                	required:"对应Game值",
                	number:"请输入数字"
                }
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
            	var mid = $("#mid").val();
        		var money = $("#money").val();
        		var gameType = $("#gameType").val();
        		if(mid =="" || mid <=0){
        			 layer.msg("mid不能为空！", {icon: 5,time:1000});
        			 return;
        		}
        		if(money =="" || money <=0){
        			 layer.msg("金额不能为空！", {icon: 5,time:1000});
        			 return;
        		}
        		if(gameType =="" || gameType <=0){
        			 layer.msg("请选择游戏类型！", {icon: 5,time:1000});
        			 return;
        		}
            	var url="/payWhite/insertSelectiveInfo.html"
                webside.common.commit('storeFormEdit', url, '/payWhite/getPayWhiteInfoList.html');
            }
        });


    }); 
	 /* function submitPayWhite() {

		var mid = $("#mid").val();
		var money = $("#money").val();
		var gameType = $("#gameType").val();
		var url="${ctx}/payWhite/insertSelectiveInfo.html";
		debugger;
		if(mid =="" || mid <=0){
			 layer.msg("mid不能为空！", {icon: 5,time:1000});
			 return;
		}
		if(money =="" || money <=0){
			 layer.msg("金额不能为空！", {icon: 5,time:1000});
			 return;
		}
		if(gameType =="" || gameType <=0){
			 layer.msg("请选择游戏类型！", {icon: 5,time:1000});
			 return;
		}
		 $.ajax({
			    type: "POST",
			    url: url,
			    data:{"mid":mid,
			          "money":money,
			          "gameType":gameType},
			    dataType: "json",
			    success: function (msg) {
			   	 if (msg.success==true){
			   		 layer.msg(msg.message, {icon: 6,time:1000});
			   		// window.location.href="${ctx}/payWhite/getPayWhiteInfoList.html";
			   		 webside.common.loadPage("/payWhite/getPayWhiteInfoList.html");
			   	 }else {
			   		 layer.msg(msg.message, {icon: 5,time:1000});
			   	 }
			    }
			    
			});
	}  */
</script>

