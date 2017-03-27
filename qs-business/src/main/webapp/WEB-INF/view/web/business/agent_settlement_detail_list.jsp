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
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="well">
                    代理商ID：<*$agentInfo.mid*> <br>
                    真实姓名：<*$agentInfo.realname|default:'<i class="content-null">未填写</i>'*> <br>
                    归属代理商ID：<*$agentInfo.firstmid*> <br>
                    归属代理商真实姓名：<*$agentInfo.realnamebind|default:'<i class="content-null">未填写</i>'*>
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
                    <td><*$settleInfo.info[0][0]|default:0*></td>
                    <td><*$settleInfo.info[0][1]|default:0*></td>
                </tr>
                <tr>
                    <td>二级团队</td>
                    <td><*$settleInfo.info[1][0]|default:0*></td>
                    <td><*$settleInfo.info[1][1]|default:0*></td>
                </tr>
                <tr>
                    <td>三级团队</td>
                    <td><*$settleInfo.info[2][0]|default:0*></td>
                    <td><*$settleInfo.info[2][1]|default:0*></td>
                </tr>
                <tr>
                    <td>总计</td>
                    <td><*$settleInfo.paytotal|default:0*></td>
                    <td><*$rebateTotal*></td>
                </tr>
                <tr>
                    <td>实际发放</td>
                    <td>--</td>
                    <td><*$settleInfo.rebatetotal|default:0*></td>
                </tr>
                </tbody>
            </table>

            <div class="well">
                     提示：<br>
                1、直属会员（含本人充值）本周返现比例<*$rebateScale.firTeam|default:40*>%，
                二级团队本周返现比例<*$rebateScale.secTeam|default:8*>%，
                三级团队本周返现比例<*$rebateScale.thdTeam|default:5*>%。<br>
                2、您在成为代理商的前三个月，暂不收税，超过三个月之后按4%收税。
            </div>
        </div>
    </div>
</div>
