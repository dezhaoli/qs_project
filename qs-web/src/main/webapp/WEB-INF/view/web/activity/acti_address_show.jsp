<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>

<div class="panel panel-default">
    <%--<div class="panel-heading">
        <h3 class="panel-title">用户联系方式</h3>
    </div>--%>
    <div class="panel-body">
        <div class="row" style="margin-top:5px;">
            <div class="col-xs-12" >
                <c:if test="${!empty actiAwardAddress}">
                    <form id="memberForm" name="memberForm" class="form-horizontal" role="form" method="post">

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                MID：${actiAwardAddress.mid}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right no-padding-right" style="text-align: left;">
                                姓名：${actiAwardAddress.name}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                QQ号：${actiAwardAddress.qq}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                微信号：${actiAwardAddress.wechat}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                邮箱号：${actiAwardAddress.email}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                联系电话：${actiAwardAddress.phone}
                            </label>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">

                            </div>
                            <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                                联系地址：${actiAwardAddress.address}
                            </label>
                        </div>
                    </form>
                </c:if>
                <c:if test="${empty actiAwardAddress}">
                    未填写收货地址！
                </c:if>
                <div class="hr hr-dotted"></div>
            </div>
        </div>
        <div class="center">
            <div class="input-group">
            <span class="input-group-btn">
            <button class="btn btn-app btn-primary btn-xs" type="button" onclick="closeLayer()">
                <i class="icon-trash bigger-200"></i>关闭
            </button>
        </span>
            </div>
            <script>
                function closeLayer() {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(index);
                }
            </script>
        </div>
    </div>
</div>
