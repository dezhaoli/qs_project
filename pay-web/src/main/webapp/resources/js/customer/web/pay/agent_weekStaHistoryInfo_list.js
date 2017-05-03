var gameType = $('#gameType').val();

var dtGridColumns = [{
    id : 'stadate',
    title : '结算时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'totalrebate',
    title : '结算金额',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'settlerebate',
    title : '实际发放金额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'date',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return "<button class='btn btn-primary' style='border-radius: 5px;'" +
            'onclick="showDetail(\''+record.date+'\',\''+record.stadate+'\')">查看明细</button>' +
            "&nbsp;<button class='btn btn-warning' style='border-radius: 5px;'" +
            'onclick="exportExcel(\''+record.date+'\',\''+record.stadate+'\')">导出Excel表格</button>';
    }
}];

function showDetail(date,stadate) {
    layer.open({
        type: 2,
        title:stadate + ' 结算发放详情',
        area: ['95%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/corpPay/agentWeekInfoStaHistoryDetailUi.html?date='+
        date+'&stadate='+stadate+'&gameType='+gameType
    });
}

function exportExcel(date,stadate) {
    layer.confirm('确认要导出Excel吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $('#iframeOut').attr("src",sys.rootPath +"/corpPay/exportDetailExcel.html?gameType="+gameType+"&date="
            + date + "&stadate="+stadate+ "_结算发放详情");

        /*$.ajax({
            type : "POST",
            url : sys.rootPath + '/' + gameType + "/exportDetailExcel.html",
            data : {
                date:date,
                stadate:stadate + "_结算发放详情"
            },
            dataType : "json",
            beforeSend : function() {

            },
            success : function(resultdata) {
                layer.msg(resultdata.message, {icon: 6});
            },
            error : function(data, errorMsg) {
                $('#iframeOut').html(data.responseText);
                console.log(data.responseText);
                layer.msg("系统繁忙，导出失败！", {icon: 5});
            }
        });*/
        layer.msg("导出成功！", {icon: 6});
    }, function(){
        count = 0;
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
    loadURL : sys.rootPath + '/corpPay/agentWeekInfoStaHistory.html?gameType='+gameType,
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',//'refresh|print|export[excel,csv,pdf,txt]'
    exportFileName : '代理商历史结算发放列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }

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
    grid.parameters['mid'] = $('#mid').val();
    grid.refresh(true);
}








