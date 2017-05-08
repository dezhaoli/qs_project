var dtGridColumns = [{
    id: 'id',
    title: '公告ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'title',
    title: '标题',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'/*,
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var str = "";
        for (var i=0 ;i < value.length ;i++) {
            var ch = value.charAt(i);
            str = str + "." +ch ;
        }
        return str.substring(1);
    }*/
}, {
    id: 'content',
    title: '内容',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'/*,
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return (parseFloat(value) / 1048576).toFixed(2) + "M";
    }*/
},{
    id: 'type',
    title: '活动类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	if(value==1){
    		return "官方公告";
    	}else if(value==2){
    		return "游戏活动";
    	}else if(value==3){
    		return "页面活动";
    	}else{
    		return "";
    	}
        
    }
},{
    id: 'stime',
    title: '开始时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'etime',
    title: '截止时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'inter',
    title: '间隔时间(秒)',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'gametype',
    title: '游戏类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'pushType',
    title: '公告发布类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	if(value==1){
    		return "发布在线公告";
    	}else if(value==2){
    		return "发布停服公告";
    	}else if(value==3){
    		return "发布定时公告";
    	}else{
    		return "";
    	}
        
    }
},{
    id: 'pushUserName',
    title: '发布用户名',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'pushTime',
    title: '发布时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum

var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: true,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/game/notice/MainList.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '房间配置列表',
    pageSize: pageSize,
    pageSizeLimit: [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function () {

    if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }

    grid.parameters = new Object();
    var apkId = $("#apkId").val();
    grid.parameters['apkId']=apkId;

    grid.load();
    $("#btnSearch").click(customSearch);

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['title'] = $("#searchKey").val();
    grid.refresh(true);
}


