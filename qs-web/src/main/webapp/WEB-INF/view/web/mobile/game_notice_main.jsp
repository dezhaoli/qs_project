<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/game_notice_main.js"></script>
        
<!-- 头部 -->
<div class="page-header">
	  <button id="btnAdd" type="button" onclick="webside.common.addModel('/game/notice/gameNoticeAddUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	 </button>
	 <button id="btnEdit" type="button" onclick="edit();" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>
	
	<button id="btnDel" type="button" onclick="executeJob('/scheduleJob/pauseJob.html')" class="btn btn-warning btn-sm">
			<i class="fa fa-pause"></i>&nbsp;暂停
		</button>
		<button id="btnDel" type="button" onclick="executeJob('/scheduleJob/resumeJob.html')" class="btn btn-success btn-sm">
			<i class="fa fa-play"></i>&nbsp;恢复
		</button>
	 <!-- <button id="btnEdit" type="button" onclick="webside.common.editModel('/dict/viewUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;查看
	</button> -->
	
</div>

<!-- 搜索框 -->
<!-- <div class="input-group">
     <input id="searchKey" type="text" class="input form-control" placeholder="标题...">
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div> -->

<form class="form-horizontal" >
				<div class="form-group" >
		           	<label class="control-label col-sm-1 no-padding-right">标题</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="title" id="title" type="text"
	                               value="${record.title }" placeholder="标题..."/>
	                </div>
	                
	                <label class="control-label col-sm-1 no-padding-right">请选择发布类型</label>
	                <div class="col-sm-2">
	                    <select class="form-control" name="pushType" id="pushType" >
	                    		<option value="">请选择...</option>
	                    		<option value="1">发布在线公告</option>
	                    		<option value="3">发布定时公告</option>
	                    </select>
	                </div>
	                <div class="col-sm-3">
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
				<h4 class="widget-title lighter">游戏公告</h4>
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

<script type="text/javascript">
function edit(){
	var rows = grid.getCheckedRecords();
	 if (rows.length != 1) {
		 layer.msg("你没有选择行或选择了多行数据", {
             icon : 0
         });
		 return;
     }
	var pushType = rows[0].pushType;
	if(pushType==3){
		webside.common.editModel('/game/notice/gameNoticeUpdateUI.html');
	}else{
		layer.msg("只有【发布定时公告】能够编辑！", {
            icon : 0
        });
	}
	
	
}
</script>

