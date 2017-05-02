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
                取消或绑定邀请人
            </h1>
        </div>
        <c:if test="${!empty memberFide}">
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
                                                   value="${memberFide.mid}" placeholder="用户Mid..." disabled/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <c:if test="${!empty memberFide.name}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">用户名</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="realname" type="text" id="realname"
                                                       value="${memberFide.name}" placeholder="用户Mid..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${!empty bName}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">所属商务</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="bName" type="text" id="bName"
                                                       value="${bName}" placeholder="所属商务..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${memberFide.invite == 0}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">邀请人</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="inviter" type="number"
                                                       id="inviter"
                                                       value="${memberFide.invite}" placeholder="邀请人..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">绑定ID</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="bingdingId" type="number"
                                                       id="bingdingId"
                                                       placeholder="绑定Id..."/>
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
                                                id="bingdingInvite">
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
                                </c:if>

                                <c:if test="${memberFide.invite != 0}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">邀请人</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="inviter" type="number"
                                                       id="inviter"
                                                       value="${memberFide.invite}" placeholder="邀请人..." disabled/>
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
                                                id="cancelInvite">
                                            <i class="icon-trash bigger-200"></i>解绑
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
                                </c:if>

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

    $('#bingdingInvite').on('click', function () {//绑定邀请人
        var mid = $('#mid').val();
        var inviter = $('#inviter').val();
        var bingdingId = $('#bingdingId').val();
        if (inviter != 0) {
            layer.msg("该用户已经绑定了" + inviter + ",无法绑定!", {icon: 5, time: 800});
            return;
        }
        if (bingdingId == undefined || bingdingId == null || bingdingId == "") {
            layer.msg("请输入要绑定的邀请人ID", {icon: 5, time: 800});
            return;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/member/agent/bindInviter/edit.html",
            data: {
                mid: mid,
                bingdingId:bingdingId
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

    $('#cancelInvite').on('click', function () {//取消绑定
        var mid = $('#mid').val();
        var inviter = $('#inviter').val();
        if (inviter == 0) {
            layer.msg("没有邀请人无须解绑！", {icon: 5, time: 800});
            return;
        }
        /*var editInviteCode_new = editInviteCode.replace(/[^0-9]+/g, '');
         if (editInviteCode_new.length != 6) {
         layer.msg("邀请码必须为6位数字！", {icon: 5, time: 800});
         //window.alert(editInviteCode_new.length);
         return;
         }*/

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/member/agent/cancelInviter/edit.html",
            data: {
                mid: mid
            },//将对象序列化成JSON字符串
            dataType: "json",
            //contentType : 'application/json;charset=utf-8', //设置请求头信息
            success: function (data) {
                if (data.success == true) {
                    layer.msg(data.message, {icon: 6});//修改成功
                    setInterval(closeLayer, 1200);// 注意函数名没有引号和括弧！
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