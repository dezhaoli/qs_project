<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<div class="page-content" style="background: #e4e6e9">
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
			<input hidden="true" type="text" value="${aid} " id="id"  name="id">
            <div class="form-group">
            <div class="well" style="font-size: 10px;padding: 5px; margin-bottom: 7px;">提示：个人资料关系到您的返利提成发放，请务必仔细填写。</div>
                <label class="control-label col-sm-1 no-padding-right">真实姓名</label>
                
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="realname" id="realname" type="text"
                               value="" placeholder="您的真实姓名"/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">联系电话</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="phone" id="phone" type="text"
                               value="" placeholder="您的手机号码"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">银行卡号</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="bankcard" id="bankcard" type="text"
                               value="" placeholder="您的银行卡号"/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">开户银行</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="bank" name="bank" style="width: 100%">
                            <option value="中国邮政储蓄银行" >中国邮政储蓄银行</option>
                                <option value="中国工商银行" >中国工商银行</option>
                                <option value="中国建设银行" >中国建设银行</option>
                                <option value="中国农业银行" >中国农业银行</option>                
                                <option value="中国民生银行" >中国民生银行</option>
                                <option value="中国光大银行" >中国光大银行</option>
                                <option value="上海浦东发展银行" >上海浦东发展银行</option>
                                <option value="广东发展银行" >广东发展银行</option>                
                                <option value="招商银行" >招商银行</option>
                                <option value="中国银行" >中国银行</option>
                                <option value="交通银行" >交通银行</option>
                                <option value="华夏银行" >华夏银行</option>
                                <option value="平安银行" >平安银行</option>
                                <option value="兴业银行" >兴业银行</option>
                                <option value="上海银行" >上海银行</option>
                                <option value="中信银行" >中信银行</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">所在区域</label>
                <div class="col-sm-5">
                    <div class="clearfix">
							<select class="form-control" name=province id="province">
								<c:forEach var="item" items="${areaList}">
									<option value="-1">-请选择省份-</option>
									<option value="${item.code}">${item.aname}</option>
								</c:forEach>
							</select> 
							<br> 
							<select class="form-control" name="city" id="city">
								<option value="-1">-请选择城市-</option>
							</select> 
							<br> 
							<select class="form-control" name="county" id="county">
								<option value="-1">-请选择市区或县-</option>
							</select>
					</div>
					<br>
					  <button id="btnAdd" type="button" onclick="submits();" class="btn btn-success btn-sm">
				        <i class="fa fa-user-plus"></i>&nbsp;
				            添加
				    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<script>

 //提交表单
 var submits=function (){
	 var url="${ctx}/user/updateUserInfo.html";
	 /* var name=$("#name").val();
	 var phone=$("#phone").val();
	 var card=$("#card").val();
	 var bankName=$("#bankName option:selected").val();
	 var province_id=$("#province_id").val();
	 var city_id=$("#city_id").val();
	 var city_id_text=$("#city_id option:selected").text();
	 var county_id=$("#county_id").val();
	 
	 {
         "name":name, 
         "phone":phone,
         "card":card,
         "bankName":bankName,
         "province_id":province_id,
         "city_id":city_id,
         "city_id_text":city_id_text,
         "county_id":county_id
    }
	 */
	 var memberAgents =$("#storeForm").serialize();   
	 debugger;
	 $.ajax({
         type: "POST",
         url: url,
         data:memberAgents,
         dataType: "json",
         success: function (msg) {
        	 debugger;
        	 if (msg=="success"){
        		        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        		        parent.layer.close(index);
        	 }else {
        		 layer.msg(msg, {icon: 5});
        	 }
         }
         
     }); 
 }
 
//  地区
$("#province").change(function(){
	   loadCity($("#province").val());  
});

$("#city").change(function(){
	   loadcounty($("#city").val());  
});
function loadCity(parentId) {
	
	if (parentId != -1) {
			$.ajax({
				url : 'area.html?level=1&code=' + parentId,
				type : 'POST',
				dataType : 'JSON',
				timeout : 5000,
				error : function() {
					layer.msg("数据加载失败", {
						icon : 3
					});
				},
				success : function(msg) {
					$("#city").empty();
					$.each(eval(msg), function(i, item) {
						$(
								"<option value='"+item.code+"'>" + item.aname
										+ "</option>").appendTo("#city");
					});
				}
			});
		} else {
			return;
		}

	};

	function loadcounty(parentId) {
	if (parentId != -1) {
			$.ajax({
				url : 'area.html?level=2&code=' + parentId,
				type : 'POST',
				dataType : 'JSON',
				timeout : 5000,
				error : function() {
					layer.msg("数据加载失败", {
						icon : 3
					});
				},
				success : function(msg) {
					$("#county").empty();
					$.each(eval(msg), function(i, item) {
						$(
								"<option value='"+item.code+"'>" + item.aname
										+ "</option>").appendTo("#county");
					});
				}
			});
		} else {
			return;
		}

	};

	$(function() {
		$('#storeForm').validate(
				{
					errorElement : 'div',
					errorClass : 'help-block',
					focusInvalid : false,
					ignore : "",
					rules : {
						name : {
							required : true
						},
						phone : {
							required : true
						},
						card : {
							required : true
						},
						bankName : {
							required : true
						},
						cad : {
							required : true
						},
						province_id : {
							required : true
						},
						city_id : {
							required : true
						},
						county_id : {
							required : true
						},
					},
					messages : {
						name : "请填写用户名",
						phone : "请填写手机号",
						card : "请输入银行卡号",
						bankName : "请选择银行名"
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
					/* submitHandler: function (form) {
		                var url = "/user/updateUserInfo.html";
		                webside.common.commit('storeForm', url, '/login.html');
		                setInterval(closeLayer, 2000);
		            } */
				});

	});
</script>