var stime4 =  $("#stime4").val();
var etime4 =  $("#etime4").val();
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
    id : 'name',
    title : '商务名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'realname',
    title : '代理商名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'userName',
    title : '用户名称',
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
	    check : false,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/createRoom/queryCreateRoomFourDetails.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    //tools : 'refresh|export[excel]',
	    exportFileName : '直属代理商创建房间统计',
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
    debugger;
    	grid.parameters = new Object();
    	grid.parameters['stime'] = stime4;
    	grid.parameters['etime'] = etime4;
        grid.parameters['appId'] = $("#appId4").val();
        grid.parameters['playId'] = $("#playId4").val();
	    grid.parameters['businessId'] = $("#businessId4").val();
	    grid.parameters['mid'] = $("#mid4").val();
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
