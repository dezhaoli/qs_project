var dtGridColumns = [{
    id : 'data.mid',
    title : 'ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.mid != null) {
            return record.mid;
        }
        return "";
    }
},{
    id : 'data.realname',
    title : '代理商姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.realname != null) {
            return record.realname;
        }
        return "";
    }
},{
    id : 'data.alevel',
    title : '代理商级别',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.alevel != null) {
            return record.alevel;
        }
        return "";
    }
},{
    id : 'data.totalpay',
    title : '团队充值',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.totalPagePay != null) {
            $('#payPageCount').text('当前页总额：' + record.totalPagePay);
        }else{
            $('#payPageCount').text('当前页总额：' +0);
        }
        if (record != null && record.totalPayCount != null) {
            $('#payCount').text('团队充值总额：' + record.totalPayCount);
        }else{
            $('#payCount').text('团队充值总额：' +0);
        }
        if (record != null && record.totalpay != null) {
            return record.totalpay;
        }
        return "";
    }
},{
    id : 'rebatetotal',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();

        return '<button class="btn btn-success" ' +
            'onclick="payDetail(\''+record.mid+'\',\''+startTime+'\',\''+endTime+'\')">充值明细</button>';
    }
}];

function payDetail(mid,startTime,endTime) {
    layer.open({
        type: 2,
        title:mid + '充值明细',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/business/user/payDetailUi.html' +
        '?mid='+mid+"&startTime="+startTime+"&endTime="+endTime
    });
}

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/business/agentTeamRechargeStatistics.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '代理商团队充值统计',
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
    grid.parameters['starDate'] = $('#startTime').val();
    grid.parameters['endDate'] = $('#endTime').val();

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
    grid.parameters['starDate'] = $('#startTime').val();
    grid.parameters['endDate'] = $('#endTime').val();
    grid.refresh(true);
}










