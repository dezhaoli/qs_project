$(function () {
    jeDate({
        dateCell: '#startDate',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#endDate',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});

var startDate = $("#startDate").val();
var endDate = $("#endDate").val();
debugger;
var dtGridColumns = [{
    id : 'bizid',
    title : '商务ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'name',
    title : '商务名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'granttotal',
    title : '新增代理商数量',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'paytotal',
    title : '充值总金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        
        return "<a href='javascript:lookDetail("+record.bizid+")'>查看代理商明细</a>";
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;


var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/businessCount/queryBusinessCountList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '商务业绩统计-商务数据',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);


$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    
    grid.parameters = new Object();
    grid.parameters['startDate'] = startDate;
    grid.parameters['endDate'] = endDate;
    grid.parameters['groupId'] = $("#groupIdBusiness").val();
    grid.parameters['businessId'] = $("#businessIdByGroupId").val();
    queryAmountTotals(startDate,endDate,null,null);
    grid.load();
    $("#btnSearch").click(customSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});


/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    var startDateStr = $("#startDate").val();
    var endDateStr = $("#endDate").val();
    var groupId = $("#groupIdBusiness").val();
	var businessId = $("#businessIdByGroupId").val();
    //var name = $("#name").val();
    grid.parameters['startDate'] = startDateStr;
    grid.parameters['endDate'] = endDateStr;
    grid.parameters['name'] = name;
    grid.parameters['groupId'] = groupId;
    grid.parameters['businessId'] = businessId;
     startDate = startDateStr ;
     endDate = endDateStr;
     queryAmountTotals(startDate,endDate,groupId,businessId);
    grid.refresh(true);
}


function updateStatus(id,status){
	$.ajax({
        type: "GET",
        url: sys.rootPath+'/dict/updateStatus.html?id='+id+'&status='+status,
        dataType: "json",
        success: function(data){
        	webside.common.loadPage('/dict/dictList.html')
     }
    });
	//webside.common.loadPage('/dict/updateStatus.html?id='+id+'&status='+status)
}



var lookDetail = function(_val){
	webside.common.loadPage('/businessCount/toAgentCountListUi.html?id='+_val)
}

function queryAmountTotals(stime,etime,groupId,businessId){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/businessCount/queryBusinessAmountTotal.html',
		data:{'stime':stime,'etime':etime,'groupId':groupId,'businessId':businessId},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}
