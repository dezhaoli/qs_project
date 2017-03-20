<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-content">
    <div class="page-header">
        <h1>
            重置密码
        </h1>
    </div>
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12">
            <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">商务专员ID</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" type="text"
                                   value="${agentId}" disabled/>
                            <input id="agentId" value="${agentId}" name="agentId" type="hidden"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">商务专员名称</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="name" id="name" type="text"
                                   value="${name}" disabled/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">新密码</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="password" id="password" type="password"
                                   placeholder="密码6-20位，不能有空格..."/>
                            <%--<input id="agentId" value="${agentId}" type="hidden"/>--%>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">确认新密码</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="confirmPwd" id="confirmPwd" type="password"
                                   placeholder="密码6-20位，不能有空格..."/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <div class="hr hr-dotted"></div>

    <div class="center">
        <button id="btnAdd" type="button" onclick="javascript:$('#storeForm').submit();"
                class="btn btn-success btn-sm">
            <i class="fa fa-user-plus"></i>&nbsp;提交
        </button>
        <button id="btn" type="button" onclick="closeL()"
                class="btn btn-info btn-sm">
            <i class="fa fa-undo"></i>&nbsp;返回
        </button>
    </div>
</div>
<script type="text/javascript">

    $(function () {

        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                password: {
                    required: true,
                    rangelength: [6, 20]
                },
                confirmPwd: {
                    required: true,
                    rangelength: [6, 20],
                    equalTo: "#password"
                }
            },
            messages: {
                password: {
                    required: "密码不能为空",
                    rangelength: "长度为6-20位"
                },
                confirmPwd: {
                    required: "确认密码不能为空",
                    rangelength: "长度为6-20位"
                }
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
                var url = "/agent/business/restPwd.html";
                webside.common.commit('storeForm', url, '/agent/business/listUi.html');
                setInterval(closeL, 2000);
            }
        });
    });

    function closeL() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        //window.location.reload();
    }
</script>