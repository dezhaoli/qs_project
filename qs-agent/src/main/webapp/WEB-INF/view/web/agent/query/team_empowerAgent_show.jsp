<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<div class="page-content">
<c:choose>
<c:when test="${count >15}">
 <div class="controls controls-row">
        <div class="form-horizontal" role="form">
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">代理商ID</label>
                <div class="col-sm-2">
                    <div class="clearfix">
                         <input class="form-control" type="text" name="agents_id" id="agents_id" placeholder="代理商ID" />
                    </div>
                </div>
                  <div class="btn control-btn" id="addAgentsBtn">添加</div>
            </div>
        </div>
    </div>
</c:when>
<c:otherwise>

<div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">授权下级代理商</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                 <div class="well">
					  温馨提示：<br>
			          1、邀请人数需要达到15（含15）人以上，才能授权下级代理商。<br>
			          2、您目前邀请人数为${count }，还差${15-count}人。
				</div>
            </div>
        </div>
    </div>
</c:otherwise>
</c:choose>
</div>

