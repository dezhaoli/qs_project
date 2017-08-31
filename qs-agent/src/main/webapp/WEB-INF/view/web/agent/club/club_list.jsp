<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<style>
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
.padd{padding-top: 8px}
</style>
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/club/club_list.js"></script>
<div class="page-header">
    <h1>
        我的俱乐部
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active" id="listMailLi"><a href="#listMail" data-toggle="tab">俱乐部成员管理</a></li>
            <li id="addMailLi"><a href="#addMail" data-toggle="tab">房间级别设置</a></li>
            <li id="updateLi" ><a href="#updateRebate" data-toggle="tab">俱乐部代开房规则</a></li>
        </ul>

        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="listMail">
            <div class="col-sm-2">
              <input type="text" id="searchMid" class="form-control"  placeholder="请输入MID...">
            </div>
            <div class="col-sm-2">
					 <div class="input-group" style="width: 100%;">
					            <span class="input-group-btn">
					            <button id="btnSearch" type="button" class="btn btn-primary query-btn" style=" padding: 2px 12px;">
										            查询
								</button>
								<button id="btnSearch" type="button" 
								onclick="addGlubMumber()"
								class="btn btn-success col-xs-3 col-sm-3" style="padding: 2px 12px;margin: 0 20px;width: 60px">
								添加</button>
					        </span>
					 </div>
		      </div> 
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="widget-box transparent ui-sortable-handle"
                             style="opacity: 1; z-index: 0;">
                            <div class="widget-header">
                                <%--<h4 class="widget-title lighter">APK更新日志</h4>--%>
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


            <div class="tab-pane fade" id="addMail">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
					<form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
                        <div class="form-horizontal" role="form">
                        
 						<c:forEach var="clubCost" items="${clubCost}" varStatus="s" >                           
	 						 <div class="form-group">
						            <label class="col-sm-2 control-label" for="agentAccount">VIP - </label>
						            <div class="col-sm-10">
						                <input class="form-control" id="vip${s.index  }" type="number" name="mid" value="${clubCost }" placeholder="返利比例(0-100纯数字)">
						            </div>
						       </div>
					    </c:forEach>
					        
                            <div class="form-group" >
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-1">
                                    <button class='btn-success btn-sm' type="button" onclick="submitInfo();"
                                    >确定</button> <!-- onclick="javascript:$('#storeForm').submit();" -->
                                </div>
                            </div>

                        </div>

					</form>
                    </div>

                    </div>
                </div>
                
                <div class="tab-pane fade" id="updateRebate">
                <div class="row">
                    <div class="col-xs-12" style=" font-size: 16px">
                        <!-- PAGE CONTENT BEGINS -->
                        <br>
                    	<h3>俱乐部代开房说明</h3> 
						<br>
						  1、代理商在“俱乐部成员管理”中可以给俱乐部内成员授权代开房，俱乐部代开房与普通代开房是区分开的。俱乐部内的代开房在创建普通房间时无效，同理，普通代开房在俱乐部内创建房间时也是无效的。<br>
						 <br>
						  2、俱乐部内授权非绑定会员代开房根据俱乐部等级不同，可授权非绑定会员代开房的人数也是不同的。俱乐部等级条件以及授权非绑定会员代开房人数对应如下：<br>
						   （1）代理商自身充值达到 1000 元，俱乐部等级达到 1 级，可以在俱乐部内授权 10 名非绑定的玩家进行代开房。<br>
						  （2）代理商自身充值达到 2000 元，俱乐部等级达到 2 级，可以在俱乐部内授权 20 名非绑定的玩家进行代开房。<br>
						  （3）代理商自身充值达到 5000 元，俱乐部等级达到 3 级，可以在俱乐部内授权 30 名非绑定的玩家进行代开房。<br>
						  （4）代理商自身充值达到 10000 元，俱乐部等级达到 4 级，可以在俱乐部内授权 40 名非绑定的玩家进行代开房。<br>
						   （5）俱乐部内授权非绑定会员代开房的玩家上限暂为 40 人。<br>
						   <br>
						 3、俱乐部创建人及俱乐部内成功授权代开房的玩家创建的房间才会显示在房间列表中，其他成员点击可直接加入房间。<br>
                    </div>

                    </div>
                </div>
                
                
                
            </div>

            <div class="hr hr-dotted"></div>
        </div>
    </div>
</div>

