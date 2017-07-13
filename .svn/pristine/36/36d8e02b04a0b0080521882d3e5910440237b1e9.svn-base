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
},{
    id: 'title',
    title: '标题',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'content',
    title: '内容',
    type: 'string',
    columnClass: 'text-center',
    width:500,
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}, {
    id: 'pushType',
    title: '发布类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (value == 1) {
            return "在线公告";
        }else if(value == 2){
            return "定时公告";
        }else if(value == 3){
            return "滚动公告";
        }
        return value;
    }
}, {
    id: 'intervalTime',
    title: '间隔时间(秒)',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'startTime',
    title: '开始时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'endTime',
    title: '结束时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'pushUserName',
    title: '发布者',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'pushTime',
    title: '发布时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'isEnable',
    title: '状态',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value == '0'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">启用</span>';
        }else  if(value == '1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ" style="color: red;">禁用</span>';
        }
        return value;
    }
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a>' + ' | ' +
            '<a href="#" style="cursor: pointer;" onclick="deleteRocrd(\'' + record.id + '\')">删除</a>';
    }
}];


function edit(id) {
    webside.common.loadPage('/noticeNew/editUI.html?id=' + id)
}

function deleteRocrd(id) {
    //询问框
    layer.confirm('确认要删除吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type : "POST",
            url : sys.rootPath + "/noticeNew/deleteById.html",
            data : {
                id:id
            },
            dataType : "json",
            beforeSend : function() {

            },
            success : function(resultdata) {
                if (resultdata.success == true) {
                    layer.msg(resultdata.message, {icon: 6});
                }else {
                    layer.msg(resultdata.message, {icon: 5});
                }
                grid.refresh(true);
            },
            error : function(data, errorMsg) {
                layer.msg("系统繁忙，删除失败！", {icon: 5});
            }
        });
    }, function(){

    });
}

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: false,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/noticeNew/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '新游戏公告',
    pageSize: pageSize,
    pageSizeLimit: [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function () {
    if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
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
    grid.parameters = new Object();
    grid.parameters['searchTitle'] = $("#searchTitle").val();
    grid.parameters['searchType'] = $("#searchType").val();
    grid.refresh(true);
}


