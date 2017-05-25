<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增活动预告时间管理配置
        </c:if>
        <c:if test="${!empty record}">
            编辑活动预告时间管理配置
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
                <label class="control-label col-sm-1 no-padding-right">活动名称</label>
                <div class="col-sm-6">
                    <div class="clearfix">
                        <input class="form-control" name="name" id="name" type="text"
                               value="${record.name}" placeholder="活动名称..." style="width: 100%;"/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-1 no-padding-right">活动开始时间</label>
                <div class="col-sm-6">
                    <div class="clearfix">
                        <input class="datainp" class="form-control" name="starttime" id="starttime" type="text"
                               value="<fmt:formatDate value="${record.starttime}" type="both"/>" placeholder="活动开始时间..." style="width: 100%;"/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-1 no-padding-right">活动预告结束时间</label>
                <div class="col-sm-6">
                    <div class="clearfix">
                        <input class="datainp" class="form-control" name="endtime" id="endtime" type="text"
                               value="<fmt:formatDate value="${record.endtime}" type="both"/>" placeholder="活动结束时间..." style="width: 100%;"/>
                    </div>
                </div>
                <div class="col-sm-3"></div>
            </div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/cfg/actiTime/listUI.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    $(function () {


        jeDate({
            dateCell: '#starttime',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });

        jeDate({
            dateCell: '#endtime',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });


        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                starttime: {
                    required: true
                },
                endtime: {
                    required: true
                }
            },
            messages: {
                name: "活动名称不能为空",
                starttime: "活动预告开始时间不能为空",
                endtime: "活动预告结束时间不能为空"
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
                    url = '/cfg/actiTime/edit.html';
                } else {
                    url = '/cfg/actiTime/add.html';
                }
                webside.common.commit('storeForm', url, '/cfg/actiTime/listUI.html');
            }
        });


    });
</script>