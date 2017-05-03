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
                取消代理商
            </h1>
        </div>
        <c:if test="${!empty memberAgents}">
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
                                                   value="${memberAgents.mid}" placeholder="用户Mid..." disabled/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <c:if test="${!empty memberAgents.realname}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">用户姓名</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="realname" type="text" id="realname"
                                                       value="${memberAgents.realname}" placeholder="用户姓名..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${!empty memberBusiness}">
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
                                    <div class="col-sm-5">
                                    </div>
                                    <div class="col-sm-1">
                                    <span class="input-group-btn">
                                        <button class='btn btn-app btn-primary btn-xs' type="button"
                                                id="cancelAgent">
                                            <i class="icon-trash bigger-200"></i>取消代理
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

    $('#cancelAgent').on('click', function () {//取消代理商
        var mid = $('#mid').val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/member/agent/cancelAgent/edit.html",
            data: {
                mid: mid
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