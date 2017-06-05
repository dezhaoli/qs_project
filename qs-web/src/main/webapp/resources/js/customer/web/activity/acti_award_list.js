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
    id: 'name',
    title: '奖品',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'imgUrl',
    title: '图片',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<img src="'+value+'" style="width: 50px;height: 50px;"/>';
    }
},{
    id: 'integral',
    title: '积分',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'awardNum',
    title: '奖品个数',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'review',
    title: '审核',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-in-right">无需审核</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ" style="color: red;">需审核</span>';
        }else {
            return '';
        }
    }
},{
    id: 'limitNum',
    title: '限制次数',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-in-right">无限制</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ" style="color: red;">限制</span>';
        }else {
            return '';
        }
    }
},{
    id: 'receiveNum',
    title: '可领取次数',
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
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a> '  +  ' | '
            +' <a href="#" style="cursor: pointer;" onclick="deleteRecord(\'' + record.id + '\')">删除</a>';
        /*+' <a href="#" style="cursor: pointer;" onclick="updateAPKPak(\'' + record.id + '\')">更新APK包</a>'*/
    }
}];


function edit(id) {
    webside.common.loadPage('/award/editUI.html?id=' + id);
}

function deleteRecord(id) {
    layer.msg('你确定要删除吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            $.ajax({
                type: "POST",
                url: sys.rootPath + '/award/deleteById.html',
                data: {
                    id:id
                },
                success: function (data) {
                    if (data.success = true) {
                        layer.msg(data.message, {
                            icon : 6
                        });
                        grid.refresh(true);
                    }else {
                        layer.msg(data.message, {
                            icon : 5
                        });
                        grid.refresh(true);
                    }
                },
                error:function () {
                    layer.msg("发现异常，删除失败！", {
                        icon : 5
                    });
                    grid.refresh(true);
                }
            });
        }
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
    loadURL: sys.rootPath + '/award/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '活动中心奖品表',
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
    /*document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };*/

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}


