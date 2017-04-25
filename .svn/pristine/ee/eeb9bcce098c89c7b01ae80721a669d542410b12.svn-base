<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/member_agent_authorization_list.js"></script>

<!-- 搜索 --><!-- realname, phone -->
<div class="row" style="margin-top:5px;">
    <div class="col-sm-12">
        <div class="col-sm-3">
            <input id="realname" name="realname" type="text" class="input form-control"
                   placeholder="真实姓名..." >
        </div>
        <div class="col-sm-3">
            <input id="phone" name="phone" type="number" class="input form-control"
                   placeholder="手机号码..." >
        </div>
        <div class="col-sm-3">
            <div class="input-group">
                <input id="searchKey" type="number" class="input form-control"
                       placeholder="mid..." >
                <span class="input-group-btn">
                     <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                         <i class="fa fa-search"></i> 搜索</button>
                </span>
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
            <div class="widget-header">
                <h4 class="widget-title lighter">代理商信息</h4>
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
   $(function () {

   })
</script>

<%--<script>
    $(document).ready(function () {
        var optionValue = null;
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
            url: "/base/param/getInCheckVersion",
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
                url: "/base/param/update",
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
                url: "/base/param/update",
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


</script>--%>


