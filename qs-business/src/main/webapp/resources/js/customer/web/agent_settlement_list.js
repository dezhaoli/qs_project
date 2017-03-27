var dtGridColumns = [{
    id : 'mid',
    title : 'ID/姓名',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.realname != null) {
            return value + "/" +  record.realname;
        }
        return value + "/未填写";
    }
},{
    id : 'paytotal',
    title : '充值总额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'rebatetotal',
    title : '结算总额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'isaward',
    title : '是否发放',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (2 == value) {
            return "已发放";
        }else{
            return "未发放";
        }
        return value;
    }
},{
    id : 'mid',
    title : '明细',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record != '') {
            $('#weekTotal').text(record.weekPayTotal);
            $('#countMoney').text(record.weekSettleTotal);
        }
        var date = getSelectDate();
        return '<button class="btn btn-success" ' +
            'onclick="detail(\''+value+'\',\''+date+'\')">明细</button>';
    }
}];

function detail(mid,date) {
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').children('option:selected').text();
    var a = searchDate.substr(0,6);
    var b = searchDate.substr(8);
    var title = searchYear + '年' + a + "--" + searchYear + '年' + b;
    layer.open({
        type: 2,
        title:title,
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/business/settlementDetailUi.html' +
        '?mid='+mid + "&date="+ date
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
    loadURL : sys.rootPath + '/business/agentSettlement.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '代理商结算',
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
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        layer.msg('请选择查询时间',{time:800});
        return;
    }
    var requirtDate = searchYear + "-" + searchDate;
    grid.parameters = new Object();
    //grid.parameters['mid'] = $('#midSearch').val();
    grid.parameters['date'] = requirtDate;
    grid.refresh(true);
}

function getSelectDate() {
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        layer.msg('请选择查询时间',{time:800});
        return;
    }
    var requirtDate = searchYear + "-" + searchDate;
    return requirtDate;
}










