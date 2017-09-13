<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<style>
 body { 
 	background: #FFF; 
 } 
</style>
<div class="page-content">
    <div class="controls controls-row">
        <div class="form-horizontal" role="form">
            <div class="form-group">
<!--                 <label class="control-label col-sm-1 no-padding-right">年份</label> -->
                <div class="col-sm-3">
                    <div class="clearfix">
                        <select class="form-control" name="seacrhVersionKeySite" id="searchYear" >
                            <option selected="selected">请选择年份</option>
                            <c:forEach items="${years}" var="y">
                                <option value="${y}">${y}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

				<div class="col-sm-3">
					<div class="input-group" style="width: 100%;">
						<select class="form-control" name="seacrhVersionKeySite"
							id="searchDate">
							<option selected="selected">请选择日期</option>
						</select> 
						<span class="input-group-btn">
							<button id="btnSearch" class="btn btn-primary btn-sm"
								type="button">
								<i class="fa fa-search"></i>查询
							</button>
						</span>
					</div>
				</div>

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
					<table class="table table-condensed table-hover">
			               <thead>
				            <tr>
				                <th>类型</th>
				                <th>数量</th>
				                <th>操作</th>
				            </tr>
				            </thead>
				            <tbody>
				            <tr>
				                <td>本周新增会员</td>
				                <td class="bindpeople">${data.bindpeople }人</td>
				                <td rowspan="3">
				                    <button class="btn-default table-btn" onclick="showSettleDetail()" style="    margin: 3px !important;font-size: 12px;height: 30px;　padding: 2px 12px !important;　　border: 0px solid #fff;border-radius: 5px !important; color: #fff">详情</button>
				                </td>
				            </tr>
				            <tr>
				                <td>本周新增首次充值</td>
				                <td class="paycount">${data.paycount }人</td>
				
				            </tr>
				            <tr>
				                <td>本周开房次数</td>
				                <td class="playtimes">${data.playtimes }次</td>
				
				            </tr>
				           <!--  <tr>
				                <td>本周结算</td>
				                <td><*$statData.rebatetotal|default:0*>元</td>
				            </tr> -->
				            </tbody>
           			 </table>
                 <div class="well">
					<p>
						<b>注：</b>
					</p>
					<p>本周新增会员是：本周周一至周五绑定你的新玩家（非代理商）</p>
					<p>本周新增首次充值是：本周绑定你的新玩家第一次充值的人数</p>
					<p>本周开房次数是：本周所有绑定你的玩家开房次数</p>
				</div>
            </div>
        </div>
    </div>
</div>

<script>

$(function (){
	setTimeout(function (){
		//debugger;
		 $('#searchYear option').eq(1).attr("selected",true);
         $("#searchYear").change();
         $('#searchDate option').eq(1).attr("selected",true);
	},0);
	
	//$("#searchYear option:first-child").attr("selected",true);
});
    var json = '${jsonDate}';
    var obj = JSON.parse(json);
    $(function () {
        $('#searchYear').on('change',function (e) {
            var yearVal = $(this).children('option:selected').val();
            $('#searchDate').html('<option selected="selected">请选择日期</option>');
            if (yearVal == '请选择年份') {
                $('#searchDate').html('<option selected="selected">请选择日期</option>');
                return;
            }
            //alert(yearVal);
            var date = handleInitResponse.execute(yearVal);
            for (var i = 0;i<date.length;i++) {
                var arr = date[i];
                var v = arr.replace('月','-').replace('月','-');
                var vv = v.replace('日','-').replace('日','').substr(8);
                $('#searchDate').append('<option value="'+vv+'">'+arr+'</option>');
            }
        });

    });

    var handleInitResponse = {
        execute:function (data) {
            return eval('obj.a' + data );
        }
    }
    
    $("#btnSearch").click(customSearch);
    
    //查询
    function customSearch() {
        var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        if (searchYear == "请选择年份" || searchDate == "请选择日期") {
            layer.msg('请选择查询时间',{time:800});
            return;
        }
        var requirtDate = searchYear + "-" + searchDate;
       
    	var url = "${ctx}/agentroom/vipWeekDataStatQuery.html";
        $.ajax({
			type : "POST",
			url : url,
			data : {"data":requirtDate},
			dataType : "json",
			success : function(msg) {
				if (msg.success) {
					 $(".bindpeople").text(msg.data.bindpeople);
					 $(".paycount").text(msg.data.paycount);
					 $(".playtimes").text(msg.data.playtimes);
				} else {
					layer.msg(msg.OPERATE_FAILURE, {
						icon : 5,
						time : 500
					});
				}
			}

		});
    }
    
    
   //查看明细
   
   	function showSettleDetail(){
   		var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        var requirtDate="";
        if (searchYear != "请选择年份" || searchDate != "请选择日期") {
            requirtDate= searchYear + "-" + searchDate;
        }
        debugger
        var title= $('#searchDate :selected').text() ;
		  layer.open({
		        type: 2,
		        title:title,
		        area: ['95%','90%'],
		        fixed: false, //不固定
		        maxmin: true,
		        content: sys.rootPath + '/agentroom/vipWeekDataStatDetailUi.html?endTime='+requirtDate
		});
	}
</script>