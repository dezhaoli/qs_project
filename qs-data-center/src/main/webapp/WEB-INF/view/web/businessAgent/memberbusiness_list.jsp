<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/web/businessAgent/memberbusiness_list.js"></script>

<!-- 头部 -->
<!-- <div class="page-header">
	  <button id="btnAdd" type="button" onclick="webside.common.addModel('/dict/toAdd.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	 </button>
	 <button id="btnEdit" type="button" onclick="webside.common.editModel('/dict/editUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>
	 <button id="btnEdit" type="button" onclick="webside.common.editModel('/dict/viewUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;查看
	</button>
</div> -->

<!-- 搜索框 -->
     <form class="form-horizontal" id="exportAction" action="businessCount/exportAll.html" >
				<div class="form-group" >
					  <%--<form-groupdiv class="col-sm-2" id="divGroupId">
	                    <div class="clearfix">
	                     <select class="form-control" id="groupIdBusiness" name="groupIdBusiness" style="width: 100%" onchange="selectBusiness(this.value)">
					 	 </select>
	                    </div>
		              </form-groupdiv>
		                <div class="col-sm-1" id="divBusinessId">
		                    <div class="clearfix">
		                     <select class="form-control" id="businessIdByGroupId" name="businessIdByGroupId" style="width: 100%">
						 	 </select>
		                    </div>
		                </div>--%>
		           <!-- 	<label class="control-label col-sm-1 no-padding-right">商务名称</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="name" id="name" type="text"
	                               placeholder="商务名称..."/>
	                </div> -->
	                <label class="control-label col-sm-1 no-padding-right">开始日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="startDate" id="startDate" type="text"
	                               placeholder="日期..."/>
	                </div>
	                 <label class="control-label col-sm-1 no-padding-right">结束日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="endDate" id="endDate" type="text"
	                               placeholder="日期..."/>
	                </div>
	                <div class="col-sm-1">
	                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
	               			 <i class="fa fa-search"></i>查询
	           			</button>
	                </div>
	               <%-- <div class="col-sm-2">
	                    <button id="btnExport" class="btn btn-primary btn-sm" type="button">
	               			 导出全部代理商周统计
	           			</button>
	                </div>--%>
				</div>
	</form>

<input type="hidden" id="date" value="${date}"/>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">商务业绩统计</h4>
				<strong style="color:green; margin-left: 150px">
						商务充值总金额：<span id="countTotals"></span>
				</strong>
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


