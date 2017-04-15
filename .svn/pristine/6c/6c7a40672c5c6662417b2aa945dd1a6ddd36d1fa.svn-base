var dtGridColumns = [{
    id: 'ipId',
    title: '服务器ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'updatetime',
    title: '更新时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}, {
    id: 'type',
    title: '类型(级别)',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'ipstring',
    title: 'IP地址',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'name',
    title: '名称',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'modifierId',
    title: '修改人ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'modifierName',
    title: '修改人',
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
    check: false,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/ipAddressLog/list.html',
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
    grid.parameters['type'] = $("#type").find("option:selected").val();
    grid.parameters['modifierId'] = $("#modifierId").val();
    grid.parameters['beginTime'] = $("#beginTime").val();
    grid.parameters['endTime'] = $("#endTime").val();

    grid.load();
    $("#btnSearch").click(customSearch);

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
    grid.parameters['type'] = $("#type").find("option:selected").val();
    grid.parameters['modifierId'] = $("#modifierId").val();
    grid.parameters['beginTime'] = $("#beginTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.refresh(true);
}


