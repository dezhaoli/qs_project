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
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="showActiUserAddress(\'' + record.mid + '\')">'+value+'</a> ';
    }
},{
    id : 'name',
    title : '奖品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'integral',
    title : '兑换积分',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'isReview',
    title : '审核',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return "未审核";
        }else if (value == 1) {
            return "已审核";
        }else if (value == 2) {
            return "无须审核";
        }
        return value;
    }
},{
    id : 'type',
    title : '活动类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
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
    loadURL : sys.rootPath + '/actiAwardRecord/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '活动中心奖品兑换记录表',
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
    //grid.parameters['realname'] = $('#realname').val();
    grid.parameters['isReview'] = $('#pstatus').val();
    
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
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();
    //grid.parameters['realname'] = $('#realname').val();
    grid.parameters['isReview'] = $('#pstatus').val();
    
    grid.refresh(true);
}

function showActiUserAddress(mid) {
    layer.open({
        type: 2,
        title:"用户联系方式",
        area: ['95%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/actiAwardAddress/showAddressByMidUi.html' +
        '?mid='+mid
    });
}

function getPayCount() {
    var startTime = $('#startTime').val();
    var endTime = $('#endTime').val();
    $.ajax({
        type:'post',
        url:sys.rootPath + '/query/getPayCountByDate.html',
        data:{
            endTime:endTime,
            startTime:startTime
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(datas){
            if (datas == null || datas == '') {
                $('#payCount').text('0.0');
            }else{
                $('#payCount').text(datas);
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){

        }
    });
}






