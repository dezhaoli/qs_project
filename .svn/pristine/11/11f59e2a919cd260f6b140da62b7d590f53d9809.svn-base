<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增游戏盾入口配置
        </c:if>
        <c:if test="${!empty record}">
            编辑游戏盾入口配置
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
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">名称</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="name" id="name" type="text"
                               value="${record.name }" placeholder="名称..."/>
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">游戏盾值</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name=gameShieId id="gameshield" type="text"
                               value="${record.gameShieId}" placeholder="游戏盾值..."/>
                    </div>
                </div>
            </div>

            <%-- <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">Game值</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="gameType" id="gameType" type="number"
                               value="${record.gameType }" placeholder="对应Game值..."/>
                    </div>
                </div>
            </div> --%>

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
    <button id="btn" type="button" onclick="webside.common.loadPage('/game/shield/gameShieldUi.html<c:if
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
                name: {
                    required: true
                },
                type: {
                    required: true
                },
                gameType: {
                    required: true,
                    number:true
                },
                gameShieId: {
                    required: true
                }
            },
            messages: {
                name: "名称",
                type: "类型",
                gameShieId: "游戏盾值",
                gameType:{
                	required:"对应Game值",
                	number:"请输入数字"
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
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/shield/edit.html';
                } else {
                    url = '/game/shield/add.html';
                }
                webside.common.commit('storeForm', url, '/game/shield/gameShieldUi.html');
            }
        });


    });
</script>