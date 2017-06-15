var dtGridColumns = [
{
    id: 'id',
    title: '序号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo + 1;
    }
}, {
    id: 'mid',
    title: '游戏ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'sendTime',
    title: '发放金币时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'type',
    title: '活动类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return getActiNameByType(value);
    }
},{
    id: 'gold',
    title: '金币数量',
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
    loadURL: sys.rootPath + '/actiSendGold/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '活动金币发放列表',
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
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    querySumSendGoldByCondition();

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
    querySumSendGoldByCondition();
    grid.parameters = new Object();
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.parameters['type'] = $('#type').val();
    grid.refresh(true);
}


var activityListJson = $('#activityListJson').text();
activityListJson = JSON.parse(activityListJson);
function getActiNameByType(type) {
    if (activityListJson != undefined && activityListJson != null) {
        for (var i = 0;i < activityListJson.length;i++) {
            var code = activityListJson[i].code;
            if (type == code) {
                var name = activityListJson[i].name;
                return name;
            }
        }
    }
    return type;
}

function querySumSendGoldByCondition() {
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    var mid = $('#mid').val();
    var type = $('#type').val();
    $.ajax({
        type:'post',
        url:sys.rootPath + '/actiSendGold/querySumSendGoldByCondition.html',
        data:{
            endTime:endTime,
            startTime:startTime,
            mid:mid,
            type:type
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(datas){
            if (datas == null || datas == '') {
                $('#sumGold').text('0');
            }else{
                $('#sumGold').text(datas);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){

        }
    });
}