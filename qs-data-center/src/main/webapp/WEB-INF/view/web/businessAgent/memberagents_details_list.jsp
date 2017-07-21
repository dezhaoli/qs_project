<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/web/businessAgent/memberagents_details_list.js"></script>

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
<style>

</style>

<!-- 搜索框 -->
     <form class="form-horizontal" >
				<div class="form-group" >
		           	<label class="control-label col-sm-2 no-padding-right">商务名称</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="name" id="name" type="text"
	                               placeholder="商务名称..."/>
	                </div>
		           	<label class="control-label col-sm-2 no-padding-right">代理商创建时间(小于)</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="startDate" id="startDate" type="text"
	                               placeholder="代理商创建时间..."/>
	                </div>
				</div>
				<div class="form-group" >
	                <label class="control-label col-sm-2 no-padding-right">代理商直属会员充值金额(小于等于)</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="pamount" id="pamount" type="text" value =0
	                               placeholder="代理商充值金额..."/>
	                </div>
	                <label class="control-label col-sm-2 no-padding-right">代理商绑定人数(小于等于)</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="totals" id="totals" type="text" value =0
	                               placeholder="代理商绑定人数..."/>
	                </div>
	                <div class="col-sm-1">
	                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
	               			 <i class="fa fa-search"></i>查询
	           			</button>
	                </div>
				</div>
	</form>



<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">不合格代理商明细</h4>
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

				<!-- <div class="center">
				    <button id="btn" type="button" onclick="webside.common.loadPage('/memberagents/toMemberagentsListUi.html?gameType=runfast')"
				            class="btn btn-info btn-sm">
				        <i class="fa fa-undo"></i>&nbsp;返回
				    </button>
				</div> -->