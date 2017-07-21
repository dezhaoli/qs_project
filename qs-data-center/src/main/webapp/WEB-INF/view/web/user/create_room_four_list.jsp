<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/web/user/create_room_four_list.js"></script>

<style>
.col-sm-1{
width:80px;
}
</style>
<!-- 搜索框 -->
     <!-- <form class="form-horizontal" >
     		 <div class="form-group" >
	              	
	                <div class="col-sm-1">
		                <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
		               			 <i class="fa fa-search"></i>查询
	           			</button>
	                </div>
				</div>
				
	</form> -->

<input type="hidden" id="businessId4" value="${businessId}"/>
<input type="hidden" id="mid4" value="${mid}"/>
<input type="hidden" id="stime4" value="${stime}"/>
<input type="hidden" id="etime4" value="${etime}"/>
<input type="hidden" id="appId4" value="${appId}"/>
<input type="hidden" id="playId4" value="${playId}"/>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<!-- <div id="main" class="col-xs-11" style="width:100%;min-height: 400px;"></div> -->

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">直属玩家创建房间数据列表</h4>
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

<div class="center">
	<button id="btn" type="button" onclick="webside.common.loadPage('/createRoom/toCreateRoomThreeDetailsUi.html?stime='+$('#stime4').val()+'&etime='+$('#etime4').val()+'&appId='+$('#appId4').val()+'&playId='+$('#playId4').val()+'&businessId='+$('#businessId4').val())"
			class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

