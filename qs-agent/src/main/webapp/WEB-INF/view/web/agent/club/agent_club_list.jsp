<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<style>
 body { 
 	    background: #fff;
 } 
 input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
</style>
 <script type="text/javascript"
        src="${ctx }/resources/js/customer/web/club/agent_club_list.js"></script> 
<div class="page-content">

 <div class="row">
		<div class="col-sm-3">
			<div class="input-group" style="width: 100%;">
				<input  class="form-control" 
					id="mid" type="number" value="" placeholder="请输入mid" /> <span
					class="input-group-btn">
					<button id="btnSearch" type="button"
						onclick="slectGlubMumber()"
						class="btn btn-primary query-btn" style="padding: 2px 12px;margin-left: 10px">
						查询</button>
						<button id="btnSearch" type="button" 
						onclick="addGlubMumber()"
						class="btn btn-success" style="padding: 2px 12px;margin-left: 10px">
						添加</button>
				</span>
			</div>
		</div>
		
	</div>



    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                <h4 class="widget-title lighter">我的俱乐部</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <input id="pageNum" type="hidden" value="${page.pageNum }">
                        <input id="pageSize" type="hidden" value="${page.pageSize }">
                        <input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
                        <input id="orderByType" type="hidden" value="${page.orderByType }">
                        <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                        <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<script>

	//查询俱乐部成员
   function slectGlubMumber(){
	   customSearch();
   }
   
   //添加俱乐部成员
 	function addGlubMumber(){
 		
 		var url=sys.rootPath + '/agentClub/addAgentClubInfo.html';
 		var mid=$("#mid").val();
 		$.ajax({
			type : "POST",
			url : url,
			data : {"mid":mid},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					layer.msg(msg.message, {
						icon : 1,
						time : 500
					});
					$("#mid").val("")
					customSearch();
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 500
					});
				}
			}

		});
   }
	
//删除对象
function deleteGlubMumber(id){
 		
 		var url=sys.rootPath + '/agentClub/deleteAgentClubInfo.html';
 		var mid=$("#mid").val();
 		$.ajax({
			type : "POST",
			url : url,
			data : {"id":id},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					layer.msg(msg.message, {
						icon : 1,
						time : 500
					});
					$("#mid").val("")
					customSearch();
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 500
					});
				}
			}

		});
   }
   //带开房
   
   function openRoom(id,state){
		
		var url=sys.rootPath + '/agentClub/updateAgentClubInfo.html';
		$.ajax({
			type : "POST",
			url : url,
			data : { 
					 "id":id,
				     "state":state
					},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					layer.msg(msg.message, {
						icon : 1,
						time : 500
					});
					$("#mid").val("")
					customSearch();
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 500
					});
				}
			}

		});
  }
</script>