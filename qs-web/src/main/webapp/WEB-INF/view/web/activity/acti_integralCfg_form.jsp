<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增配置
        </c:if>
        <c:if test="${!empty record}">
            编辑配置
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
                <div class="col-sm-3"></div>
                <label class="control-label col-sm-1 no-padding-right">活动类型</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <select class="form-control" id="actiType" name="actiType" style="width: 100%">
                            <c:forEach items="${activityList}" var="act">
                                <option value="${act.code}"
                                        <c:if test="${record.actiType eq act.code}">selected="selected"</c:if>>${act.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

            <div class="form-group">
                <div class="col-sm-3"></div>
                <label class="control-label col-sm-1 no-padding-right">金币数量</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input class="form-control" name="gold" id="gold" type="number"
                               value="${record.gold}" placeholder="消耗的金币数量..."/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

            <div class="form-group">
                <div class="col-sm-3"></div>
                <label class="control-label col-sm-1 no-padding-right">积分数量</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input class="form-control" name="integral" id="integral" type="number"
                               value="${record.integral }" placeholder="赠送积分数量..."/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/actiIntegralCfg/listUi.html<c:if
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
                gold: {
                    required: true
                },
                integral: {
                    required: true
                },
                actiType: {
                    required: true
                }
            },
            messages: {
                gold: "消耗的金币数量不能为空！",
                integral: "赠送积分数量不能为空！",
                actiType: "请选择活动类型！"
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
                    url = '/actiIntegralCfg/edit.html';
                } else {
                    url = '/actiIntegralCfg/add.html';
                }
                commit('storeForm', url, '/actiIntegralCfg/listUi.html');
            }
        });

        function commit(formId, commitUrl, jumpUrl) {
            //组装表单数据
            var index;
            var data = $("#" + formId).serialize();
            if (undefined != $("#pageNum").val()) {
                jumpUrl = jumpUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val();
            }
            $.ajax({
                type : "POST",
                url : sys.rootPath + commitUrl,
                data : data,
                dataType : "json",
                beforeSend : function() {
                    index = layer.load();
                },
                success : function(resultdata) {
                    layer.close(index);
                    if (resultdata.success) {
                        layer.msg(resultdata.message, {
                            icon : 1
                        });
                        webside.common.loadPage(jumpUrl);
                    } else {
                        layer.msg(resultdata.message, {
                            icon : 5
                        });
                    }
                },
                error : function(data, errorMsg) {
                    layer.close(index);
                    layer.msg(data.responseText, {
                        icon : 2
                    });
                }
            });
        }
    });

</script>