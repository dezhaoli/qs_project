<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-content">
    <div class="page-header">
        <h1>
            修改手机号码
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
                            <input class="form-control" name="agentId" id="agentId" type="text"
                                   value="${agentId}" disabled/>
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
                    <label class="control-label col-sm-1 no-padding-right">手机号码</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="phone" id="phone" type="number"
                                   value="${phone}" placeholder="名称..."/>
                            <%--<input id="agentId" value="${agentId}" name="agentId" type="hidden"/>--%>
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
            <i class="fa fa-user-plus"></i>&nbsp;保存
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
                phone: {
                    required: true
                },
                agentId: {
                    required: true
                }
            },
            messages: {
                phone: "手机号码不能为空",
                agentId: "系统异常"
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
                var url = "/agent/business/updatePhoneNumber.html";
                webside.common.commit('storeForm', url, '/agent/business/listUi.html');
                setInterval(closeLayer, 2000);
            }
        });

        function closeLayer() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
        }

    });

    function closeL() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        //window.location.reload();
    }

</script>