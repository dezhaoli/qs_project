<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/orderDetail_query_list.js"></script>

<div class="page-content">
    <div class="row">
        <div class="col-sm-1">
            <div class="input-group">
                <input class="form-control" name="mid" id="mid" type="number"
                       value="" placeholder="请输入mid..."/>
            </div>
        </div>
        <div class="col-sm-1">
            <div class="input-group">
                <input class="form-control" name="parentid" id="parentid" type="number"
                       value="" placeholder="请输入父ID..."/>
            </div>
        </div>
        <div class="col-sm-1">
            <div class="input-group">
                <input class="form-control" name="realname" id="realname" type="text"
                       value="" placeholder="请输入姓名..."/>
            </div>
        </div>
            <div class="col-sm-2">
                <!-- 搜索 -->
                <div class="input-group" style="width: 100%">
                    <input class="form-control" name="startTime" id="startTime" type="text"
                           value="" placeholder="开始日期..."/>
                </div>
            </div>
        <div class="col-sm-2">
            <!-- 搜索 -->
            <div class="input-group">
                <input class="form-control" name="endTime" id="endTime" type="text"
                       value="" placeholder="结束日期..."/>
                <span class="input-group-btn">
                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                        <i class="fa fa-search"></i>查询
                    </button>
                </span>
            </div>
        </div>
        <div class="col-sm-5">

        </div>
    </div>

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">订单明细</h4>
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
    $(function () {
        jeDate({
            dateCell: '#startTime',
            //isinitVal:new Date(),
            isinitVal:false,
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
        jeDate({
            dateCell: '#endTime',
            isinitVal:false,
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
    });
</script>