<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>

<div class="page-content">
    <div class="row">
        <input type="hidden" id="gameType" value="${gameType}"/>
        <input type="hidden" id="date" value="${date}"/>
        <input type="hidden" id="stadate" value="${stadate}"/>

        <div class="col-sm-3">
            <div class="input-group" style="width: 100%;">
                <input type="number" class="input form-control" name="mid" id="mid" placeholder="请输入MID"/>
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

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <c:if test="${!empty gameType and gameType == 'majiang'}">
                        <h4 class="widget-title lighter">麻将代理商周信息统计</h4>
                    </c:if>
                    <c:if test="${!empty gameType and gameType == 'bull'}">
                        <h4 class="widget-title lighter">斗牛代理商周信息统计</h4>
                    </c:if>
                    <c:if test="${!empty gameType and gameType == 'runfast'}">
                        <h4 class="widget-title lighter">跑得快/跑胡子代理商周信息统计</h4>
                    </c:if>
                    <c:if test="${empty gameType}">
                        <h4 class="widget-title lighter">代理商周信息统计</h4>
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

    <div class="center">
        <div class="input-group">
            <span class="input-group-btn">
            <button class="btn btn-app btn-primary btn-xs" type="button" onclick="closeLayer()">
                <i class="icon-trash bigger-200"></i>关闭
            </button>
        </span>
        </div>
        <script>
            function closeLayer() {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
        </script>
    </div>
</div>

<script>
    var gameType = $('#gameType').val();

    var dtGridColumns = [{
        id : 'mid',
        title : 'MID',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            return value ;
        }
    },{
        id : 'openid',
        title : '微信openid',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            return value ;
        }
    },{
        id : 'mktime',
        title : '加入时间',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'realname',
        title : '真实姓名',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'phone',
        title : '联系电话',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'rebatetotal',
        title : '本周返利金额',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'bindpeople',
        title : '本周招募人数',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'paycount',
        title : '首冲人数',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            return value;
        }
    },{
        id : 'isaward',
        title : '返现状态',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution:function (value, record, column, grid, dataNo, columnNo) {
            if (0 == value) {
                return "未审核";
            }
            if (1 == value) {
                return "审核通过";
            }
            if (2 == value) {
                return "已发放";
            }
            return value;
        }
    }];

    //动态设置jqGrid的rowNum
    var pageSize = $("#pageSize").val();
    pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

    var stadate = $('#stadate').val();

    var dtGridOption = {
        lang : 'zh-cn',
        ajaxLoad : true,
        check : true,
        checkWidth :'37px',
        extraWidth : '37px',
        loadURL : sys.rootPath + '/corpPay/agentWeekInfoStaHistoryDetail.html?gameType='+gameType,
        columns : dtGridColumns,
        gridContainer : 'dtGridContainer',
        toolbarContainer : 'dtGridToolBarContainer',
        tools : 'refresh',//'refresh|print|export[excel,csv,pdf,txt]'
        exportFileName : stadate + '_代理商结算发放详情',
        pageSize : pageSize,
        pageSizeLimit : [10, 20, 30]
    };

    var grid = $.fn.dlshouwen.grid.init(dtGridOption);
    $(function() {
        if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
            grid.sortParameter.columnId = $("#orderByColumn").val();
            grid.sortParameter.sortType = $("#orderByType").val();
        }

        grid.parameters = new Object();
        grid.parameters['searchDate'] = $('#date').val();
        grid.parameters['mid'] = $('#mid').val();

        grid.load();
        $("#btnSearch").click(customSearch);

        //注册回车键事件
        document.onkeypress = function(e){
            var ev = document.all ? window.event : e;
            if(ev.keyCode==13) {
                customSearch();
            }
        };

    });

    /**
     * 自定义查询
     * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
     */
    function customSearch() {
        grid.parameters = new Object();
        grid.parameters['searchDate'] = $('#date').val();
        grid.parameters['mid'] = $('#mid').val();
        grid.refresh(true);
    }
</script>