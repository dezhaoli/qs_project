<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/customer/agent/agent_userGradeUi_list.js"></script>
<div class="page-content">
	<div class="controls controls-row">
        <div class="form-horizontal" role="form">
            <div class="form-group">
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

	<input type="hidden" id="gameType" value="${gameType}"/>


	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="chartsId" class="col-xs-12" style="min-height: 300px;"></div>

	<div class="row" style="margin-top:5px;">
		<div class="col-xs-12 widget-container-col ui-sortable"
			style="min-height: 127px;">
			<div class="widget-box transparent ui-sortable-handle"
				style="opacity: 1; z-index: 0;">
				<div class="widget-header">
					<h4 class="widget-title lighter">星级代理商明细</h4>
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


$(function (){
	setTimeout(function (){
		//debugger;
	 $('#searchYear option').last().attr("selected",true);
	 $("#searchYear").change();
	 $('#searchDate option').last().attr("selected",true);
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
</script>