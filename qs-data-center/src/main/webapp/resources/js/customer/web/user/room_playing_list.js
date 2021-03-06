/*//基于准备好的dom，初始化echarts实例
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
};*/



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

var stimePlay =  $("#stime").val();
var etimePlay =  $("#etime").val();


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
    id : 'mid',
    title : '游戏mid',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'total',
    title : '房间数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
	  	id : 'gameStartTimeStr',
	    title : '日期',
	    type : 'string',
	    //format:'yyyy-MM-dd',
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
	    loadURL : sys.rootPath + '/roomPlaying/roomPlayList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '用户房间数统计',
	    //tools : 'refresh',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);


//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
/*var setOption=function (appName,playName,stime,etime){
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
setOption();*/



$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['stime'] = stimePlay;
    grid.parameters['etime'] = etimePlay;
    grid.load();
    //setOption("","",stimePlay,etimePlay);
    queryRoomPlayingCountTotals(stimePlay,etimePlay,null)
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
	/*var appName = $("#appName").val();*/
	var midplay = $("#mid").val();
	stimePlay = $("#stime").val();
	etimePlay = $("#etime").val();
    grid.parameters = new Object();
    /*grid.parameters['appName'] = appName;*/
    	grid.parameters['mid'] = midplay;
    	grid.parameters['stime'] = stimePlay;
    	grid.parameters['etime'] = etimePlay;
    //setOption(appName,playName,stimePlay,etimePlay);
    	queryRoomPlayingCountTotals(stimePlay,etimePlay,midplay)
    grid.refresh(true);
}


function queryRoomPlayingCountTotals(stime,etime,mid){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/roomPlaying/queryRoomCountTotal.html',
		data:{'stime':stime,'etime':etime,'mid':mid},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}

