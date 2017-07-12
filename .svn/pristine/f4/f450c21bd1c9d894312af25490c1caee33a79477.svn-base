//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
/*var option = {
 title: {
     text: '在玩人数'
 },
 tooltip: {},
 legend: {
	  data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
 },
 xAxis: {
	 type: 'category',
     data: ["0"]
 },
 yAxis: {},
 series: [{
     name: '人数',
     //color:'red',
     type: 'line',
     data: ["0"]
 }]
};*/
var option = {
	    title: {
	        text: '在玩人数'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: ['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [
	        {
	            name:'人数',
	            type:'line',
	            stack: '总量',
	            data:[0,0,0]
	        }
	    ]
	};


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




//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;





//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
var setOption=function (stime,etime){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/taxesInvite/taxesInviteCountOfCompanyList.html',
		data:{'stime':stime,'etime':etime},
		dataType: "json",
		success: function(data){
			debugger;
			//option.series[0].data=JSON.parse(array[1]);
			//option.xAxis.data= JSON.parse(array[0]);
			// 使用刚指定的配置项和数据显示图表。data.lengend
			var legend=JSON.parse(data.lengend);
			var series=JSON.parse(data.data);
			option.legend.data=legend;
			for(var i=0;i<legend.length;i++){
				var ser={
				           name:'人数',
				            type:'line',
				           stack: '总量',
				            data:[0,0,0]};
				ser.name=legend[i];
				ser.data=JSON.parse(data.xAxis);
				option.series.push(ser);
			}
//			option.series.data=JSON.parse(data.data);
			option.xAxis.data=JSON.parse(data.xAxis);
			myChart.setOption(option);
		}
	});
}



$(function() {
    setOption(stimePlay,etimePlay);
    $("#btnSearch").click(customSearch);
});


/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
	stimePlay = $("#stime").val();
	etimePlay = $("#etime").val();
    setOption(stimePlay,etimePlay);
}



 

