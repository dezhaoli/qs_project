var dtGridColumns = [{
    id : 'mid',
    title : 'ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%"
}/*, {
    id : 'truename',
    title : '真实姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%"
}*/, {
    id : 'nickname',
    title : '微信昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%"
}, {
    id : 'comeTime',
    title : '加入时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%"
},{
    id : 'childCount',
    title : '招募人数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%"
},{
    id : 'mid',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    width:"20%",
    //hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<i style="font-size:24px;" class="fa fa-user fa-2x blue iconShow tooltip-success"' +
            'onclick="showAgentDetailPage(\'' + record.mid + '\')"></i>'
            + '<i style="font-size:24px;" class="fa fa-users fa-2x green iconShow tooltip-success"' +
            ' onclick="queryChildrenAgent(\'' + record.mid + '\')"></i>';

        /*return '<button class="btn btn-success" onclick="showAgentDetailPage(\'' + record.mid + '\')">' +
            '直属代理商详情</button>&nbsp;'
            +'<button class="btn btn-warning" onclick="queryChildrenAgent(\'' + record.mid + '\')">' +
            '直属代理商子级代理查询 </button>';*/
    }
}];

function showAgentDetailPage(id) {
    webside.common.loadPage('/agentQuery/showAgentDetailPageUi.html?id=' + id);
}

function queryChildrenAgent(id) {
    webside.common.loadPage('/agentQuery/queryChildrenAgentUi.html?id=' + id);
}

function fixPhoneNumber(id) {
    layer.open({
        type: 2,
        title:'修改手机号码',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/business/updatePhoneNumberUi.html?id='+id
    });
}

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/agentQuery/queryAgentList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '代理商查询',
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
    grid.parameters['belongid'] = $('#belongid').val();

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
    grid.parameters['belongid'] = $('#belongid').val();
    grid.parameters['mid'] = $("#searchKey").val();
    grid.refresh(true);
}


