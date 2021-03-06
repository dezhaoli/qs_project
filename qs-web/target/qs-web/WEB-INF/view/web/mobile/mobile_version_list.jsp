<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/mobile_version_list.js"></script>
<div class="page-header">
    <button id="btnAdd" type="button" onclick="webside.common.addModel('/game/version/addUI.html')"
            class="btn btn-primary btn-sm">
        <i class="fa fa-user-plus"></i>&nbsp;添加
    </button>
    <button id="inCheckApp" type="button" class="btn btn-primary btn-sm btn-purple ">
        <i class="fa fa-user-secret"></i>&nbsp;正在审核的APP
    </button>
</div>
<!-- 正在审核的APP -->
<div id="inCheckVersionDiv" style="display: none;">
    <form class="form-inline" role="form">
        <div class="form-group">
            <label class="" for="android">安卓---APP版本号:</label>
            <input type="hidden" id="hiddenAndroidId">
            <input type="text" class="form-control" id="android"
                   placeholder="请输入安卓版本号">
        </div>
        <button type="button" class="btn btn-info btn-sm" id="androidUpdate">更新</button>
    </form>
    <form class="form-inline" role="form">
        <div class="form-group">
            <label class="" for="ios">苹果---APP版本号:</label>
            <input type="hidden" id="hiddenIosId">
            <input type="text" class="form-control" id="ios"
                   placeholder="请输入苹果版本号">
        </div>
        <button type="button" class="btn btn-success btn-sm" id="iosUpdate">更新</button>
    </form>
    <hr/>
</div>

<div class="controls controls-row">
    <div class="col-sm-5">
        <div class="clearfix">
            <select class="form-control" name="seacrhVersionKeySite" id="seacrhVersionKeySite" >
                <option selected="selected" value="">全部平台</option>
                <option value="1">android</option>
                <option value="2">ios</option>
            </select>
        </div>
    </div>
    <div class="col-sm-5">
        <div class="clearfix">
            <input class="form-control" name="seacrhVersionKeyChannel" id="seacrhVersionKeyChannel" type="text"
                   value="" placeholder="渠道..."/>
        </div>
    </div>
    <button id="searchVersion" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
</div>

<%--<div class="input-group">
    <span class="">平台：&nbsp;&nbsp;</span>
    <select name="seacrhVersionKeySite" id="seacrhVersionKeySite" >
        <option selected="selected" value="">全部</option>
        <option value="1">android</option>
        <option value="2">ios</option>
    </select>--%>
    <%--<span class="">渠道：&nbsp;&nbsp;</span>
    <input type="text" class="input" name="seacrhVersionKeyChannel" id="seacrhVersionKeyChannel" placeholder="渠道..."/>
    <span class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>--%>
    <%--<button id="searchVersion" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>--%>
<%--</div>--%>

<div class="row" style="margin-top:5px;">
    <div class="col-xs-12 widget-container-col ui-sortable"
         style="min-height: 127px;">
        <!-- #section:custom/widget-box.options.transparent -->
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">移动版本管理列表</h4>
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
    /*$('#searchVersion').on('click',function (e) {
        $('#seacrhVersionKey').fadeToggle(100);
    });
    $('#seacrhVersionKey').on('change',function (e) {
        var optionValue = $(this).children('option:selected').val();
        grid.parameters = new Object();
        grid.parameters['site'] = optionValue;
        grid.refresh(true);
    });*/
    $(document).ready(function () {
        var optionValue = null;
        //var optionValueChannel = null;
        $('#seacrhVersionKeySite').on('change',function (e) {
            optionValue = $(this).children('option:selected').val();
        });

        $('#searchVersion').on('click',function () {
            grid.parameters = new Object();
            grid.parameters['site'] = optionValue;
            grid.parameters['channel'] = $('#seacrhVersionKeyChannel').val();
            grid.refresh(true);
        });

        document.onkeypress = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                grid.parameters = new Object();
                grid.parameters['site'] = optionValue;
                grid.parameters['channel'] = $('#seacrhVersionKeyChannel').val();
                grid.refresh(true);
            }
        };


        //初始化审核中的版本号
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/baseparam/getInCheckVersion.html",
            data: {

            },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {//android //ios
                if (data.successAndroid == true) {
                    $('#hiddenAndroidId').val(data.android.id);
                    $('#android').val(data.android.value);
                }
                if (data.successIos == true) {
                    $('#hiddenIosId').val(data.ios.id);
                    $('#ios').val(data.ios.value);
                }
                // Play with returned data in JSON format
            },
            error: function (msg) {
                layer.msg(msg, {icon: 5});
                //console.log(msg);
                //alert(msg);
            }
        });

        $('#inCheckApp').on('click', function () {
            $('#inCheckVersionDiv').fadeToggle(200);
        });

        $('#androidUpdate').on('click',function () {
            var versionValue = $('#android').val() + "";
            var id = $('#hiddenAndroidId').val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath }/baseparam/updateInCheckVersion.html",
                data: {
                    versionValue:versionValue,
                    mobileType:"android_version",
                    id:id
                },
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {//android //ios
                    if (data.successAndroid == true) {
                        $('#android').val(data.android.value);
                    }
                    if (data.successIos == true) {
                        $('#ios').val(data.ios.value);
                    }
                    layer.msg('更新成功!', {icon: 6});
                },
                error: function (msg) {
                    layer.msg(msg, {icon: 5});
                }
            });
        });

        $('#iosUpdate').on('click',function () {
            var versionValue = $('#ios').val() + "";
            var id = $('#hiddenIosId').val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath }/baseparam/updateInCheckVersion.html",
                data: {
                    versionValue:versionValue,
                    mobileType:"ios_version",
                    id:id
                },
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {//android //ios
                    if (data.successAndroid == true) {
                        $('#android').val(data.android.value);
                    }
                    if (data.successIos == true) {
                        $('#ios').val(data.ios.value);
                    }
                    layer.msg('更新成功!', {icon: 6});
                    //alert("更新成功！");
                },
                error: function (msg) {
                    layer.msg(msg, {icon: 5});
                }
            });
        });

    });


</script>


