$(function () {
    jeDate({
        dateCell: '#startTime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#endTime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});

var datasX = new Array(0);
var dataxS = new Array(0);

var dtGridColumns = [{
    id : 'rowno',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo+1;
    }
},{
    id : 'appName',
    title : '应用名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'region',
    title : '区域名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totals',
    title : '登录次数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;


var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : false,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/userLoginLog/queryListByPage.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '用户登录统计',
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
    var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    grid.parameters = new Object();
    grid.parameters['startTime'] = startTime;
    grid.parameters['endTime'] = endTime;
    grid.load();
    queryUserLoginCountTotals(startTime,endTime);
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
	
	setOption();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    grid.parameters = new Object();
    grid.parameters['startTime'] = startTime;
    grid.parameters['endTime'] = endTime;
    grid.refresh(true);
    queryUserLoginCountTotals(startTime,endTime);
}




// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
 title: {
     text: '用户登录统计'
 },
 tooltip: {},
 legend: {
     data:['人数']
 },
 xAxis: {
     data: []
 },
 yAxis: {},
 series: [{
     name: '人数',
     //color:'blue',
     type: 'line',
     barWidth: '30%',
     data: []
 }]
}; 

//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
var setOption=function (){
	var endTime = $("#endTime").val();
	var startTime = $("#startTime").val();
	
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/userLoginLog/userLoginLogList.html',
		data:{'startTime':startTime,'endTime':endTime},
		dataType: "json",
		success: function(data){
			var array = data.split("@");
			option.series[0].data=JSON.parse(array[1]);
			option.xAxis.data= JSON.parse(array[0]);
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			console.log(array[1]);
			console.log(array[0]);
		}
	});
}
setOption();


$(function () {
	$("#date").change(function(){
		var _val = $("#date").val();
		dateFormate(_val);
	});
});

var dateFormate = function(_val){
	var datetimeNow = new Date();
	var datetimeBefor = new Date();
	
	datetimeNow.setDate(datetimeNow.getDate()+1);
	
	var year = datetimeNow.getFullYear();
    var month = datetimeNow.getMonth() + 1 < 10 ? "0" + (datetimeNow.getMonth() + 1) : datetimeNow.getMonth() + 1;
    var date = datetimeNow.getDate() < 10 ? "0" + datetimeNow.getDate() : datetimeNow.getDate();
	
	var now = year+"-"+month+"-"+date;
	
	datetimeBefor.setDate(datetimeBefor.getDate() - _val);
	
	var year = datetimeBefor.getFullYear();
    var month = datetimeBefor.getMonth() + 1 < 10 ? "0" + (datetimeBefor.getMonth() + 1) : datetimeBefor.getMonth() + 1;
    var date = datetimeBefor.getDate() < 10 ? "0" + datetimeBefor.getDate() : datetimeBefor.getDate();
	
	
	var befor = year+"-"+month+"-"+date;
	$("#startTime").val(befor);
	$("#endTime").val(now);
}

function queryUserLoginCountTotals(stime,etime){
	debugger;
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/userLoginLog/queryUserLoginCountTotals.html',
		data:{'stime':stime,'etime':etime},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}
