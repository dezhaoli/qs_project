



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


var startDate = $("#stime").val();
var endDate = $("#etime").val();

var saveDate = new Date(Date.parse(endDate.replace(/-/g, "/")));
saveDate.setDate(saveDate.getDate()-6);

var year = saveDate.getFullYear();
var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

startDate = year + "-" + month + "-" + date;

$('#stime').val(startDate);


var stimePlay =  $("#stime").val();
var etimePlay =  $("#etime").val();


var dtGridColumns = [{
    id : 'playDateStr',
    title : '日期',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totals',
    title : '日活跃人数',
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
	    loadURL : sys.rootPath + '/playing/activeList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '活跃用户列表',
	    //tools : 'refresh',
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
    grid.parameters['stime'] = stimePlay;
    grid.parameters['etime'] = etimePlay;
    grid.load();
    //queryPlayingCountActiveTotals(stimePlay,etimePlay);
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
	stimePlay = $("#stime").val();
	etimePlay = $("#etime").val();
    grid.parameters = new Object();
    
    	grid.parameters['stime'] = stimePlay;
    	grid.parameters['etime'] = etimePlay;
    	//queryPlayingCountActiveTotals(stimePlay,etimePlay);
    grid.refresh(true);
}



function queryPlayingCountActiveTotals(stime,etime){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/playing/queryCountTotalActive.html',
		data:{'stime':stime,'etime':etime},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}


 

