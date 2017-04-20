<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/changeQuery_list.js"></script>

<div class="page-content">
    <div class="row">
        <%--<div class="col-sm-1">

        </div>--%>
        <div class="col-sm-6">
            <!-- 搜索 -->
            <div class="input-group">
                <input class="form-control" name="searchDate" id="searchDate" type="text"
                       value="" placeholder="请选择日期..."/>
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
                    <c:if test="${queryType == 'change'}">
                        <h4 class="widget-title lighter">充值查询</h4>
                    </c:if>
                    <c:if test="${queryType == 'gold'}">
                        <h4 class="widget-title lighter">金币查询</h4>
                    </c:if>
                    <c:if test="${queryType != 'gold' and queryType != 'change'}">
                        <h4 class="widget-title lighter">查询</h4>
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

                <input type="hidden" id="queryType" value="${queryType}">

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
            dateCell: '#searchDate',
            isinitVal:new Date(),
            format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });
    });
</script>