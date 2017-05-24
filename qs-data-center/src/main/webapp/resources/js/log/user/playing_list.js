
var gameType = $('#gameType').val();
var dtGridColumns = [{
    id : 'appId',
    title : '游戏名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'playName',
    title : '玩法名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totals',
    title : '在玩人数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}/*,{
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        
        return "<a href='javascript:lookDetail("+record.id+")'>查看明细</a>";
    }
}*/];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;


var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/playing/playList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    //tools : 'refresh|export[excel]',
	    //exportFileName : '房间配置列表',
	    tools : 'refresh',
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
    grid.parameters['appName'] = $("#appName").val();
    grid.parameters['playName'] = $("#playName").val();
    var stime =  $("#stime").val();
    var etime =  $("#etime").val();
    if(stime != null && stime != ''){
    	grid.parameters['stime'] = stime+' 23:59:59';
    }else{
    	grid.parameters['stime'] = stime;
    }
    if(etime != null && etime != ''){
    	grid.parameters['etime'] = etime+' 23:59:59';
    }else{
    	grid.parameters['etime'] = etime;
    }
    
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


$(function () {
    jeDate({
        dateCell: '#stime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#etime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});


//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
 title: {
     text: '在玩人数'
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
     //color:'red',
     type: 'line',
     data: []
 }]
}; 

//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
var setOption=function (){
	    var appName = $("#appName").val();
	    var playName = $("#playName").val();
	    var stime =  $("#stime").val();
	    var etime =  $("#etime").val();
	    if(stime != null && stime != ''){
	    	stime = stime+' 23:59:59';
	    }else{
	    	stime = stime;
	    }
	    if(etime != null && etime != ''){
	    	etime = etime+' 23:59:59';
	    }else{
	    	etime = etime;
	    }
	
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/playing/playCount.html',
		data:{'stime':stime,'etime':etime,'appName':appName,'playName':playName},
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



var lookDetail = function(_val){
	webside.common.loadPage('/memberagents/toMemberagentsDetailsUi.html?id='+_val+'&gameType='+gameType)
}
