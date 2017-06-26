<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_cancelmemberbinding_list.js"></script>
<div class="page-content">
    <div class="controls controls-row">
        <div class="controls controls-row">
            <button id="goBack" class="btn btn-info btn-sm" type="button">
                <i class="fa fa-undo"></i>返回
            </button>
        </div>
        <hr/>
        <div class="controls controls-row">
            <div class="col-sm-4">
                <div class="clearfix">
                    <input class="form-control" name="memberMid"
                           id="memberMid" type="number"
                           value="" placeholder="请输入会员mid..."/>
                </div>
            </div>
            <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                <i class="fa fa-search"></i>搜索
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
                    <h4 class="widget-title lighter">取消代理商会员绑定</h4>
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
                        <input id="mid" type="hidden" value="${mid}">
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
        $('#goBack').on('click', function () {
            closeLayer();
        });
    });

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
</script>