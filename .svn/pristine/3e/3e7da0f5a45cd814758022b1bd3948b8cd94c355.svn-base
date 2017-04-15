var dtGridColumns = [{
    id: 'mid',
    title: 'Mid',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'usetime',
    title: '使用时间',
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
},{//是否上线，0为不上线，1为上线
    id: 'ipstring',
    title: '分配IP组',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{//是否上线，0为不上线，1为上线
    id: 'loginIp',
    title: '登录ip',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}];

/*`id` int(11) NOT NULL AUTO_INCREMENT,
    `mid` int(10) NOT NULL COMMENT '用户mid',
    `ipstring` varchar(1000) NOT NULL COMMENT 'ip地址',
    `name` varchar(100) NOT NULL COMMENT '名称',
    `login_ip` varchar(100) NOT NULL COMMENT '登录ip',
    `type` varchar(100) NOT NULL COMMENT '类型(级别)',
    `usetime` int(11) unsigned NOT NULL COMMENT '使用时间',*/

//动态设置jqGrid的rowNum

var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: true,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/ipAddressUserLog/list.html',
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
    grid.parameters['mid'] = $("#mid").val();
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
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['beginTime'] = $("#beginTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.refresh(true);
}


