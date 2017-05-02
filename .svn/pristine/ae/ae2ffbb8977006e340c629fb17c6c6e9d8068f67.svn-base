<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_childs_list.js"></script>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<div class="row">
    <div class="col-sm-1">
        <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryAgentsUi.html<c:if
                test="${!empty totalCount}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
                class="btn btn-info btn-sm">
            <i class="fa fa-undo"></i>&nbsp;返回&nbsp;
        </button>
    </div>

    <div class="col-sm-6">
        <!-- 搜索 -->
        <div class="input-group">
            <input id="searchKey" type="text" class="input form-control"
                   placeholder="请输入子集代理商ID" >
            <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
             <i class="fa fa-search"></i>查询</button>
     </span>
        </div>
    </div>
    <div class="col-sm-5">

    </div>
</div>
<div class="row">
    <div class="col-sm-1">

    </div>
    <div class="col-sm-6">
        <div class="well" style="padding: 5px;">
            <h4 class="blue smaller lighter">累计授权子集代理商：${totalCount}</h4>
            <h5>
                <i style="font-size:14px;" class="fa fa-user fa-2x blue iconShow tooltip-success">：直属代理商详情</i>
            </h5>
            <h5>
                <i style="font-size:14px;" class="fa fa-users fa-2x green iconShow tooltip-success">：直属代理商子级代理查询</i>
            </h5>
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
                <h4 class="widget-title lighter">子级代理商查询</h4>
                <div class="widget-toolbar no-border">
                    <a href="#" data-action="fullscreen" class="orange2">
                        <i class="ace-icon fa fa-arrows-alt"></i>
                    </a>
                    <a href="#" data-action="collapse" class="green">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <input id="firstmid" type="hidden" value="${firstmid}">
            <input id="belongid" type="hidden" value="${belongid}">

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

<%--
<div class="center">
    <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryChildrenAgentUi.html<c:if
            test="${!empty totalCount}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>
--%>


