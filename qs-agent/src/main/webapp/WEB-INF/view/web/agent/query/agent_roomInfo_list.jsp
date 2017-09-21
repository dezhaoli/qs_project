<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<style>
 body { 
 	    background: #fff;
 } 
 input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
</style>
		 <script type="text/javascript"
        src="${ctx }/resources/js/customer/web/agent_roomInfo_list2.js"></script> 
<div class="page-content">
<div class="row" style="margin-top:5px;padding: 0px 12px;">
	        <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
					<div class="form-group">
					
						<div class="col-sm-3">
								<input readonly="readonly" class="form-control" name="startTime" id="startTime"
									type="text" value="" placeholder="开始时间..." />
						</div>
					<div class="col-sm-3">
					            <div class="input-group" style="width: 100%;">
					                <input readonly="readonly" class="form-control" name="endTime" id="endTime" 
													type="text" value="" placeholder="结束时间..." />
					            </div>
					    </div> 
					    <div class="col-sm-3">
				            <div class="input-group" style="width: 100%;">
				                <input class="form-control" name="mid" id="mid" 
												type="number" value=""  placeholder="请输入mid" />
				                <span class="input-group-btn">
				                <button id="btnSearch" type="button"  class="btn btn-primary query-btn" style="    padding: 2px 12px;">
									            查询
									    </button>
				                 </span>
				            </div>
				    </div> 
					</div>
	        </form>
</div>
    <div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
     总开房次数：<span id="oprenRoom">${resultParam.oprenRoom }
	       <%-- <c:choose>
	       <c:when test=" ${resultParam.oprenRoom>0 }"> ${resultParam.oprenRoom }</c:when>
	       <c:when test="${resultParam.oprenRoom<0 || resultParam.oprenRoom=='' }">0 </c:when>
	       </c:choose> --%>
        </span>&nbsp;&nbsp;&nbsp;总消耗:-<span id="countGold">${resultParam.countGold }</span>
    </div>
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                <h4 class="widget-title lighter">查看代开房信息(包含自己并只保留最近40天的代开房数据)</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
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
        </div>
    </div>

</div>

<script>
/* 
$(function() {
	jeDate({
		dateCell:"#startTime",//isinitVal:true,
		format:"YYYY-MM-DD",
		isinitVal:true,
		 //isClear:false,
		isClear:false,
		minDate:"2014-09-19"
	});
	jeDate({
		dateCell : '#endTime',
		format:"YYYY-MM-DD",
		isinitVal:true,
		 //isClear:false,
		isClear:false,
		minDate:"2014-09-19"
	});
}); */
 $(function() {
	jeDate({
		dateCell:"#startTime",//isinitVal:true,
		format:"YYYY-MM-DD hh:mm:ss",
		isinitVal:true,
		isTime:true, //isClear:false,
		minDate:"2014-09-19"
	});
	jeDate({
		dateCell : '#endTime',
		//format:"YYYY-MM-DD hh:mm:ss",
		format:"YYYY-MM-DD hh:mm:ss",
		isinitVal:true,
		isTime:true, //isClear:false,
		//minDate:"2014-09-19 00:00:00"
		minDate:"2014-09-19"
	});
}); 

function getParam(){
	  var user1 =$("#mid").val();
	  var sDate= $('#startTime').val();
	  var eDate = $('#endTime').val();
	  var url=sys.rootPath + '/agentroom/getCountOpenRoom.html';
		$.ajax({
		type : "POST",
		url : url,
		data : {"user1":user1,"sDate":sDate,"eDate":eDate},
		dataType : "json",
		success : function(msg) {
			
			if (msg.success=true){
		    	$("#oprenRoom").text(msg.mgr.oprenRoom );
		    	$("#countGold").text(msg.mgr.countGold );
			}else {
				$("#oprenRoom").text(msg.mgr.oprenRoom );
	    	    $("#countGold").text(msg.mgr.countGold );
			}
		}

	});
}
$(document).ready(function(){
getParam();
	});
	
function showSettleDetail(mid){
	  var sDate= $('#startTime').val();
	  var eDate = $('#endTime').val();
	  layer.open({
	        type: 2,
	        title:"开房详情列表",
	        area: ['95%','90%'],
	        fixed: false, //不固定
	        maxmin: true,
	        content: sys.rootPath + '/agentroom/agentRoomDetailUi.html?startTime='+sDate+"&endTime="+eDate+"&mid="+mid
	}); 
}
</script>
