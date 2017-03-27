var dtGridColumns = [{
    id : 'mid',
    title : '用户ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.realname != null) {
            return value + "/" +  record.realname;
        }
        return value + "/未填写";
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
    id : 'bindtime',
    title : '绑定时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.realname != null) {
            return value + "/" +  record.realname;
        }
        return value + "/未填写";
    }
},{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
    	return '<button class="btn btn-primary" onclick="show(\'' + record.id + '\')">代理开房</button>'+
    	 '<button class="btn btn-primary" onclick="show(\'' + record.id + '\')">充值明细</button>';
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
    loadURL : sys.rootPath + '/agentroom/selectVipDirectlyInfo.html',
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
	grid.parameters = new Object();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['type'] = $('#type :checked').val();
    grid.load();

});

