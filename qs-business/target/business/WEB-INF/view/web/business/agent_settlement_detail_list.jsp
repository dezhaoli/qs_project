<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_pay_detail_list.js"></script>--%>
<style>
    .table>thead>tr>th,
    .table>tbody>tr>th,
    .table>tfoot>tr>th,
    .table>thead>tr>td,
    .table>tbody>tr>td,
    .table>tfoot>tr>td {
        padding: 14px;
        line-height: 1;
        vertical-align: middle;
        cursor: default;
    }
</style>

<div class="page-content">
    <div class="row" style="margin-top:5px;">
        <div class="col-sm-12">
            <div class="col-xs-12 widget-container-col ui-sortable"
                 style="min-height: 127px;">
                <!-- #section:custom/widget-box.options.transparent -->
                <div class="widget-box transparent ui-sortable-handle"
                     style="opacity: 1; z-index: 0;">
                    <div class="well">
                        <c:if test="${!empty record.memberAgents and !empty record.memberAgents.mid}">
                            代理商ID：${record.memberAgents.mid}<br>
                        </c:if>
                        <c:if test="${empty record.memberAgents}">
                            代理商ID：未绑定<br>
                        </c:if>
                        <c:if test="${!empty record.memberAgents and !empty record.memberAgents.realname}">
                            真实姓名：${record.memberAgents.realname}<br>
                        </c:if>
                        <c:if test="${empty record.memberAgents or empty record.memberAgents.realname}">
                            真实姓名：未填写<br>
                        </c:if>

                        <c:if test="${!empty record.memberAgents and !empty record.memberAgents.firstmid}">
                            归属代理商ID：${record.memberAgents.firstmid}<br>
                        </c:if>
                        <c:if test="${empty record.memberAgents or empty record.memberAgents.firstmid}">
                            归属代理商ID：未绑定代理商<br>
                        </c:if>
                        <c:if test="${!empty record.memberAgents and !empty record.memberAgents.realnamebind}">
                            归属代理商真实姓名：${record.memberAgents.realnamebind}<br>
                        </c:if>
                        <c:if test="${empty record.memberAgents or empty record.memberAgents.realnamebind}">
                            归属代理商真实姓名：未填写<br>
                        </c:if>
                    </div>
                </div>

                <table class="table table-condensed table-hover">
                    <thead>
                    <tr>
                        <th></th>
                        <th>充值总额</th>
                        <th>结算总额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>直属会员</td>
                        <td>${record.infoMap.info00}</td>
                        <td>${record.infoMap.info01}</td>
                    </tr>
                    <tr>
                        <td>二级团队</td>
                        <td>${record.infoMap.info10}</td>
                        <td>${record.infoMap.info11}</td>
                    </tr>
                    <tr>
                        <td>三级团队</td>
                        <td>${record.infoMap.info20}</td>
                        <td>${record.infoMap.info21}</td>
                    </tr>
                    <tr>
                        <td>总计</td>
                        <td>${record.detailInfo.paytotal}</td>
                        <td>${record.rebateTotal}</td>
                    </tr>
                    <tr>
                        <td>实际发放</td>
                        <td>--</td>
                        <td>${record.detailInfo.rebatetotal}</td>
                    </tr>
                    </tbody>
                </table>

                <div class="well">
                    提示：<br>
                    1、直属会员（含本人充值）本周返现比例${record.firTeam}%，
                    二级团队本周返现比例${record.secTeam}%，
                    三级团队本周返现比例${record.thdTeam}%。<br>
                    2、您在成为代理商的前三个月，暂不收税，超过三个月之后按4%收税。
                </div>
            </div>
        </div>
    </div>

    <div class="center">
        <div class="input-group" onclick="closeLayer()">
            <span class="input-group-btn">
            <button class="btn btn-app btn-primary btn-xs" type="button">
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
