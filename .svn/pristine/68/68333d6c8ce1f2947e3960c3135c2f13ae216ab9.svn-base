<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/web/user/user_add_log_list.js"></script>
<input type="hidden" name="startTime" id="startTime" type="text"/>
<input type="hidden" name="endTime" id="endTime" type="text"/>

<!-- 搜索框 -->
     <form class="form-horizontal" >
				<div class="form-group" >
		           <!-- 	<label class="control-label col-sm-1 no-padding-right">开始日期</label> -->
	                <div class="col-sm-2">
	                	<select class="form-control" name="date" id="date">
	                		<option value="">请选择日期...</option>
	                		<option value="60">过去60天</option>
	                		<option value="30">过去30天</option>
	                		<option value="7">过去7天</option>
	                		<option value="0">今日</option>
	                	</select>
	                </div>
	                <div class="col-sm-3">
	                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
	               			 <i class="fa fa-search"></i>查询
	           			</button>
	                </div>
				</div>
	</form>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" class="col-xs-11" style="width:100%;min-height: 300px;"></div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">新增用户列表</h4>
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
