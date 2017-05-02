var dtGridColumns = [{
    id : 'mid',
    title : 'MID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'date',
    title : '日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'type',
    title : '类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNe, columnNo) {
        if (value == '1') {
            return '充值购买';
        } else if (value == '4') {
            return '生成房间消耗';
        }else if (value == '5') {
            return '退还房间费用';
        }else if (value == '6') {
            return '绑定奖励';
        }else if (value == '7') {
            return '后台添加金币';
        }else if (value == '8') {
            return '初始赠送';
        }else if (value == '13') {
            return '商务金币添加';
        }else if (value == '16') {
            return '邮件赠送';
        }
        return value;
    }
},{
    id : 'gold',
    title : '金币动态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'nowgold',
    title : '现存金币',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'gametype',
    title : '游戏类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'formid',
    title : '代开房',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'remark',
    title : '备注',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/member/agent/goldOrigin/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '房间配置列表',
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
    var mid = $("#mid").val();
    grid.parameters['mid'] = mid;

    grid.load();
    $("#btnSearch1").click(customSearch1);

    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch1();
        }
    };

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch1() {
    grid.parameters = new Object();
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['date'] = $("#date").val();
    grid.refresh(true);
}


