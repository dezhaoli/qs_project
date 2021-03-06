
var gameType = $('#gameType').val();
console.log(gameType);
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
    title : '代理商ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'one',
    title : '第一周总金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'two',
    title : '第二周总金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'three',
    title : '第三周总金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'four',
    title : '第四周总金额',
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
	    loadURL : sys.rootPath + '/weekdown/TaxesInviteWeekDownList.html?gameType='+gameType,
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
	setOption()
    grid.parameters = new Object();
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['date'] = $("#date").val();
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


//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
 title: {
     text: '代理商业绩下降报表'
 },
 tooltip: {},
 legend: {
     data:['金额']
 },
 xAxis: {
     data: ['第一周','第二周','第三周','第四周']
 },
 yAxis: {},
 series: [{
     name: 'RMB',
     //color:'red',
     type: 'line',
     data: []
 }]
}; 

//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
var setOption=function (){
	debugger;
	var mid = $("#mid").val();
	var date = $("#date").val();
	
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/weekdown/TaxesInviteWeekDownListCount.html',
		data:{'date':date,'mid':mid,'gameType':gameType},
		dataType: "json",
		success: function(data){
			debugger;
			var a = JSON.parse(data);
			var arr = new Array();
			if(a[0] != null){
				arr[0] = a[0].one;
				arr[1] = a[0].two;
				arr[2] = a[0].three;
				arr[3] = a[0].four;
			}
			
			
			option.series[0].data=arr;
			//option.xAxis.data= JSON.parse(array[0]);
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
	});
}
setOption();


$(function () {
    jeDate({
        dateCell: '#date',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06', //最小日期
        maxDate: '2050-06' //最大日期
    });
    /*jeDate({
        dateCell: '#endTime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });*/
});


var lookDetail = function(_val){
	webside.common.loadPage('/memberagents/toMemberagentsDetailsUi.html?id='+_val+'&gameType='+gameType)
}
