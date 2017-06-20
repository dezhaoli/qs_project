var dtGridColumns = [{
    id : 'mid',
    title : '代理商ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	 return "<span  style='color: #52b2d8' onclick='selectGlod("+value+");'>"+value+"</span>" ;
    }
},{
    id : 'realname',
    title : '用户昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'count',
    title : '招募人数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'bindtime',
    title : '加入时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'mid',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
    	
    	return '<button class="btn btn-primary agent_but" onclick="showSettleDetail(\'' + record.mid + '\')">充值明细</button>';
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
    loadURL : sys.rootPath + '/agentroom/selectVipDirectlyInfoProxy.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '代理商周信息统计',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	
	if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
		grid.sortParameter.columnId = $("#orderByColumn").val();
		grid.sortParameter.sortType = $("#orderByType").val();
	}
	grid.parameters = new Object();
    grid.parameters['type'] ="2"; 
    grid.parameters['querymid'] ="";
    grid.load();
    //$("#type").click(customSearch);
    $("#btnSearch").click(customSearch);
   // $("#types").click(customSearch);
    //注册回车键事件
    document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };


});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    
    grid.parameters['type'] ="2"; 
    grid.parameters['querymid'] = $("#midSearch").val();
    grid.refresh(true);
}
/*function selectGlod(id){
	  layer.open({
	        type: 2,
	        title:"金币余额",
	        area: ['80%','40%'],
	        fixed: false, //不固定
	        maxmin: true,
	        content: sys.rootPath + '/agentroom/getGoldCountUi.html?id='+id
	}); 
}*/

