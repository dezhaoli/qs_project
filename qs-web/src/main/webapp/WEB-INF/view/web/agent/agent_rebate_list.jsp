<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<<style>
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
.padd{padding-top: 8px}
</style>
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/agent_rebate_list.js"></script>
<div class="page-header">
    <h1>
        返利比例管理
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#listMail" data-toggle="tab">返利比例管理</a></li>

            <li><a href="#addMail" data-toggle="tab">添加返利比例</a></li>
             <li style="display: none;" id="updateLi"><a href="#updateRebate" data-toggle="tab">修改返利比例</a></li>
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

                            <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentAccount">代理商mid</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentMid" type="number" name="mid" placeholder="代理商mid">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_less">3500以下</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_less" type="number" name="firstLessscale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_middle">3500-7000</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_middle" type="number" name="firstMiddlescale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					             
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_more">7000以上</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_more" type="number" name="firstMorescale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="second">二级团队</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="second" type="number" name="secondScale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="third">三级团队</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="third" type="number" name="thirdScale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="third">所属游戏</label>
					            <div class="col-sm-10">
								<select class="form-control" name="gametype" id="gameType">
									<option value="">-游戏类型-</option>
									<option value="2">牵手跑得快/跑胡子</option>
	<!-- 								<option value="2">牵手跑胡子</option> -->
									<option value="3">疯狂斗牛OL</option>
									<option value="4">牵手湖南麻将</option>
									<option value="9">牵手木虱</option>
									<option value="5">金溪麻将</option>
						  		</select>					            
						  		</div>
					        </div>
                            <div class="form-group" >
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-1">
                                    <button class='btn-success btn-sm' type="button" onclick="submitInfo();"
                                    id="saveMail">修改</button> <!-- onclick="javascript:$('#storeForm').submit();" -->
                                </div>
                            </div>

                        </div>

					</form>
                    </div>

                    </div>
                </div>
                
                <div class="tab-pane fade" id="updateRebate">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
					<form id="storeFormUpdate" name="storeForm" class="form-horizontal" role="form" method="post">
                        <div class="form-horizontal" role="form">
                            <div class="form-group">
                             <input class="form-control" id="idT" name="id" type="number" style="display: none;">
					            <label class="col-sm-2 control-label" for="agentAccount">代理商mid</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentMidT" type="number" name="mid" placeholder="代理商mid">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_less">3500以下</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_lessT" type="number" name="firstLessscale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_middle">3500-7000</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_middleT" type="number" name="firstMiddlescale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					             
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="agentfirst_more">7000以上</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="agentfirst_moreT" type="number" name="firstMorescale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="second">二级团队</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="secondT" type="number" name="secondScale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="third">三级团队</label>
					            <div class="col-sm-10">
					                <input class="form-control" id="thirdT" type="number" name="thirdScale" placeholder="返利比例(0-100纯数字)">
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-2 control-label" for="third">所属游戏</label>
					            <div class="col-sm-10">
								<select class="form-control" name="gametype" id="gameTypeT">
									<option value="">-游戏类型-</option>
									<option value="2">牵手跑得快/跑胡子</option>
	<!-- 								<option value="2">牵手跑胡子</option> -->
									<option value="3">疯狂斗牛OL</option>
									<option value="4">牵手湖南麻将</option>
									<option value="9">牵手木虱</option>
									<option value="5">金溪麻将</option>
						  		</select>					            
						  		</div>
					        </div>
                            <div class="form-group" >
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-1">
                                    <button class='btn-success btn-sm' type="button" onclick="updateRebateInfo();"
                                    id="saveMail">添加</button> <!-- onclick="javascript:$('#storeForm').submit();" -->
                                </div>
                            </div>

                        </div>

					</form>
                    </div>

                    </div>
                </div>
            </div>

            <div class="hr hr-dotted"></div>
        </div>
    </div>
</div>

<script>

//添加按钮
function submitInfo(){
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
	if (firstLessscale.length ==0 || firstLessscale ==""  || firstLessscale >= 3500  || firstLessscale < 0){  layer.msg("3500以下，不能为空！", { icon : 5 }); return ; }
	if (firstMiddlescale.length ==0 || firstMiddlescale ==""  || firstMiddlescale >7000 || 3500 >firstMiddlescale){  layer.msg("3500-7000直接，不能为空！", { icon : 5 }); return ; }
	if (firstMorescale.length ==0 || firstMorescale =="" || firstMorescale <7000){ layer.msg("7000以上，不能为空！", { icon : 5 }); return ;}
	if (secondScale.length ==0 || secondScale =="" || secondScale >=100 || 0>secondScale){ layer.msg("二级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (thirdScale.length ==0 || thirdScale =="" || thirdScale >=100 || 0>thirdScale ){ layer.msg("三级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (gametype.length ==0 || gametype ==""){ layer.msg("请选择游戏类型，不能为空！", { icon : 5 }); return ;}

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
}

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
	debugger;
	if (mid.length ==0 || mid ==""){layer.msg("请输入mid", { icon : 5 }); return; }
	if (firstLessscale.length ==0 || firstLessscale ==""  || firstLessscale >= 3500  || firstLessscale < 0){  layer.msg("3500以下，不能为空！", { icon : 5 }); return ; }
	if (firstMiddlescale.length ==0 || firstMiddlescale ==""  || firstMiddlescale >7000 || 3500 >firstMiddlescale){  layer.msg("3500-7000直接，不能为空！", { icon : 5 }); return ; }
	if (firstMorescale.length ==0 || firstMorescale =="" || firstMorescale <7000){ layer.msg("7000以上，不能为空！", { icon : 5 }); return ;}
	if (secondScale.length ==0 || secondScale =="" || secondScale >=100 || 0>secondScale){ layer.msg("二级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (thirdScale.length ==0 || thirdScale =="" || thirdScale >=100 || 0>thirdScale ){ layer.msg("三级团队返利比0-100之间，不能为空！", { icon : 5 }); return ;}
	if (gametype.length ==0 || gametype ==""){ layer.msg("请选择游戏类型，不能为空！", { icon : 5 }); return ;}
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

