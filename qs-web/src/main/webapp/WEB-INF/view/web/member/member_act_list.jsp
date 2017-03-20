<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/member_act_list.js"></script>

<div class="row" style="margin-top:5px;">
    <div class="col-xs-12 widget-container-col ui-sortable"
         style="min-height: 127px;">
        <div class="widget-box transparent ui-sortable-handle"
             style="opacity: 1; z-index: 0;">
            <div class="widget-header">
                <h4 class="widget-title lighter">活动测试白名单列表</h4>
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


        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                mid: {
                    required: true
                }
            },
            messages: {
                mid: "mid"
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/activityTest/edit.html';
                } else {
                    url = '/game/activityTest/add.html';
                }
                webside.common.commit('storeForm', url, '/game/activityTest/listUi.html');
            }
        });

    });


</script>


