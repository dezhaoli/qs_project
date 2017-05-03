<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script>
    $(function () {
        jeDate({
            dateCell: '#startTime',
            isinitVal:new Date(),
            //isinitVal:false,
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
        jeDate({
            dateCell: '#endTime',
            isinitVal:new Date(),
            //isinitVal:false,
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
    });
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/gold_add_log_list.js"></script>

<div class="page-content">
     <form class="form-horizontal" >
				<div class="form-group" >
		           	<label class="control-label col-sm-1 no-padding-right">开始日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="name" id="startTime" type="text"
	                               value="${record.name }" placeholder="开始日期..."/>
	                </div>
	                
	                <label class="control-label col-sm-1 no-padding-right">结束日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="code" id="endTime" type="text"
	                               value="${record.code }" placeholder="结束日期..."/>
	                </div>
	                <label class="control-label col-sm-1 no-padding-right">用户ID</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="code" id="mid" type="text"
	                               value="${record.code }" placeholder="用户ID..."/>
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
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">战绩查询</h4>
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
                        <%-- <input id="orderByColumn" type="hidden" value="${page.mid }"> --%>
                        <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                        <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

