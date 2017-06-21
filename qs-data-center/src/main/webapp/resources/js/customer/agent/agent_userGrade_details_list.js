
var belongid=$("#belongid").val();
var currdate=$("#currdate").val();
var grade=$("#grade").val();
//chare数据
$(function(){  

/*var chart = document.getElementById('chartsSecondId');    
var echart = echarts.init(chart); 
var option = {
	    title : {
	        text: '星级代理商数量'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['星级代理商数量']
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['一星级','二星级','三星级','四星级','五星级']
	        }
	    ],
	    yAxis : [
	        {
	        	name:"人数",
	        	splitNumber: 4,
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'人数',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[0, 0, 0, 0, 0]
	        }
	    ]
	};

//查询
function getShotEchars() {
	var url = sys.rootPath+"/weekdown/userGradeChar.html";
    $.ajax({
		type : "POST",
		url : url,
		data : {"eDate":currdate,"gameType":gameType,"belongid":belongid,"grade":grade},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				
				option.series[0].data=msg.data;
				echart.setOption(option);  
			} else {
				layer.msg(msg.OPERATE_FAILURE, {
					icon : 5,
					time : 500
				});
			}
		}

	});
}*/


//表格格数据
	
	var dtGridColumns = [{
	    id : 'name',
	    title : '商务名称',
	    type : 'number',
	    columnClass : 'text-center',
	    headerClass : 'dlshouwen-grid-header'
	},{
	    id : 'realname',
	    title : '代理商名称',
	    type : 'number',
	    columnClass : 'text-center',
	    headerClass : 'dlshouwen-grid-header'
	},{
	    id : 'mid',
	    title : 'MID',
	    type : 'number',
	    columnClass : 'text-center',
	    headerClass : 'dlshouwen-grid-header',
	    resolution: function (value, record, column, grid, dataNo, columnNo) {
	        return value ;
	    }
	},{
	    id : 'paytotal',
	    title : '团队充值',
	    type : 'number',
	    columnClass : 'text-center',
	    headerClass : 'dlshouwen-grid-header',
	    resolution: function (value, record, column, grid, dataNo, columnNo) {
	        return value ;
	    }
	},{
	    id : 'grade',
	    title : '星级',
	    type : 'number',
	    columnClass : 'text-center',
	    headerClass : 'dlshouwen-grid-header',
	    resolution: function (value, record, column, grid, dataNo, columnNo) {
	    	var r='';
	    	switch (value) {
			case 1:
				r='一星级';
				break;
			case 2:
				r='二星级';
				break;
			case 3:
				r='三星级';
				break;
			case 4:
				r='四星级';
				break;
			case 5:
				r='五星级';
				break;
			default:
				r='一星级';
				break;
			}
	        return  r;
	    }
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
	    loadURL : sys.rootPath + '/weekdown/userGradeSecondList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh',
	    exportFileName : '代理商周信息统计',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

	var grid = $.fn.dlshouwen.grid.init(dtGridOption);
		if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
			grid.sortParameter.columnId = $("#orderByColumn").val();
			grid.sortParameter.sortType = $("#orderByType").val();
		}
		
		grid.parameters = new Object();
       // getShotEchars();
	    grid.parameters = new Object();
	    grid.parameters['eDate'] = currdate;
	    grid.parameters['belongid'] = belongid;
	    grid.parameters['grade'] = grade;
	    grid.load();
	 




});