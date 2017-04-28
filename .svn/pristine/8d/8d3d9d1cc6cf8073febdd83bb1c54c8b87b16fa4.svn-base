<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_agentEmpowerGold_list.js"></script>

<div class="page-content">
    <div class="row">
        <div class="col-sm-3">
            <div class="input-group">
                  <input class="form-control" type="number" name="agents_id" id="AgentsId" placeholder="请输入代理商游戏ID">
                <span class="input-group-btn">
                <button id="addGold" class="btn btn-primary btn-sm col-xs-12" type="button">
                    <i class="fa fa-search"></i>添加
                </button>
            </span>
            </div>
        </div>
        <div class="col-sm-2">

        </div>
    </div>

    <div class="row" style="margin-top:5px;" id="showList">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">代理商周信息统计</h4>
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
    
    
     <div class="row" style="margin-top:5px;display:none;" id="showfromadd" >
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
             <div class="well">
			            游戏ID：<b class="text-info" id="gameMid"></b> <br>
			            游戏昵称：<b class="text-info" id="gameName"></b><br>
			            是否代理商：<b class="text-info" id="agentType"> </b><br>
       		 </div>
        <div id="msgBox"></div>
        <form class="clear" name="add_goldAgents_form"  role="form" autocomplete="off">
            <div class="form-group">
                <input class="btn btn-primary col-xs-6" id="empowerGoldAgents" type="button" name="add_btn" value="授权">
                 <input class=" btn btn-success col-xs-6"  type="button" id="beakList" value="返回">
               
            </div>
        </form>
             
        </div>
    </div>
</div>

<script>
$(function (){
	
	 $("#beakList").click(function (){
		$("#showList").show();
		$("#showfromadd").hide();
		customSearch();
	});
	 
	$("#addGold").click(function (){
	  var AgentsId=$("#AgentsId").val();
	    var url="/business/addGoldUserUi.html";
		
		if (AgentsId.length==0){
			 layer.msg("代理商游戏ID不能为空！", {
                 icon : 0
             });
			 return ;
		}else {
		  $.ajax({
             type : "POST",
             url : sys.rootPath + url,
             data : {
                 "mid" :AgentsId
             },
             dataType : "json",
             success : function(resultdata) {
                 if (resultdata.success) {
                	$("#showList").hide();
         			$("#showfromadd").show();
                	 $("#gameMid").text(resultdata.memberFides.mid);
                	 $("#gameName").text(resultdata.memberFides.name);
                	 if (resultdata.agentType ==1){
                		 $("#agentType").text("是");
                	 }else{
                		 $("#agentType").text("否");
                	 }
                 } else {
                     layer.msg(resultdata.message, {
                         icon : 5
                     });
                     $("#showList").show();
             		$("#showfromadd").hide();
                 }
             }
         }); 
		}
		
	});
	
	//点击授权按钮
	$("#empowerGoldAgents").click(function (){
	    var AgentsId=$("#AgentsId").val();
	    layer.open({
	        content: '您确定要将玩家ID：'+AgentsId+'添加为金牌代理商吗？'
	        ,btn: ['确定', '取消']
	        ,yes: function(index, layero){
	            
	    		var url="/business/addGoldUserSubmit.html";
	    		 $.ajax({
	                 type : "POST",
	                 url : sys.rootPath + url,
	                 data : {
	                     "mid" :AgentsId
	                 },
	                 dataType : "json",
	                 success : function(resultdata) {
	                     if (resultdata.success) {
	                    	 layer.msg(resultdata.message, {
	                             icon : 6
	                         });
	                 		 customSearch();
	                 		setTimeout(function (){
	                    		 $("#showList").show();
	                     		 $("#showfromadd").hide();
	                    	},500)
	                    	 
	                     } else {
	                         layer.msg(resultdata.message, {
	                             icon : 5
	                         });
	                     }
	                 }
	             }); 
	        }
	        ,btn2: function(index, layero){
	            if (countPay == 2) {
	                return;
	            }
	      
	        }
	        ,cancel: function(){
	            //右上角关闭回调
	            if (countPay == 2) {
	                return;
	            }
	        }
	    });
		
		
	});
});
   
</script>