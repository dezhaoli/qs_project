<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/member_user_goldOrigin_list.js"></script>

<div class="page-content">
    <div class="controls controls-row">
        <div class="controls controls-row">
            <button id="goBack" class="btn btn-info btn-sm" type="button">
                <i class="fa fa-undo"></i>返回
            </button>
        </div>
        <hr/>
        <div class="controls controls-row">
            <div class="col-sm-10">
                <div class="clearfix">
                    <select class="form-control" name="date"
                            id="date">
                        <option selected="selected" value="0" >本月</option>
                        <option value="1">上月</option>
                        <option value="2">上上月</option>
                    </select>
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
                    <h4 class="widget-title lighter">金币来源</h4>
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
                        <input id="mid" type="hidden" value="${id}">
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
        $('#goBack').on('click',function () {
            closeLayer();
        });
    });

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
</script>
<%--<div class="page-content">
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12">
            <div id="myTabContent" class="tab-content">

              &lt;%&ndash;  <div class="tab-pane fade " id="cardRecords">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <div class="controls controls-row">
                                <div class="col-sm-2">
                                    <div class="clearfix">
                                        <select class="form-control" name="gameType"
                                                id="gameType" disabled>
                                            <option selected="selected" value="1" >四川麻将</option>
                                            &lt;%&ndash;<option value="1">android</option>
                                            <option value="2">ios</option>&ndash;%&gt;
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="clearfix">
                                        <input class="form-control" name="roomId"
                                               id="roomId" type="number"
                                               value="" placeholder="房间ID..."/>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="clearfix">
                                        <input class="form-control" name="cardNum"
                                               id="cardNum" type="text"
                                               value="" placeholder="牌局编号..."/>
                                    </div>
                                </div>
                                <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                                    <i class="fa fa-search"></i>搜索
                                </button>
                            </div>

                            <div class="widget-box transparent ui-sortable-handle"
                                 style="opacity: 1; z-index: 0;">
                                <div class="widget-header">
                                    &lt;%&ndash;<h4 class="widget-title lighter">APK更新日志</h4>&ndash;%&gt;
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
                                        <input id="mid" type="hidden" value="${id}">
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
                </div>&ndash;%&gt;

                &lt;%&ndash;<div class="tab-pane fade " id="goldOrigin">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <div class="controls controls-row">
                                <div class="col-sm-10">
                                    <div class="clearfix">
                                        <select class="form-control" name="searchDate"
                                                id="searchDate">
                                            <option selected="selected" value="0" >本月</option>
                                            <option value="1">上月</option>
                                            <option value="2">上上月</option>
                                        </select>
                                    </div>
                                </div>
                                <button id="btnSearch1" class="btn btn-primary btn-sm" type="button">
                                    <i class="fa fa-search"></i>搜索
                                </button>
                            </div>

                            <div class="widget-box transparent ui-sortable-handle"
                                 style="opacity: 1; z-index: 0;">
                                <div class="widget-header">
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
                                        <input id="pageNum1" type="hidden" value="${page.pageNum }">
                                        <input id="pageSize1" type="hidden" value="${page.pageSize }">
                                        <input id="orderByColumn1" type="hidden" value="${page.orderByColumn }">
                                        <input id="orderByType1" type="hidden" value="${page.orderByType }">
                                        <div id="dtGridContainer1" class="dlshouwen-grid-container"></div>
                                        <div id="dtGridToolBarContainer1" class="dlshouwen-grid-toolbar-container"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>&ndash;%&gt;

            </div>
        </div>

        <div class="hr hr-dotted"></div>
    </div>
</div>--%>

<%--<script>

    $(function () {

        $('#li1').on('click', function () {
            $(this).addClass('active');
            $('#li2').removeClass('active');
            $('#li3').removeClass('active');
            $('#userInfo').fadeIn(100);
            $('#cardRecords').fadeOut(100);
            $('#goldOrigin').fadeOut(100);
        });
        $('#li2').on('click', function () {
            $(this).addClass('active');
            $('#li1').removeClass('active');
            $('#li3').removeClass('active');
            $('#cardRecords').fadeIn(100);
            $('#cardRecords').addClass('in', 'active');
            $('#userInfo').fadeOut(100);
            $('#goldOrigin').fadeOut(100);
        });
        $('#li3').on('click', function () {
            $(this).addClass('active');
            $('#li1').removeClass('active');
            $('#li2').removeClass('active');
            $('#goldOrigin').fadeIn(100);
            $('#goldOrigin').addClass('in', 'active');
            $('#userInfo').fadeOut(100);
            $('#cardRecords').fadeOut(100);
        });
    });


    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
</script>--%>

<!-- 表单提交的时候用到先保留 -->
<%--<script>

    $(function () {

        $('#agentForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                account: {
                    required: true
                },
                passWord: {
                    required: true,
                    rangelength: [6, 20]
                },
                name: {
                    required: true
                },
                confirmPwd: {
                    required: true,
                    rangelength: [6, 20],
                    equalTo: "#passWord"
                }
            },
            messages: {
                account: "账户名称不能为空",
                passWord: {
                    required: "密码不能为空",
                    rangelength: "长度为6-20位"
                },
                confirmPwd: {
                    required: "确认密码不能为空",
                    rangelength: "长度为6-20位"
                },
                name: "真实姓名不能为空"
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
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/agent/business/edit.html';
                } else {
                    url = '/agent/business/add.html';
                }
                webside.common.commit('agentForm', url, '/agent/business/listUi.html');
            }
        });

        $('#li1').on('click', function () {
            $('#pageHeader').text("商务明细");
        });
        $('#li2').on('click', function () {
            $('#pageHeader').text("添加商务专员");
        });
        $('#li3').on('click', function () {
            $('#pageHeader').text("各地区业绩");
        });

    });

    var jsonBuilder = {//layer.msg('更新成功!', {icon: 6});
        add: function (key, value) {
            this.startJson += "\"" + key + "\"" + ":" + "\"" + value + "\",";
            return this;
        },
        startJson: "{",
        getJson: function () {
            var aa = this.startJson.substring(0, this.startJson.length - 1) + "}";
            this.startJson = "{";
            return aa;
        }
    }

</script>--%>

