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

<%-- <c:choose>
<c:when test="${clubType==0 }">
 <div class="row">
 <div  class="col-sm-12 text-center">
	 您当前未开通此权限！
 </div>
 </div>
</c:when>
<c:when test="${clubType==1 }"> --%>
<c:choose>
<c:when test="${gameType ==17 }">
<div class="row">
 <div  class="col-sm-12 text-center">
	 您当前未开通此权限！
 </div>
 </div>
</c:when>
<c:otherwise>  
 <script type="text/javascript"
        src="${ctx }/resources/js/customer/web/club/agent_club_list.js"></script> 
<div class="page-content">

 <div class="row">
				<input  class="col-xs-3 col-sm-3"  style="padding: 8px 12px;margin-left: 10px;width: 100px"
					id="mid" type="number" value="" placeholder="请输入mid" />
					<button id="btnSearch" type="button"
						onclick="slectGlubMumber()"
						class="btn btn-primary col-xs-3 col-sm-3" style="padding: 2px 12px;margin-left: 10px;width: 60px">
						查询</button>
						<button id="btnSearch" type="button" 
						onclick="addGlubMumber()"
						class="btn btn-success col-xs-3 col-sm-3" style="padding: 2px 12px;margin-left: 10px;width: 60px">
						添加</button>
						 <button type="button" id="editValue"
						onclick="webside.common.addModel('/agentClub/editClubRoomChargingUi.html')" value="0"
						class="btn btn-info tryitbtn col-xs-3 col-sm-3" style="padding: 2px 12px;margin-left: 10px;width: 100px">
						房卡数设置</button> 
		
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

<script type="text/javascript">

function closeClubEdit(){
	webside.common.loadPage('/agentClub/infoAgentClubUi.html');
}
 function editClubRoom(){
	 var replaceLows=parseInt($("#replaceLows").val());
	 var yoursLows=parseInt($("#yoursLows").val());
	 var bigLows=parseInt($("#bigLows").val());
	 
	 var replaceMid=parseInt($("#replaceMid").val());
	 var yoursMid=parseInt($("#yoursMid").val());
	 var bigMid=parseInt($("#bigMid").val());
	 
	 var replaceHig=parseInt($("#replaceHig").val());
	 var yoursHig= parseInt($("#yoursHig").val());
	 var bigHig=parseInt($("#bigHig").val());
	 
	 if(replaceLows.length==0 || isNaN(replaceLows) ){ showNull(); return;}
	 if(yoursLows.length==0 ||  isNaN(yoursLows)){ showNull(); return; }
	 if(bigLows.length==0 ||  isNaN(bigLows)){ showNull();  return;}
	 
	 if(replaceMid.length==0 ||  isNaN(replaceMid)){ showNull();  return;}
	 if(yoursMid.length==0 ||  isNaN(yoursMid)){ showNull();  return;}
	 if(bigMid.length==0 ||  isNaN(bigMid)){ showNull();  return;}
	 
	 if(replaceHig.length==0 ||  isNaN(replaceHig)){ showNull();  return;}
	 if(yoursHig.length==0 ||  isNaN(yoursHig)){ showNull();  return;}
	 if(bigHig.length==0 ||  isNaN(bigHig)){ showNull();  return;}
	 debugger;
	 if(1>replaceLows || replaceLows>= 10  ) { layer.msg("4牌局参数设置必须大于或等于1，小于或等于10", { icon : 5 }); return ; }
	 if(1>yoursLows ||yoursLows >=10) { layer.msg("4牌局参数设置必须大于或等于1，小于或等于10", { icon : 5 }); return ; }
	 if(1>bigLows || bigLows >=10) { layer.msg("4牌局参数设置必须大于或等于1，小于或等于10", { icon : 5 }); return ; }

	
	if (2 > replaceMid || replaceMid>= 10) { layer.msg("8牌局参数设置必须大于或等于2，小于或等于10", { icon : 5  }); return ; }
	if (2 > yoursMid || yoursMid>= 10) { layer.msg("8牌局参数设置必须大于或等于2，小于或等于10", { icon : 5  }); return ; }
	if (2 > bigMid || bigMid >= 10) { layer.msg("8牌局参数设置必须大于或等于2，小于或等于10", { icon : 5  }); return ; }

 	if (4 > replaceHig || replaceHig >= 10  ) { layer.msg("16牌局参数设置必须大于或等于4，小于或等于10", { icon : 5  }); return ; }
 	if (4 > yoursHig || yoursHig>= 10 ) { layer.msg("16牌局参数设置必须大于或等于4，小于或等于10", { icon : 5  }); return ; }
 	if (4 > bigHig || bigHig>= 10) { layer.msg("16牌局参数设置必须大于或等于4，小于或等于10", { icon : 5  }); return ; }
 	
	 var data = $("#storeForm").serialize();
	 $.ajax({
         type : "POST",
         url : sys.rootPath + "/agentClub/updateClubRoomCharging.html",
         data : data,
         dataType : "json",
         success : function(resultdata) {
             if (resultdata.success) {
                 layer.msg(resultdata.message, {
                     icon : 1
                 });
                 webside.common.loadPage('/agentClub/infoAgentClubUi.html');
             } else {
                 layer.msg(resultdata.message, {
                     icon : 5
                 });
             }
         }
     });
} 
var showNull=function (){
		 layer.msg("不能为空！", { icon : 5 });
}
</script>
<%-- </c:when>
</c:choose> --%>
</c:otherwise>  
</c:choose>  