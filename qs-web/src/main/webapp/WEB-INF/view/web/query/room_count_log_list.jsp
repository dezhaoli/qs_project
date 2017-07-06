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
            isinitVal:saveDate,
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
        jeDate({
            dateCell: '#endTime',
            isinitVal:new Date(),
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
    });


    var endDate = $("#endTime").val();

    var saveDate = new Date(Date.parse(endDate.replace(/-/g, "/")));
    saveDate.setDate(saveDate.getDate()-6);

    var year = saveDate.getFullYear();
    var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
    var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

    startDate = year + "-" + month + "-" + date;
    $('#startTime').val(startDate);
</script>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/query/room_count_log_list.js"></script>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/query/room_count_log_list1.js"></script>

<div class="page-content">
     <form class="form-horizontal" >
				<div class="form-group" >
		           	<label class="control-label col-sm-1 no-padding-right">开始日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="name" id="startTime" type="text"
	                                placeholder="开始日期..."/>
	                </div>
	                
	                <label class="control-label col-sm-1 no-padding-right">结束日期</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="code" id="endTime" type="text"
	                               placeholder="结束日期..."/>
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
                    <h4 class="widget-title lighter">房卡统计</h4>
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

