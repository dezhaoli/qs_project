<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增APK游戏版本
        </c:if>
        <c:if test="${!empty record}">
            编辑APK游戏版本
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
                <label class="control-label col-sm-1 no-padding-right">APK游戏名称</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="title" id="title" type="text"
                               value="${record.title}" placeholder="APK游戏名称..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">游戏英文名称</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="name" id="name" type="text"
                               value="${record.name}" placeholder="游戏英文名称..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">APK下载次数</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="download" id="download" type="number"
                               value="${record.download }" placeholder="APK下载次数..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">是否上线</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="status" name="status" style="width: 100%">
                            <option value="1" <c:if test="${record.status eq '1' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="0" <c:if test="${record.status eq '0' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">游戏简短介绍</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="shortdesc" id="shortdesc" type="text"
                               value="${record.shortdesc }" placeholder="游戏简短介绍..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">游戏详细介绍</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <div>
                            <%--<label for="desc">Default</label>--%>
                            <textarea class="form-control" id="desc0"
                                       name="desc0" placeholder="游戏详细介绍...">${record.desc0 }</textarea>
                        </div>
                    </div>
                </div>
            </div>

            <br/>
            <hr/>
            <div style="text-align: center;"><h1>以下为开发人员配置（非开发人员，无需填写）</h1></div>
            <hr/>
            <!-- 开发人员配置（非开发人员，无需填写） -->

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">游戏站长统计ID</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="chinaid" id="chinaid" type="text"
                               value="${record.chinaid }" placeholder="游戏站长统计ID..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">游戏推广数据库表</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="dbtable" id="dbtable" type="text"
                               value="${record.dbtable }" placeholder="游戏推广数据库表..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">APPLE STORE URL</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="applestoreurl" id="applestoreurl" type="text"
                               value="${record.applestoreurl }" placeholder="APPLE STORE URL..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">游戏使用数据库名</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="dbname" id="dbname" type="text"
                               value="${record.dbname }" placeholder="游戏使用数据库名..."/>
                    </div>
                </div>
            </div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/game/apkSynchro/listUi.html<c:if
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
                dbname: {
                    required: true
                },
                desc0: {
                    required: true
                }
            },
            messages: {
                dbname: "游戏使用数据库名",
                desc0: "游戏详细介绍"
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
                    url = '/game/apkSynchro/edit.html';
                } else {
                    url = '/game/apkSynchro/add.html';
                }
                webside.common.commit('storeForm', url, '/game/apkSynchro/listUi.html');
            }
        });


    });
</script>