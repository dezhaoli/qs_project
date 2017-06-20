var dtGridColumns = [{//SELECT a.fmid,SUM(a.pamount) AS ptotal,b.name AS username
    id : 'rowno',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo+1;
    }
},{
    id : 'roomid',
    title : '房间号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'num',
    title : '牌局数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'date',
    title : '日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'player1',
    title : '玩家1',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var name = record.namep1;
        if (name == null) {
            name = ' ';
        }
        return '玩家1ID:'+ value + ' | 名字:' + name + ' | 总论积分:' + record.datap1;
    }
},{
    id : 'player2',
    title : '玩家2',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var name = record.namep2;
        if (name == null) {
            name = ' ';
        }
        return '玩家2ID:'+ value + ' | 名字:' + name + ' | 总论积分:' + record.datap2;
    }
},{
    id : 'player3',
    title : '玩家3',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var name = record.namep3;
        if (name == null) {
            name = ' ';
        }
        if (value == 0) {
            return " -- ";
        }
        return '玩家3ID:'+ value + ' | 名字:' + name + ' | 总论积分:' + record.datap3;
    }
},{
    id : 'player4',
    title : '玩家4',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var name = record.namep4;
        if (name == null) {
            name = ' ';
        }
        if (value == 0) {
            return " -- ";
        }
        return '玩家4ID:'+ value + ' | 名字:' + name + ' | 总论积分:' + record.datap4;
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
    loadURL : sys.rootPath + '/query/gameRecord.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '战绩查询',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    //grid.parameters = new Object();
    //grid.parameters['mid'] = $('#mid').val();

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

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['mid'] = $('#mid').val();
    grid.refresh(true);
}









