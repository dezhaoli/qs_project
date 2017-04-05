<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/mail_list.js"></script>--%>

<div class="page-content">
    <div class="center">
        <c:if test="${empty error}">
            <div class="page-header">
                <h1>
                    修改邀请码
                </h1>
            </div>
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
                                                   value="${mid}" placeholder="用户Mid..." disabled/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <c:if test="${!empty realname}">
                                    <div class="form-group">
                                        <div class="col-sm-2">

                                        </div>
                                        <label class="control-label col-sm-1 no-padding-right">真实名字</label>
                                        <div class="col-sm-6">
                                            <div class="clearfix">
                                                <input class="form-control" name="realname" type="text" id="realname"
                                                       value="${realname}" placeholder="用户Mid..." disabled/>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">

                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <label class="control-label col-sm-1 no-padding-right">原邀请码</label>
                                    <div class="col-sm-6">
                                        <div class="clearfix">
                                            <input class="form-control" name="orgInviteCode" type="number"
                                                   id="orgInviteCode"
                                                   value="${inviteCode}" placeholder="原邀请码..." disabled/>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-2">

                                    </div>
                                    <label class="control-label col-sm-1 no-padding-right">新邀请码</label>
                                    <div class="col-sm-6">
                                        <div class="clearfix">
                                            <input class="form-control" name="editInviteCode" type="number"
                                                   id="editInviteCode"
                                                   placeholder="请输入6位要更新的邀请码..."/>
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
                                                id="saveInviteCode">
                                            <i class="icon-trash bigger-200"></i>保存
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
            <div class="hr hr-dotted"></div>
        </c:if>

        <c:if test="${!empty error}">
            <div class="row" style="margin-top:5px;">
                    ${error}
                <hr/>
                <span class="input-group-btn">
                        <button class="btn btn-app btn-danger btn-xs" type="button" onclick="closeLayer()">
                            <i class="icon-trash bigger-200"></i>关闭
                        </button>
                    </span>
            </div>
        </c:if>

    </div>
</div>

<script>
    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }

    $('#saveInviteCode').on('click', function () {
        var editInviteCode = $('#editInviteCode').val();
        var orgInviteCode = $('#orgInviteCode').val();
        if (editInviteCode == orgInviteCode) {
            layer.msg("要修改的邀请码不能和原来的一样！", {icon: 5,time:800});
            return ;
        }
        var editInviteCode_new = editInviteCode.replace(/[^0-9]+/g, '');
        if (editInviteCode_new.length != 6) {
            layer.msg("邀请码必须为6位数字！", {icon: 5,time:800});
            //window.alert(editInviteCode_new.length);
            return ;
        }
        var mid = $('#mid').val();
        var mid_new = mid.replace(/[^0-9]+/g, '');
        if (mid_new == "" || mid_new.length == 0) {
            layer.msg("mid为0或不存在，无法提交！", {icon: 5,time:800});
            //window.alert(mid_new.length);
            return ;
        }

        if (mid_new != "" && mid_new.length != 0 && editInviteCode_new.length == 6) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath }/member/agent/editUserInviteCode/edit.html",
                data:{
                    editInviteCode:editInviteCode,
                    mid:mid
                },//将对象序列化成JSON字符串
                dataType:"json",
                //contentType : 'application/json;charset=utf-8', //设置请求头信息
                success: function(data){
                    if (data.success == true){
                        layer.msg(data.message, {icon : 6});//修改成功
                        setInterval(closeLayer,1200);// 注意函数名没有引号和括弧！
                        //closeLayer();
                    }else{
                        layer.msg(data.message, {icon : 5});//修改失败
                    }
                },
                error: function(res){
                    layer.msg(res, {icon: 5,time:500});//发生错误
                }
            });
        }else {

        }
    });

</script>