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
    id: 'name',
    title: 'APK名称',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'version',
    title: 'APK版本',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var str = "";
        for (var i=0 ;i < value.length ;i++) {
            var ch = value.charAt(i);
            str = str + "." +ch ;
        }
        return str.substring(1);
    }
}, {
    id: 'size',
    title: 'APK大小（单位：M）',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return (parseFloat(value) / 1048576).toFixed(2) + "M";
    }
},{//是否上线，0为不上线，1为上线
    id: 'date',
    title: 'APK更新日期',
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
    loadURL: sys.rootPath + '/game/record/list.html',
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

    //注册回车键事件
    /*document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };*/

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}


