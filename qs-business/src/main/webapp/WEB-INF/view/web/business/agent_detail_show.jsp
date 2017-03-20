<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_query_list.js"></script>

<div class="row">
    <div class="col-sm-1">
        <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryAgentsUi.html<c:if
                test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
                class="btn btn-info btn-sm">
            <i class="fa fa-undo"></i>&nbsp;返回
        </button>
    </div>
    <div class="col-sm-11"></div>
</div>
<hr/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">代理商详情</h3>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top:5px;">
            <div class="col-xs-12" >
                <form id="memberForm" name="memberForm" class="form-horizontal" role="form" method="post">

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" for="belongAgentId">
                            归属代理商ID：
                        </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.belongid}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right no-padding-left">
                            归属代理商真实姓名：
                        </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        ${record.realNameBind}
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >游戏ID：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.mid}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >游戏昵称：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        ${record.gameName}
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >累计充值：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        ${record.payTotal}
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >累计招募人数：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        ${record.inviteTotal}
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >真实姓名：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.realname}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >加入时间：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.comeTime}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >联系电话：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.phone}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >银行账号：</label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.bankCart}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >开户银行：</label>
                        <div class="col-sm-6">
                            <div class="clearfix">
                                <label class="control-label">
                                    <c:if test="${!empty record}">
                                        <c:if test="${!empty record.agentBusinessInfo}">
                                            ${record.agentBusinessInfo.bank}
                                        </c:if>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" >
                            <button type="button" class="btn btn-success">查看代理商业绩</button>
                        </label>
                        <div class="col-sm-10">
                            <div class="clearfix">

                            </div>
                        </div>
                    </div>

                </form>
                <div class="hr hr-dotted"></div>
            </div>
        </div>

        <%--<div class="center">
            <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryAgentsUi.html<c:if
                    test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
                    class="btn btn-info btn-sm">
                <i class="fa fa-undo"></i>&nbsp;返回
            </button>
        </div>--%>

    </div>
</div>


