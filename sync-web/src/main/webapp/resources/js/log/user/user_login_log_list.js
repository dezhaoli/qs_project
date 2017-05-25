
var datasX = new Array(0);
var dataxS = new Array(0);

var dtGridColumns = [{
    id : 'mid',
    title : '用户ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'appName',
    title : '应用名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'appVersion',
    title : '应用版本号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'terminalType',
    title : '终端类型',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'loginTime',
    title : '登录时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'logoutTime',
    title : '登出时间',
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
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/userLoginLog/queryListByPage.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh',
	    //exportFileName : '房间配置列表',
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
	
	setOption();
    grid.parameters = new Object();
    grid.parameters['startTime'] = $("#startTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.refresh(true);
}

function edit(id){
	webside.common.loadPage('/game/shield/editUI.html?id='+id)
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


// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
 title: {
     text: '用户在线人数'
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
     type: 'bar',
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
			debugger;
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

/*$(function () {
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
});*/

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
