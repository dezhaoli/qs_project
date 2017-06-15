<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/agent/agent_userGrade_details_list.js"></script>

	<input type="hidden" id="belongid" value="${id}"/>
	<input type="hidden" id="currdate" value="${date}"/>
	<input type="hidden" id="grade" value="${grade}"/>


	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="chartsSecondId" class="col-xs-12" style="min-height: 300px;"></div>

	<div class="row" style="margin-top:5px;">
		<div class="col-xs-12 widget-container-col ui-sortable"
			style="min-height: 127px;">
			<div class="widget-box transparent ui-sortable-handle"
				style="opacity: 1; z-index: 0;">
				<div class="widget-header">
					<h4 class="widget-title lighter">星级代理商明细</h4>
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
				    <button id="btn" type="button" onclick="webside.common.loadPage('/weekdown/userGradeUi.html')"
				            class="btn btn-info btn-sm">
				        <i class="fa fa-undo"></i>&nbsp;返回
				    </button>
				</div>
