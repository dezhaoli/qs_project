<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<style>
body {
	background: #FFF;
}
</style>
<div class="page-content proxy_padding">
<div class="row" style="margin-top:5px;">
        <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
				<div class="form-group">
<!-- 					<label class="control-label col-sm-1 no-padding-right">开始时间</label> -->
					<div class="col-sm-3">
							<input class="form-control" name="startTime" id="startTime" readonly="readonly"
								type="text" value="" placeholder="开始时间..." />
					</div>
<!-- 					<label class="control-label col-sm-1 no-padding-right">结束时间</label> -->
				<div class="col-sm-3">
				            <div class="input-group" style="width: 100%;">
				                <input class="form-control" name="endTime" id="endTime" readonly="readonly"
												type="text" value="" placeholder="结束时间..." />
				                <span class="input-group-btn">
				                <button id="queryBtn" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-primary query-btn" style="    padding: 2px 12px;">
									            查询
									    </button>
				                 </span>
				            </div>
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
                <td class="firpay">${resultMap.firpay} </td>
                <td>
                    <button class="btn-default table-btn agent_all" onclick="showPayDetail(1, '', '')" style=" font-size: 12px;height: 30px;　padding: 2px 12px !important;　　border: 0px solid;border-radius: 5px !important;color: #fff">充值明细</button>
                </td>
            </tr>
            <tr>
                <td>二级团队</td>
                <td class="secpay">${resultMap.secpay}</td>
                <td>
                    <button class="btn-default table-btn agent_all" onclick="showPayDetail(2, '', '')" style=" font-size: 12px;height: 30px;　padding: 2px 12px !important;　　border: 0px solid;border-radius: 5px !important;color: #fff">充值明细</button>
                </td>
            </tr>
            
             <c:choose>
					<c:when test="${gameType <20}">
			                <tr>
				                <td>三级团队</td>
				                <td class="thdpay">${resultMap.thdpay}</td>
				                <td>
				                    <button class="btn-default table-btn agent_all" onclick="showPayDetail(3, '', '')" style=" font-size: 12px;height: 30px;　padding: 2px 12px !important;　　border: 0px solid;border-radius: 5px !important;color: #fff">充值明细</button>
				                </td>
				            </tr>
			          </c:when> 
						<c:otherwise> 
				<!--   江西麻将文字说明 -->
			     
			       </c:otherwise> 
					</c:choose> 
            <tr>
                <td>总计</td>
                <td class="count">${resultMap.count}</td>
                <td>--</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
<script>


	$(function() {
		jeDate({
			dateCell : '#startTime',
			isinitVal : true,
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			minDate : '1900-06-01', //最小日期
			maxDate : '2050-06-01' //最大日期
		});
		jeDate({
			dateCell : '#endTime',
			isinitVal : true,
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			minDate : '1900-06-01', //最小日期
			maxDate : '2050-06-01' //最大日期
		});
	});
	var validateForm = $(function() {
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
					submitHandler : function(form) {
						submits();
					}
				});

	});

	//根据查询数据
	var submits = function() {
		var url = "${ctx}/agentroom/teamrechargecountbytime.html";

		var stratTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		
		$.ajax({
			type : "POST",
			url : url,
			data : {"startTime":stratTime,
				    "endTime":endTime},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					var phone = $(".firpay").text(msg.data.firpay);
					var card = $(".secpay").text(msg.data.secpay);
					var bankName = $(".thdpay").text(msg.data.thdpay);
					var province_id = $(".count").text(msg.data.count);
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 500
					});
				}
			}

		});
	}
	
	function showPayDetail(type){
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var title="";
		if (type==1){
			title="直属会员"
		}
		if (type==2){
			title="二级团队"
		}
		if (type==3){
			title="三级团队"
		}
		  layer.open({
		        type: 2,
		        title:title+'明细',
		        area: ['95%','90%'],
		        fixed: false, //不固定
		        maxmin: true,
		        content: sys.rootPath + '/agentroom/teamquerydetail.html?startTime='+startTime+'&endTime='+endTime+"&type="+type
		});
	}
	
</script>