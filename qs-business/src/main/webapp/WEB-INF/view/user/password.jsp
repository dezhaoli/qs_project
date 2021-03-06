<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<%@include file="/common/common.jspf" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_pay_detail_list.js"></script>--%>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<div class="page-content">
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">修改密码</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <br/>

                <form id="storeForm" class="form-horizontal" name="storeForm"  role="form" method="post">
                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right">用户ID</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <input class="form-control" name="id" id="id" type="number"
                                       value="${id}" placeholder="用户ID..." disabled/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right">用户名</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <input class="form-control" name="name" id="name" type="text"
                                       value="${name}" placeholder="用户名..." disabled/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right">账号</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <input class="form-control" name="account" id="account" type="text"
                                       value="${account}" placeholder="账号..." disabled/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right">新密码</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <input class="form-control" name="password" id="password" type="password"
                                       value="" placeholder="请输入您要修改的新密码,密码长度必须为6-20位..."/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right">确认密码</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <input class="form-control" name="confirmPassword" id="confirmPassword" type="password"
                                       value="" placeholder="请确认您输入的新密码,密码长度必须为6-20位..."/>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <div class="center">
        <div class="input-group">
            <span class="input-group-btn">
                <button class="btn btn-app btn-success btn-xs" type="button" onclick="javascript:$('#storeForm').submit();">
                    <i class="icon-trash bigger-200"></i>确认修改
                </button>
                <button class="btn btn-app btn-primary btn-xs" type="button" onclick="goBack()">
                    <i class="icon-trash bigger-200"></i>返回
                </button>
            </span>
        </div>
    </div>

</div>
<script>
    $(function () {
        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                password: {
                    required: true,
                    rangelength:[6,20]
                },
                confirmPassword: {
                    required: true,
                    equalTo: "#password",
                    rangelength:[6,20]
                }
            },
            messages: {
                password: "密码长度必须为6-20位",
                confirmPassword: "确认密码不正确，请重新输入！"
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
                var account = $("#account").val();
                var id = $("#id").val();
                if (account == null || account == '' || account == undefined) {
                    layer.msg("账号为空，修改失败!", {icon: 6});
                    return;
                }
                if (id == null || id == '' || id == undefined) {
                    layer.msg("id为空，修改失败!", {icon: 6});
                    return;
                }
                var url = '/user/editPassword.html?id=' + id;
                webside.common.commit('storeForm', url, '/home.html');
            }
        });

    });

    function goBack() {
        webside.common.loadPage('/home.html');
    }
</script>