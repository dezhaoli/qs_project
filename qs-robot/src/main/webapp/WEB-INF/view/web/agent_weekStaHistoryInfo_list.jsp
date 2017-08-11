<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_weekStaHistoryInfo_list.js"></script>

<div class="page-content">
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <c:if test="${!empty gameType and gameType == 'majiang'}">
                        <h4 class="widget-title lighter">麻将代理商历史结算发放列表</h4>
                    </c:if>
                    <c:if test="${!empty gameType and gameType == 'bull'}">
                        <h4 class="widget-title lighter">斗牛代理商历史结算发放列表</h4>
                    </c:if>
                    <c:if test="${!empty gameType and gameType == 'runfast'}">
                        <h4 class="widget-title lighter">跑得快/跑胡子代理商历史结算发放列表</h4>
                    </c:if>
                    <c:if test="${empty gameType}">
                        <h4 class="widget-title lighter">代理商历史结算发放列表</h4>
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

                <iframe style="display: none" id="iframeOut" src=""></iframe>
                <input type="hidden" id="gameType" value="${gameType}"/>

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