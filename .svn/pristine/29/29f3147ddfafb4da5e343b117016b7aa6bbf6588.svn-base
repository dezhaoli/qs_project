<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/selectMyPayRecord_list.js"></script>
<style>
 body { 
 	background: #FFF; 
 } 
</style>
<div class="page-content" style="">
	<div class="row" style="margin-top:5px;">
	        <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
					<div class="form-group">
						<div class="col-sm-3">
								<input class="form-control" name="sDate" id="sDate"
									type="text" value="" placeholder="开始时间..." />
						</div>
					<div class="col-sm-3">
					            <div class="input-group" style="width: 100%;">
					                <input class="form-control" name="eDate" id="eDate" 
													type="text" value="" placeholder="结束时间..." />
					                <span class="input-group-btn">
					                <button id="btnSearch" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-primary query-btn" style=" padding: 2px 12px;">
										            查询
										    </button>
					                 </span>
					            </div>
					    </div> 
					</div>
	        </form>
	</div>
	
	 <div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <input id="pageNum" type="hidden" value="${page.pageNum }">
                        <input id="pageSize" type="hidden" value="${page.pageSize }">
                        <input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
                        <input id="orderByType" type="hidden" value="${page.orderByType }">
                        <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                        <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                    </div>
      </div>
	
</div>
<script>


	$(function() {
		jeDate({
			dateCell : '#sDate',
			isinitVal : false,
			format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			minDate : '1900-06-01', //最小日期
			maxDate : '2050-06-01' //最大日期
		});
		jeDate({
			dateCell : '#eDate',
			isinitVal : false,
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