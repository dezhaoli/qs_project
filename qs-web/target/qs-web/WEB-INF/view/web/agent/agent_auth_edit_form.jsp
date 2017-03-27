<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-content">
    <div class="page-header">
        <h1>
            代理商用户信息编辑
        </h1>
    </div>
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12">
            <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">MID</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" type="text"
                                   value="${mid}" disabled/>
                            <input id="mid" value="${mid}" name="mid" type="hidden"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">真实姓名</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="realName" id="realName" type="text"
                                   value="${realName}" disabled/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">返现比例</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <input class="form-control" name="scale" id="scale" type="number"
                                   value="" placeholder="注：返现比例采用百分制，最大100..."/>
                            <input id="id" value="${id}" name="id" type="hidden"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">

                    </div>
                    <label class="control-label col-sm-1 no-padding-right">备注信息</label>
                    <div class="col-sm-5">
                        <div class="clearfix">
                            <textarea id="remark" name="remark" style="width: 100%;"
                                      placeholder="备注信息..."></textarea>
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
            <i class="fa "></i>&nbsp;修改
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
                scale: {
                    required: true,
                    max:100,
                    min:0
                }
            },
            messages: {
                scale: {
                    max:"请填写正确的返现比例",
                    min:"请填写正确的返现比例",
                    required:"返现比例不能为空"
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
                var url = "/agent/authorization/edit.html";
                webside.common.commit('storeForm', url, '/agent/authorization/listUi.html');
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