<script>
//房间级别设置
//添加俱乐部成员
function submitInfo(){
	var vip=$("#vip0").val()+","+$("#vip1").val()+","+$("#vip2").val()+","+$("#vip3").val()+","+$("#vip4").val();
	var url=sys.rootPath + '/agentClub/submitInfo.html';
	var mid=$("#searchMid").val();
	$.ajax({
	type : "POST",
	url : url,
	data : {"vip":vip},
	dataType : "json",
	success : function(msg) {
		if (msg.success == true) {
			layer.msg(msg.message, {
				icon : 1,
				time : 500
			});
			$("#searchMid").val("")
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

//添加俱乐部成员
	function addGlubMumber(){
		
		var url=sys.rootPath + '/agentClub/addClubMember.html';
		var mid=$("#searchMid").val();
		if (mid.length==0 && mid ==""){
			layer.msg("Mid不能为空！", { icon : 5,
				time : 500
			});
			return ;
		}
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
				$("#searchMid").val("")
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
		   var url=sys.rootPath + '/agentClub/addOrUpdateClubInfo.html';
			$.ajax({
				type : "POST",
				url : url,
				data : { 
						 "mid":id,
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
	 //删除对象
	   function deleteGlubMumber(id){
	    		
	    		var url=sys.rootPath + '/agentClub/deleteClubMemberInfo.html';
	    		var mid=$("#mid").val();
	    		$.ajax({
	   			type : "POST",
	   			url : url,
	   			data : {"mid":id},
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
//添加按钮
/* function submitInfo(){
	var data = $("#storeForm").serialize();
	var url = "${ctx}/agentRebate/insertRebateInfo.html";
	var mid=$("#agentMid").val();
	var firstLessscale=$("#agentfirst_less").val()
	var firstMiddlescale=$("#agentfirst_middle").val();
	var firstMorescale=$("#agentfirst_more").val();
	var secondScale=$("#second").val();
	var thirdScale=$("#third").val();
	var gametype=$("#gameType").val();
	
	if (mid.length ==0 || mid ==""){layer.msg("请输入mid", { icon : 5 }); return; }
	if (firstLessscale.length ==0 || firstLessscale ==""  ||  firstLessscale >=100 || 0>firstLessscale){  layer.msg("3500以下，请输入1-100之间！", { icon : 5 }); return ; }
	if (firstMiddlescale.length ==0 || firstMiddlescale ==""  ||  firstMiddlescale >=100 || 0>firstMiddlescale){  layer.msg("3500-7000直接，请输入1-100之间！", { icon : 5 }); return ; }
	if (firstMorescale.length ==0 || firstMorescale =="" ||  firstMorescale >=100 || 0>firstMorescale){ layer.msg("7000以上，请输入1-100之间！", { icon : 5 }); return ;}
	if (secondScale.length ==0 || secondScale =="" || secondScale >=100 || 0>secondScale){ layer.msg("二级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (thirdScale.length ==0 || thirdScale =="" || thirdScale >=100 || 0>thirdScale ){ layer.msg("三级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	//if (gametype.length ==0 || gametype ==""){ layer.msg("请选择游戏类型，不能为空！", { icon : 5 }); return ;}

	$.ajax({
	    type : "POST",
	    url : url,
	    data : data,
	    dataType : "json",
	    success : function(resultdata) {
	        if (resultdata.success) {
	            $("#myTab li").removeClass("active");
	            layer.msg(resultdata.message, {
	                icon : 1
	            });
	            $(".page-content").load("${ctx}/agentRebate/rebateUi.html");
	        } else {
	            layer.msg(resultdata.message, {
	                icon : 5
	            });
	        }
	    }
	});
} */

//修改按钮
function updateRebateInfo(){
	
	var data = $("#storeFormUpdate").serialize();
	var url = "${ctx}/agentRebate/updateRebateInfo.html";
	var mid=$("#agentMidT").val();
	var firstLessscale=$("#agentfirst_lessT").val()
	var firstMiddlescale=$("#agentfirst_middleT").val();
	var firstMorescale=$("#agentfirst_moreT").val();
	var secondScale=$("#secondT").val();
	var thirdScale=$("#thirdT").val();
	var gametype=$("#gameTypeT").val();
	if (mid.length ==0 || mid ==""){layer.msg("请输入mid", { icon : 5 }); return; }
	if (firstLessscale.length ==0 || firstLessscale ==""  ||  firstLessscale >=100 || 0>firstLessscale){  layer.msg("3500以下，请输入1-100之间！", { icon : 5 }); return ; }
	if (firstMiddlescale.length ==0 || firstMiddlescale ==""  ||  firstMiddlescale >=100 || 0>firstMiddlescale){  layer.msg("3500-7000直接，请输入1-100之间！", { icon : 5 }); return ; }
	if (firstMorescale.length ==0 || firstMorescale =="" ||  firstMorescale >=100 || 0>firstMorescale){ layer.msg("7000以上，请输入1-100之间！", { icon : 5 }); return ;}
	if (secondScale.length ==0 || secondScale =="" || secondScale >=100 || 0>secondScale){ layer.msg("二级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (thirdScale.length ==0 || thirdScale =="" || thirdScale >=100 || 0>thirdScale ){ layer.msg("三级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	//if (gametype.length ==0 || gametype ==""){ layer.msg("请选择游戏类型，不能为空！", { icon : 5 }); return ;}
	$.ajax({
	    type : "POST",
	    url : url,
	    data : data,
	    dataType : "json",
	    success : function(resultdata) {
	        if (resultdata.success) {
	            layer.msg(resultdata.message, {
	                icon : 1
	            });
	            $(".page-content").load("${ctx}/agentRebate/rebateUi.html");
	        } else {
	            layer.msg(resultdata.message, {
	                icon : 5
	            });
	        }
	    }
	});
}


</script>

