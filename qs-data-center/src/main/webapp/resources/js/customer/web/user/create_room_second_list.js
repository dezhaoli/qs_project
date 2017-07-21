var stime2 =  $("#stime2").val();
var etime2 =  $("#etime2").val();
var gameType = $('#gameType').val();
var dtGridColumns = [{
    id : 'name',
    title : '商务名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'appName',
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
    title : '创建房间数',
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

        return '<a href="javascript:lookCreateRoomDetailOfThree('+record.appId+',\''+record.playId+'\',\''+record.id+'\')">查看代理商明细</a>';
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
	    loadURL : sys.rootPath + '/createRoom/queryCreateRoomSecondDetails.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    //tools : 'refresh|export[excel]',
	    exportFileName : '用户创建房间统计',
	    //tools : 'refresh',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);


//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
/*var setOption=function (appName,playName,stime2,etime2){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/createRoom/createRoomCount.html',
		data:{'stime2':stime2,'etime2':etime2,'appName':appName,'playName':playName},
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
}*/

$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    debugger;
    	grid.parameters = new Object();
    	grid.parameters['stime'] = stime2;
    	grid.parameters['etime'] = etime2;
	    grid.parameters['appId'] = $("#appId").val();
	    grid.parameters['playId'] = $("#playId").val();
	    grid.parameters['businessId'] = $("#businessId2").val();
	    grid.parameters['groupId'] = $("#groupId2").val();
	    grid.load();
    //setOption(null,null,stime2,etime2);
    //queryCountTotals(stime2,etime2,null,null);
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
/*function customSearch() {
	var playName = $("#playName").val();
	 var appId = $("#appId").val();
	 var playId = $("#playId").val();
		 stime2 = $("#stime2").val();
		 etime2 = $("#etime2").val();
	    grid.parameters = new Object();
	    grid.parameters['playName'] = playName;
    	grid.parameters['stime'] = stime2;
    	grid.parameters['etime'] = etime2;
	    grid.parameters['appId'] = appId;
	    grid.parameters['playId'] = playId;
	    grid.parameters['businessId'] = $("#businessId2").val();
	    grid.parameters['groupId'] = $("#groupId2").val();
    //setOption(appName,playName,stime2,etime2);
   // queryCountTotals(stime2,etime2,playName,groupId,businessId);
    grid.refresh(true);
}*/



var lookCreateRoomDetailOfThree = function(appId,playId,id){
    var st  = $("#stime2").val();
    var et  = $("#etime2").val();
    webside.common.loadPage('/createRoom/toCreateRoomThreeDetailsUi.html?stime='+st+'&etime='+et+'&businessId='+id+'&appId='+appId+'&playId='+playId);
}



