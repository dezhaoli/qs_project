<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<style>
body {
	background: #FFF;
}

</style>
<div class="page-content" style="">
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right">开始时间</label>
					<div class="col-sm-3">
						<div class="clearfix">
							<input class="form-control" name="startTime" id="startTime"
								type="text" value="" placeholder="开始时间..." />
						</div>
					</div>
					<label class="control-label col-sm-1 no-padding-right">结束时间</label>
					<div class="col-sm-3">
						<div class="clearfix">
							<input class="form-control" name="endTime" id="endTime"
								type="text" value="" placeholder="结束时间..." />
						</div>
						<br>
						  <button id="queryBtn" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-primary query-btn">
					            查询
					    </button>
					</div> 
				</div>
        </form>
        <div class="query-content">
        <table class="table table-condensed table-hover">
            <thead>
            <tr>
                <th></th>
                <th>充值总额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>直属会员</td>
                <td>${resultMap.firpay} </td>
                <td>
                    <button class="btn btn-default table-btn" onclick="showPayDetail(1, '', '')">充值明细</button>
                </td>
            </tr>
            <tr>
                <td>二级团队</td>
                <td>${resultMap.secpay}</td>
                <td>
                    <button class="btn btn-default table-btn" onclick="showPayDetail(2, '', '')">充值明细</button>
                </td>
            </tr>
            <tr>
                <td>三级团队</td>
                <td>${resultMap.thdpay}</td>
                <td>
                    <button class="btn btn-default table-btn" onclick="showPayDetail(3, '', '')">充值明细</button>
                </td>
            </tr>
            <tr>
                <td>总计</td>
                <td></td>
                <td>--</td>
            </tr>
            </tbody>
        </table>
    </div>
    </div>
</div>
</div>
<script>

$(function () {
    jeDate({
        dateCell: '#startTime',
        isinitVal: false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#endTime',
        isinitVal: false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});
	var validateForm= $(function() {
		$('#storeForm').validate(
				{
					errorElement : 'div',
					errorClass : 'help-block',
					focusInvalid : false,
					ignore : "",
					rules : {
						name : {
							required : true
						}
					},
					messages : {
						name : "请填写用户名"
						
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
						 var url = "/agentroom/teamrechargecount.html";
			                webside.common.commit('storeForm', url, '/agentroom/teamrechargecount.html');
			                setInterval(closeLayer, 2000);
		            } 
				});

	});
	
	
</script>