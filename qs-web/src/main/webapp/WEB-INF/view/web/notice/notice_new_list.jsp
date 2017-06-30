<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/notice/notice_new_list.js"></script>
<style>
    input[disabled] {
        color: #848484 !important;
        background-color: #fff !important;
    }
</style>
<div class="page-header">
   <%-- <div class="col-sm-1">
        <input class="form-control" type="text" value="添加公告"
               style="border: 0px solid #fff;padding: 3px 31px 6px;" disabled/>
    </div>--%>
   <div class="col-sm-2" style="display: none;" id="addActi">
       <select class="form-control" name="seacrhVersionKeySite" id="changeType" >
           <option selected="selected" value="">请选择发布类型</option>
           <option value="1">在线公告</option>
           <option value="2">定时公告</option>
       </select>
   </div>
    <div class="col-sm-1">
        <div class="input-group">
            <button id="btnAdd" type="button" onclick="showOrHide()"
                    class="btn btn-primary btn-sm">
                <i class="fa fa-user-plus"></i>&nbsp;添加
            </button>
        </div>
    </div>

       <div class="col-sm-2">
           <select class="form-control" name="searchType" id="searchType">
               <option value="" selected="selected">请选择发布类型...</option>
               <option value="1" >在线公告...</option>
               <option value="2" >定时公告...</option>
           </select>
       </div>
       <div class="col-sm-3">
           <div class="input-group">
               <input class="form-control" name="searchTitle" id="searchTitle" type="text"
                      value="" placeholder="标题..."/>
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
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">游戏公告</h4>
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

<script>
    $('#changeType').on('change',function (e) {
        var opt = $("#changeType").val();
        if (opt == 1) {
            webside.common.addModel('/noticeNew/addUI.html?noticeType='+1);//在线公告
        }else if (opt = 2){
            webside.common.addModel('/noticeNew/addUI.html?noticeType='+2);//定时公告
        }else {

        }
    });
    function showOrHide() {
        $('#addActi').toggle();
        $('#searchType').toggle();
        $('#searchTitle').toggle();
        $('#btnSearch').toggle();
    }
</script>

