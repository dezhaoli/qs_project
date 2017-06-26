var dtGridColumns = [{
    id : 'mmid',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return dataNo + 1;
    }
}, {
    id : 'mmid',
    title : 'MID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'name',
    title : '昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'amid',
    title : '代理商',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if(value == undefined || value == null || value == ''){
            return '<span class="label label-sm label-info arrowed arrowed-righ">否</span>';
        }else{
            return '<span class="label label-sm label-info arrowed arrowed-in-right" style="color: red;">是</span>';
        }
    }
},{
    id : 'mmid',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record.amid == undefined || record.amid == null || record.amid == '') {
            return '<a href="#" style="cursor: pointer;" onclick="cannelBinding(\'' + value + '\')">取消绑定</a> '
        }else {
            return '直属代理商不能解绑';
        }
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
    loadURL : sys.rootPath + '/member/agent/cancelAgentMembershipBinding/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '取消代理商会员绑定',
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
    grid.parameters['mid'] = $("#mid").val();

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
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['memberMid'] = $("#memberMid").val();
    grid.refresh(true);
}

function cannelBinding(mid) {
    $.ajax({
        type:'post',
        url:sys.rootPath + '/member/agent/cancelInviter/edit.html',
        data:{
            mid:mid
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(data){
            if (data.success == true) {
                layer.msg(data.message, {
                    icon : 6
                });
                customSearch();
            }else{
                layer.msg(data.message, {
                    icon : 5
                });
            }
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            layer.msg("系统异常！", {
                icon : 5
            });
        }
    });
}
