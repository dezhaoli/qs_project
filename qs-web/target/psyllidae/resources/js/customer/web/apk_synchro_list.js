var dtGridColumns = [
{
    id: 'id',
    title: 'ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'title',
    title: 'APK游戏名称',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'score',
    title: 'APK游戏评分',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'download',
    title: 'APK下载次数',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{//是否上线，0为不上线，1为上线
    id: 'status',
    title: 'APK状态',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-in-right" style="color: red;">未上线</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">已上线</span>';
        }
    }
},{
    id: 'chinaid',
    title: 'APK站长ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'dbtable',
    title: 'APK推广数据库表',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'applestoreurl',
    title: 'APK APPSTORE URL',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    hideType: 'xs',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a> '
            +' <a href="#" style="cursor: pointer;" onclick="showUpdateLog(\'' + record.id + '\')">查看更新日志</a>'
            +' <a href="#" style="cursor: pointer;" onclick="updateAPKPak(\'' + record.id + '\')">更新APK包</a>';
    }
}];

function edit(id) {
    webside.common.loadPage('/game/apkSynchro/editUI.html?id=' + id)
}

function showUpdateLog(id) {
    layer.open({
        type: 2,
        title:'查看更新日志',
        area: ['80%','80%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/game/record/listUi.html?id='+id
    });
}

function updateAPKPak(id) {
    layer.open({
        type: 2,
        title:'更新apk包',
        area: ['80%','80%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/game/record/upload.html?id='+id
    });
}

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: true,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/game/apkSynchro/list.html',
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


