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
    id: 'gold',
    title: '消耗金币',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'integral',
    title: '赠送积分',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'actiType',
    title: '活动类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return getActiNameByType(value);
    }
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a> '  +  ' | '
        +' <a href="#" style="cursor: pointer;" onclick="deleteRecord(\'' + record.id + '\')">删除</a>';
    }
}];


function edit(id) {
    webside.common.loadPage('/actiIntegralCfg/editUI.html?id=' + id)
}

function deleteRecord(id) {
    layer.msg('你确定要删除吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            $.ajax({
                type: "POST",
                url: sys.rootPath + '/actiIntegralCfg/deleteById.html',
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
    loadURL: sys.rootPath + '/actiIntegralCfg/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '活动中心积分配置表',
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


var activityList = $('#activityList').text();
activityList = JSON.parse(activityList);
function getActiNameByType(type) {
    if (activityList != undefined && activityList != null) {
        for (var i = 0;i < activityList.length;i++) {
            var code = activityList[i].code;
            if (type == code) {
                var name = activityList[i].name;
                return name;
            }
        }
    }
    return type;
}

