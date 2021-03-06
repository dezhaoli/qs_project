<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/activity/acti_award_list.js"></script>
<div class="page-header">
    <div class="col-sm-2">
        <button id="btnAdd" type="button" onclick="webside.common.addModel('/award/addUI.html')"
                          class="btn btn-primary btn-sm">
        <i class="fa fa-user-plus"></i>&nbsp;添加
        </button>
    </div>

    <div class="col-sm-2">
        <select class="form-control" name="searchKey" id="searchKey">
            <option value="" selected="selected">全部</option>
            <c:forEach items="${activityList}" var="act">
                <option value="${act.code}"
                        <c:if test="${record.type eq act.code}">selected="selected"</c:if>>${act.name}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="col-sm-3">
        <div class="input-group">
            <%--<input class="form-control" name="searchTitle" id="searchTitle" type="text"
                   value="" placeholder="标题..."/>--%>
            <span class="input-group-btn">
                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                        <i class="fa fa-search"></i>查询
                    </button>
                </span>
        </div>
    </div>
</div>

<div class="row" style="margin-top:5px;">
    <div class="col-xs-12 widget-container-col ui-sortable"
         style="min-height: 127px;">
        <!-- #section:custom/widget-box.options.transparent -->
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">活动奖品列表</h4>
                <div class="widget-toolbar no-border">
                    <a href="#" data-action="fullscreen" class="orange2">
                        <i class="ace-icon fa fa-arrows-alt"></i>
                    </a>
                    <a href="#" data-action="collapse" class="green">
                        <i class="ace-icon fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>

            <span id="activityListJson" style="display: none;">${activityListJson}</span>

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

<script>
    //alert(activityListJson);

</script>

