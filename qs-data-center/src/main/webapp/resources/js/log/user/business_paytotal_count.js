//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));



var option2 = {
	    title: {
	        text: '商务业绩趋势图'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:[]
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
	        data: []
	    },
	    yAxis: {
		name:'金额/月份',
	    	nameTextStyle:{color : '#438eb9'},
	        type: 'value'
	    },
	    series: []
	};

$(function () {
    jeDate({
        dateCell: '#stime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06', //最小日期
        maxDate: '2050-06' //最大日期
    });
    jeDate({
        dateCell: '#etime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06', //最小日期
        maxDate: '2050-06' //最大日期
    });
});




var saveDate = new Date(Date.parse($("#etime").val().replace(/-/g, "/")));
saveDate.setMonth(saveDate.getMonth()-5);

var year = saveDate.getFullYear();
var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

startDate = year + "-" + month;

$('#stime').val(startDate);

var stimePlay =  $("#stime").val();
var etimePlay =  $("#etime").val();


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;





//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option2);
var setOption=function (stime,etime,groupId){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/taxesInvite/taxesInviteCountOfBusinessList.html',
		data:{'stime':stime,'etime':etime,'groupId':groupId},
		dataType: "json",
		success: function(data){
			debugger;
			myChart.clear();
			option2.series=[];
			var legend=JSON.parse(data.lengend);
			option2.legend.data=legend;
			option2.xAxis.data=JSON.parse(data.xAxis);
			var datas=JSON.parse(data.data);
			for(var i=0;i<legend.length;i++){
				var ser={
				           name:'',
				           type:'line',
				           
				           data:[0,0,0]
				};
				ser.name=legend[i];
				ser.data=datas[i];
				option2.series.push(ser);
			}
			myChart.setOption(option2);
		}
	});
}



$(function() {
    setOption(stimePlay,etimePlay,1);
    $("#btnSearch").click(customSearch);
});


/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
	stimePlay = $("#stime").val();
	etimePlay = $("#etime").val();
	var groupId = $("#groupIdBusiness").val();
    setOption(stimePlay,etimePlay,groupId);
}



 

