var dtGridColumns = [
{
    id: 'site',
    title: '平台',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">安卓</span>';
        }else  if(value =='2'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">ios</span>';
        }
    }
}, {
    id: 'channel',
    title: '渠道',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'bigversion',
    title: '大版本号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'version',
    title: '版本号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'isskip',
    title: '是否可以跳过',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">可以跳过</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">不可以跳过</span>';
        }
    }
},{
    id: 'des',
    title: '更新描述',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'url',
    title: '最新的下载地址',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    //width: '10%',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<p style="white-space:nowrap; width: 200px; ' +
            'text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;"' +
            ' onmouseenter="urlShow(this,\''+value+'\')" onmouseout="urlHide(this,\''+value+'\')">' +
            value +'</p>'
    }
},{
    id: 'onlineversion',
    title: '上线版本',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'devicelistTest',
    title: '测试设备号列表',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<p style="white-space:nowrap; width: 200px; ' +
            'text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;"' +
            ' onmouseenter="urlShow(this,\''+value+'\')" onmouseout="urlHide(this,\''+value+'\')">' +
            value +'</p>'
    }
}, {
    id: 'urlTest',
    title: '测试url',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<p style="white-space:nowrap; width: 200px; ' +
            'text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;"' +
            ' onmouseenter="urlShow(this,\''+value+'\')" onmouseout="urlHide(this,\''+value+'\')">' +
            value +'</p>'
    }
},{
    id: 'lasttime',
    title: '上次更新时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNe, columnNo) {
        return value;
        //return "<span>"+"<fmt:formatDate value='"+value+"' pattern='yyyy-MM-dd'/>"+"</span>";
    }
},{
    id: 'forceurl',
    title: '强更url',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<p style="white-space:nowrap; width: 200px; ' +
            'text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;"' +
            ' onmouseenter="urlShow(this,\''+value+'\')" onmouseout="urlHide(this,\''+value+'\')">' +
            value +'</p>'
    }
},{
    id: 'gameType',
    title: '游戏类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNe, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">麻将</span>';
        }/*else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">跑得快</span>';
        }else  if(value =='2'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">疯狂斗牛</span>';
        }else  if(value =='3'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">木虱</span>';
        }*/
        return '<span class="label label-sm label-info arrowed arrowed-righ">麻将</span>';
    }
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    hideType: 'xs',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a>';
    }
}];

function urlShow(biaoqian,value) {
    //$(biaoqian).width('100%');
    //alert(value);
}

function urlHide(biaoqian,value) {
    //$(biaoqian).width('200px');
    //alert(value);
}

function edit(id) {
    webside.common.loadPage('/game/version/editUI.html?id=' + id)
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
    loadURL: sys.rootPath + '/game/version/list.html',
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


