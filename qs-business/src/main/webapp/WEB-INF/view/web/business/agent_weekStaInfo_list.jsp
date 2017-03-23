<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_weekStaInfo_list.js"></script>


<div class="page-content">
    <div class="controls controls-row">
        <div class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">年份</label>
                <div class="col-sm-2">
                    <div class="clearfix">
                        <select class="form-control" name="seacrhVersionKeySite" id="searchYear" >
                            <option selected="selected">请选择年份</option>
                            <c:forEach items="${years}" var="y">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">日期</label>
                <div class="col-sm-2">
                    <div class="clearfix">
                        <select class="form-control" name="seacrhVersionKeySite" id="searchDate">
                            <option selected="selected">请选择日期</option>
                        </select>
                    </div>
                </div>
                <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                    <i class="fa fa-search"></i>查询
                </button>
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
                    <h4 class="widget-title lighter">代理商周信息统计</h4>
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
    var json = '${jsonDate}';
    var obj = JSON.parse(json);
    $(function () {
        $('#searchYear').on('change',function (e) {
            var yearVal = $(this).children('option:selected').val();
            $('#searchDate').html('<option selected="selected">请选择日期</option>');
            if (yearVal == '请选择年份') {
                $('#searchDate').html('<option selected="selected">请选择日期</option>');
                return;
            }
            //alert(yearVal);
            var date = handleInitResponse.execute(yearVal);
            for (var i = 0;i<date.length;i++) {
                var arr = date[i];
                var v = arr.replace('月','-').replace('月','-');
                var vv = v.replace('日','-').replace('日','').substr(8);
                $('#searchDate').append('<option value="'+vv+'">'+arr+'</option>');
            }
        });

    });

    var handleInitResponse = {
        execute:function (data) {
            return eval('obj.a' + data );
        }
    }
</script>