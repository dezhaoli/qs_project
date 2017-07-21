
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


var startDate = $("#startDate").val();
var endDate = $("#endDate").val();
/*
var saveDate = new Date(Date.parse(endDate.replace(/-/g, "/")));
saveDate.setDate(saveDate.getDate()-6);

var year = saveDate.getFullYear();
var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

startDate = year + "-" + month + "-" + date;
$('#startDate').val(startDate);*/

var dtGridColumns = [{
    id : '',
    title : '排名',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	var str=dataNo + 1;
        return  str;
    }
},{
    id : 'mid',
    title : '用户ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'name',
    title : '用户名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pamount',
    title : '付费总金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'mtimeStr',
    title : '用户注册时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'lgtmStr',
    title : '最后登录时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'ifvalid',
    title : '是否已经流失',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	var str='';
    	switch (value) {
		case 0:
			str='否';
			break;
		case 1:
			str='是';
			break;
		default:
			str='';
			break;
		}
        return  str;
    }
},{
    id : 'sta',
    title : '是否是代理商',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	var str='';
    	switch (value) {
		case 0:
			str='否';
			break;
		case 1:
			str='是';
			break;
		default:
			str='';
			break;
		}
        return  str;
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;


var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    //check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/memberFides/userPayrankingList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '付费排行榜',
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
    grid.load();
    //queryAddUserCountTotals(startDate,endDate);
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
    grid.parameters['startDate'] = startDateStr;
    grid.parameters['endDate'] = endDateStr;
     startDate = startDateStr ;
     endDate = endDateStr;
    grid.refresh(true);
    //queryAddUserCountTotals(startDate,endDate);
}


function queryAddUserCountTotals(stime,etime){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/memberFides/queryAddUserCountTotals.html',
		data:{'startDate':stime,'endDate':etime},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}
