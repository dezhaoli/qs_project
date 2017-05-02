<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/business_resInquSearch_list.js"></script>



<div class="page-content">
    <div class="row">
        <%--<div class="col-sm-1">

        </div>--%>
        <div class="col-sm-3">
            <!-- 搜索 -->
            <div class="input-group" style="width: 100%;">
                <input class="form-control" name="startTime" id="startTime"
                       type="text" value="" placeholder="开始时间..." />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group" style="width: 100%;">
                <input class="form-control" name="endTime" id="endTime"
                       type="text" value="" placeholder="结束时间..." />
                <span class="input-group-btn">
                <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                    <i class="fa fa-search"></i>查询
                </button>
            </span>
            </div>
        </div>
        <div class="col-sm-2">

        </div>
    </div>

    <div class="col-sm-12">
        <div class="col-sm-6">
            <div style="min-height: 20px;padding: 19px;margin-bottom: 20px;
                        border-radius: 4px;box-shadow: inset 0 1px 1px rgba(0,0,0,.05);"><!-- class="well" -->
                <div class="col-sm-3">
                    <h5 class="blue smaller lighter" id="payCount"></h5>
                </div>
                <div class="col-sm-3"></div>
                <div class="col-sm-3">
                    <h5 class="blue smaller lighter" id="payPageCount"></h5>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header"><!-- //跑得快 / 跑胡子  -->
                    <c:if test="${!empty gameName}">
                        <h4 class="widget-title lighter">${gameName} 商务业绩列表</h4>
                    </c:if>
                    <c:if test="${empty gameName}">
                        <h4 class="widget-title lighter">商务业绩列表</h4>
                    </c:if>
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
    $(function() {
        jeDate({
            dateCell : '#startTime',
            isinitVal : true,
            format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate : '2016-09-01', //最小日期
            maxDate : '2050-06-01', //最大日期
            isToday:true
        });
        jeDate({
            dateCell : '#endTime',
            isinitVal : true,
            format : 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate : '2016-09-01', //最小日期
            maxDate : '2050-06-01', //最大日期
            isToday:true
        });
    });
</script>