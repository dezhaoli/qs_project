<%--<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/my-common.jspf" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评论送房卡</title>
</head>
<body>
<div id="pageone">
    <div id="header">
        <div class="row" id="headerRow">
            <div class="col-sm-5">

            </div>
            <div class="col-sm-2">
                <h1 id="h1Height">欢迎来到我的主页</h1>
            </div>
            <div class="col-sm-5">

            </div>
        </div>
    </div>

    <div id="content">
        <div class="row">
            <div class="col-sm-4">
                <div class="well" style="padding: 5px;">
                    <h4 class="blue smaller lighter" style="font-size:18px;margin-bottom: 2px;">
                        上周结算:
                    </h4>
                </div>
            </div>

            <div class="col-sm-1">
                <select class="form-control" name="isaward" id="isaward">
                    <option selected="selected" value="1">已审核</option>
                    <option value="2">已支付</option>
                </select>
            </div>

            <div class="col-sm-1">
                <input type="number" class="input form-control" name="mid" id="mid" placeholder="请输入MID"/>
            </div>

            <div class="col-sm-1">
                <!-- 搜索 -->
                <div class="input-group" style="width: 100%;">
                    <select class="form-control" name="seacrhVersionKeySite" id="searchYear" >
                        <option selected="selected">请选择年份</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="input-group">
                    <select class="form-control" name="seacrhVersionKeySite" id="searchDate">
                        <option selected="selected">请选择日期</option>
                    </select>
                    <span class="input-group-btn">
                <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
                    <i class="fa fa-search"></i>查询
                </button>
            </span>
                </div>
            </div>
            <div class="col-sm-2">

            </div>
            <div class="col-sm-1">
            <span class="input-group-btn">
                <button id="oneKeyPay" class="btn btn-danger btn-sm" type="button">
                    <i class="fa fa-anchor"></i>一键支付
                </button>
            </span>
            </div>
        </div>
    </div>

    <div id="footer">
        <div class="row" id="footerRow">
            <div class="col-sm-5">

            </div>
            <div class="col-sm-2"><!--  style="text-align: center;" -->
                <h1 id="footerH1Height">版权所有© 乐玩游戏网络技术有限公司</h1>
            </div>
            <div class="col-sm-5">

            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="${ctx}/resources/js/customer/web/act/com_give_gold_ui.js" type="text/javascript"></script>
