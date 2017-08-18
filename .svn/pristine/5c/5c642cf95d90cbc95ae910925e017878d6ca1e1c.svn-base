<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增微信机器人
        </c:if>
        <c:if test="${!empty record}">
            编辑微信机器人
        </c:if>
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
            <c:if test="${!empty record}">
                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
                <input type="hidden" name="id" id="id" value="${record.id }">
            </c:if>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-1 no-padding-right">游戏id</label>
                <div class="col-sm-6">
                    <div class="clearfix">
                        <input class="form-control" name="mid" id="mid" type="number"
                                <c:if test="${!empty record}">disabled="disabled"</c:if>
                               value="${record.mid}" placeholder="游戏id..." style="width: 100%;"/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

            <c:if test="${!empty record}">
                <div class="form-group">
                    <div class="col-sm-2"></div>
                    <label class="control-label col-sm-1 no-padding-right">过期日期</label>
                    <div class="col-sm-6">
                        <div class="clearfix">
                            <input class="form-control" type="text" disabled
                                   value="${date}" placeholder="过期日期..." style="width: 100%;"/>
                        </div>
                    </div>
                    <div class="col-sm-3"></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2"></div>
                    <label class="control-label col-sm-1 no-padding-right">续签月数</label>
                    <div class="col-sm-6">
                        <div class="clearfix">
                            <input class="form-control" name="num" id="num" type="number"
                                   value="" placeholder="续签月数..." style="width: 100%;"/>
                        </div>
                    </div>
                    <div class="col-sm-3"></div>
                </div>
            </c:if>

            <c:if test="${empty record}">
                <div class="form-group">
                    <div class="col-sm-2"></div>
                    <label class="control-label col-sm-1 no-padding-right">开通月数</label>
                    <div class="col-sm-6">
                        <div class="clearfix">
                            <input class="form-control" name="num" id="num" type="number"
                                   value="" placeholder="开通月数..." style="width: 100%;"/>
                        </div>
                    </div>
                    <div class="col-sm-3"></div>
                </div>
            </c:if>

        </form>
    </div>
    <div class="hr hr-dotted"></div>
</div>

<div class="center">
    <button id="btnAdd" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-success btn-sm">
        <i class="fa fa-user-plus"></i>&nbsp;
        <c:if test="${empty record}">
            添加
        </c:if>
        <c:if test="${!empty record}">
            保存
        </c:if>
    </button>
    <button id="btn" type="button" onclick="webside.common.loadPage('/robot/listUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    $(function () {

        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                mid: {
                    required: true
                },
                num: {
                    required: true,
                    min : 1
                }
            },
            messages: {
                mid: "请输入mid",
                num: "请输入开通月数"
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
                    url = '/robot/edit.html';
                } else {
                    url = '/robot/add.html';
                }
                webside.common.commit('storeForm', url, '/robot/listUi.html');
            }
        });


    });
</script>