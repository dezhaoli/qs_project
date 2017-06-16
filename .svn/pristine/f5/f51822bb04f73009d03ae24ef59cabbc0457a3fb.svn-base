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
                    <form id="memberForm" name="memberForm" class="form-horizontal" role="form" method="post">
					<table class="table table-condensed table-hover">
			               <thead>
				            <tr>
				                <th>MID</th>
				                <th>昵称</th>
				                <th>房卡数</th>
				            </tr>
				            </thead>
				            <tbody>
				            <tr>
				                <td>${mid}</td>
				                <td>${name}</td>
				                <td>${gold}</td>
				            </tr>
				            </tbody>
           			 </table>
                    </form>
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
