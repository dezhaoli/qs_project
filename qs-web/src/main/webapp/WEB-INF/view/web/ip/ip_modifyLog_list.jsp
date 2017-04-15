<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf"%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/ip_modifyLog_list.js"></script>
<div class="page-content">
    <div class="controls controls-row">
        <div class="controls controls-row">
            <div class="col-sm-2">
                <div class="clearfix">
                    <input class="form-control" name="modifierId"
                           id="modifierId" type="number"
                           value="" placeholder="请输入modifierId..."/>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="clearfix">
                    <select class="form-control" name="type" id="type">
                        <option selected="selected" value="">请选择级别</option>
                        <option value="L1">L1</option>
                        <option value="L2">L2</option>
                        <option value="L3">L3</option>
                        <option value="L4">L4</option>
                        <option value="L5">L5</option>
                        <option value="L6">L6</option>
                        <option value="L7">L7</option>
                        <option value="L8">L8</option>
                        <option value="L9">L9</option>
                        <option value="L10">L10</option>
                        <option value="L11">L11</option>
                        <option value="L12">L12</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="clearfix">
                    <input class="form-control" name="beginTime" id="beginTime" type="text"
                           value="" placeholder="输入开始时间..."/>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="clearfix">
                    <input class="form-control" name="endTime" id="endTime" type="text"
                           value="" placeholder="输入结束时间..."/>
                </div>
            </div>
            <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                <i class="fa fa-search"></i>查询
            </button>
        </div>
    </div>

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">查看历史修改记录</h4>
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
            dateCell: '#beginTime',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01 00:00:00', //最小日期
            maxDate: '2050-06-01 23:59:59' //最大日期
        });
        jeDate({
            dateCell: '#endTime',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01 00:00:00', //最小日期
            maxDate: '2050-06-01 23:59:59' //最大日期
        });
    })
</script>
