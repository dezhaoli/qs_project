<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<style>
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
</style>
<div class="page-content">
<input hidden type="text" name="count" id="count" value="${count }">
<c:choose>
<c:when test="${gameType >=20 or gameType==17}">

		<c:choose>
		<c:when test="${count >=15}">
		 <div class="controls controls-row row_pad">
		        <div class="form-horizontal" role="form">
		            <div class="form-group">
		            
		            <div class="col-sm-3">
						            <div class="input-group" style="width: 100%;">
						                <input class="form-control" type="number" name="agents_id" id="agents_id" placeholder="代理商ID" />
						                <span class="input-group-btn">
							                <div class="btn btn-primary" id="addAgentsBtn" style="    padding: 2px 12px;">添加</div>
								       </span>
						            </div>
					</div> 
		               <!--  <div class="col-sm-2">
		                    <div class="clearfix">
		                         <input class="form-control" type="text" name="agents_id" id="agents_id" placeholder="代理商ID" />
		                     <br>
		                     <div class="btn btn-primary" id="addAgentsBtn">添加</div>
		                    </div>
		                </div> -->
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

		</c:when>
		<c:otherwise>
		
		 <div class="controls controls-row row_pad">
		        <div class="form-horizontal" role="form">
		            <div class="form-group">
		            
		            <div class="col-sm-3">
						            <div class="input-group" style="width: 100%;">
						                <input class="form-control" type="number" name="agents_id" id="agents_id" placeholder="代理商ID" />
						                <span class="input-group-btn">
							                <div class="btn btn-primary" id="addAgentsBtn" style="    padding: 2px 12px;">添加</div>
								       </span>
						            </div>
					</div> 
		               <!--  <div class="col-sm-2">
		                    <div class="clearfix">
		                         <input class="form-control" type="text" name="agents_id" id="agents_id" placeholder="代理商ID" />
		                     <br>
		                     <div class="btn btn-primary" id="addAgentsBtn">添加</div>
		                    </div>
		                </div> -->
		            </div>
		        </div>
		    </div>
		
		<div class="row" style="margin-top:5px;">
		        <div class="col-xs-12 widget-container-col ui-sortable"
		             style="min-height: 127px;">
		            <!-- #section:custom/widget-box.options.transparent -->
		           <%-- <div class="widget-box transparent ui-sortable-handle"
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
						</div> --%>
		            </div>
		        </div>
		    </div>

</c:otherwise>
</c:choose>

</div>


<script>
    $("#addAgentsBtn").click(customSearch);
    //查询
    function customSearch() {
        var agentsId =  $('#agents_id').val();
        var count =  $('#count').val();
        if (agentsId == "") {
            layer.msg('请输入授权Id',{time:800});
            return;
        }
       
    	var url = "${ctx}/agentroom/empowerAgentSubmit.html";
        $.ajax({
			type : "POST",
			url : url,
			data : {"agentId":agentsId,"count":count},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					layer.msg(msg.message, {
						icon : 6,
						time : 1000
					});
					$('#agents_id').val("");
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 1000
					});
					$('#agents_id').val("");
				}
			}

		});
    }
</script>

