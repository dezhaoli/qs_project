<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/mail_list.js"></script>--%>

<div class="page-content">
    <div class="center">
        <div class="page-header">
            <h1>
                增/减金币
            </h1>
        </div>
        <c:if test="${!empty memberFides}">
            <div class="row" style="margin-top:5px;">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->

                            <div class="form-horizontal" role="form">
                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <label class="control-label col-sm-1 no-padding-right">用户Mid</label>
                                    <div class="col-sm-6">
                                        <div class="clearfix">
                                            <input class="form-control" name="mid" type="number" id="mid"
                                                   value="${memberFides.mid}" placeholder="用户Mid..." disabled/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <c:if test="${!empty memberFides.name}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">用户名</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="username" type="text" id="username"
                                                       value="${memberFides.name}" placeholder="用户名..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${!empty memberAgents and !empty memberAgents.realname}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">所属代理商姓名</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="realname" type="text" id="realname"
                                                       value="${memberAgents.realname}" placeholder="所属代理商姓名..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${!empty memberBusiness and !empty memberBusiness.name}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">所属商务</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="bName" type="text" id="bName"
                                                       value="${memberBusiness.name}" placeholder="所属商务..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <label class="control-label col-sm-1 no-padding-right">增/减金币数量</label>
                                    <div class="col-sm-6">
                                        <div class="clearfix">
                                            <input class="form-control" name="goldCount" type="number" id="goldCount"
                                                   value="" placeholder="添加/减少金币数量，减少金币请加一个负号‘-’"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <label class="control-label col-sm-1 no-padding-right">备注</label>
                                    <div class="col-sm-6">
                                        <div class="clearfix">
                                            <input class="form-control" name="remark" type="text" id="remark"
                                                   value="" placeholder="备注..."/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-5">
                                    </div>
                                    <div class="col-sm-1">
                                    <span class="input-group-btn">
                                        <button class='btn btn-app btn-primary btn-xs' type="button"
                                                id="addGold">
                                            <i class="icon-trash bigger-200"></i>提交
                                        </button>
                                    </span>
                                    </div>
                                    <div class="col-sm-1">
                                   <span class="input-group-btn">
                                        <button class="btn btn-app btn-danger btn-xs" type="button"
                                                onclick="closeLayer()">
                                            <i class="icon-trash bigger-200"></i>关闭
                                        </button>
                                    </span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty error}">
            ${error}
        </c:if>
        <div class="hr hr-dotted"></div>

    </div>
</div>

<script>
    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }

    $('#addGold').on('click', function () {//取消代理商
        var mid = $('#mid').val();//mid
        var remark = $('#remark').val();//备注
        var goldCount = $('#goldCount').val();//金币数量
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/member/agent/backgroundAddGold/edit.html",
            data: {
                mid: mid,
                remark:remark,
                goldCount:goldCount
            },//将对象序列化成JSON字符串
            dataType: "json",
            //contentType : 'application/json;charset=utf-8', //设置请求头信息
            success: function (data) {
                if (data.success == true) {
                    layer.msg(data.message, {icon: 6});//修改成功
                    setInterval(closeLayer, 1200);// 注意函数名没有引号和括弧！
                    //closeLayer();
                } else {
                    layer.msg(data.message, {icon: 5});//修改失败
                    setInterval(closeLayer, 1200);// 注意函数名没有引号和括弧！
                }
            },
            error: function (res) {
                layer.msg(res, {icon: 5, time: 500});//发生错误
            }
        });
    });

</script>