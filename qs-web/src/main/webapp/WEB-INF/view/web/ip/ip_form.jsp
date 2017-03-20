<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增ip配置
        </c:if>
        <c:if test="${!empty record}">
            编辑ip配置
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
                <label class="control-label col-sm-1 no-padding-right">类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="type" name="type" style="width: 100%">
                            <option value="L1" <c:if test="${record.type eq 'L1' }">selected="selected"</c:if>>
                                L1
                            </option>
                            <option value="L2" <c:if test="${record.type eq 'L2' }">selected="selected"</c:if>>
                                L2
                            </option>
                            <option value="L3" <c:if test="${record.type eq 'L3' }">selected="selected"</c:if>>
                                L3
                            </option>
                            <option value="L4" <c:if test="${record.type eq 'L4' }">selected="selected"</c:if>>
                                L4
                            </option>
                            <option value="L5" <c:if test="${record.type eq 'L5' }">selected="selected"</c:if>>
                                L5
                            </option>
                            <option value="L6" <c:if test="${record.type eq 'L6' }">selected="selected"</c:if>>
                                L6
                            </option>
                            <option value="L7" <c:if test="${record.type eq 'L7' }">selected="selected"</c:if>>
                                L7
                            </option>
                            <option value="L8" <c:if test="${record.type eq 'L8' }">selected="selected"</c:if>>
                                L8
                            </option>
                        </select>
                    </div>
                </div>

                <%--<div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="type" id="" type="radio"
                               value="${record.type}" placeholder="类型..."/>
                        <input class="form-control" name="type" type="radio"
                               value="L2" placeholder="类型..."/>
                        <input class="form-control" name="type" type="radio"
                               value="L3" placeholder="类型..."/>
                        <input class="form-control" name="type" type="radio"
                               value="L4" placeholder="类型..."/>
                    </div>
                </div>--%>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">名称</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="name" id="name" type="text"
                               value="${record.name}" placeholder="名称..."/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">ip地址</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="ipstring" id="ipstring" type="text"
                               value="${record.ipstring }" placeholder="ip地址..."/>
                    </div>
                </div>
            </div>

            <%--<div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">活动开始时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="datainp" class="form-control" name="starttimeStr" id="starttimeStr" type="text"
                               value="${record.starttimeStr}" placeholder="活动开始时间..."/>
                    </div>
                </div>

                <label class="control-label col-sm-1 no-padding-right">活动结束时间</label>
                <div class="col-sm-4">
                    <div class="clearfix">
                        <input class="datainp" class="form-control" name="endtimeStr" id="endtimeStr" type="text"
                               value="${record.endtimeStr}" placeholder="活动结束时间..."/>
                    </div>
                </div>
            </div>--%>
        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>
</div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/game/ip/ipListUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    $(function () {
       /* jeDate({
            dateCell: '#starttimeStr',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });

        jeDate({
            dateCell: '#endtimeStr',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01', //最小日期
            maxDate: '2050-06-01' //最大日期
        });*/


        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                type: {
                    required: true
                },
                ipstring: {
                    required: true
                }
            },
            messages: {
                name: "名称",
                type: "类型",
                ipstring: "ip地址"
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
                    url = '/game/ip/edit.html';
                } else {
                    url = '/game/ip/add.html';
                }
                webside.common.commit('storeForm', url, '/game/ip/ipListUi.html');
            }
        });


    });
</script>