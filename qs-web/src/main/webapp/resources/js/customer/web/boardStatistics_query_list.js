var dtGridColumns = [{
    id : 'no',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo+1;
    }
},{
    id : 'mid',
    title : 'MID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'name',
    title : '昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'RegTime',
    title : '注册时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'allRecord',
    title : '牌局数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}];
// pr.mid,mf.`name`,FROM_UNIXTIME(mf.mtime,'%Y-%m-%d %H:%m:%s') AS RegTime,COUNT(pr.mid) AS allRecord

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/query/boardStatistics.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '牌局统计',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();

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
    getPayCount();
    grid.parameters = new Object();
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.parameters['realname'] = $('#realname').val();
    grid.parameters['pstatus'] = $('#pstatus').val();
    
    grid.refresh(true);
}

function getPayCount() {
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var mid = $('#mid').val();
    $.ajax({
        type:'post',
        url:sys.rootPath + '/query/queryBoardStatisticsByCount.html',
        data:{
            endTime:endTime,
            startTime:startTime,
            mid:mid
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(datas){
            if (datas == null || datas == '') {
                $('#playCount').text('0');
            }else{
                $('#playCount').text(datas);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){

        }
    });
}







