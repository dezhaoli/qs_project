var dtGridColumns = [
    {
        id: 'mid',
        title: 'MID',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header'
    }, {
        id: 'testType',
        title: '名单是否生效',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNe, columnNo) {
            if (value == '0') {
                return '<span class="label label-sm label-info arrowed arrowed-in" style="color: #ff6e00;">未生效</span>';
            } else if (value == '1') {
                return '<span class="label label-sm label-info arrowed arrowed-righ">已生效</span>';
            }
        }
    }, {
        id: 'name',
        title: '名称',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header'
    }, {
        id: 'icon',
        title: '头像',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNe, columnNo) {
            return '<img src="' + value + '" style="width: 50px;height: 50px;"/>';
        }
    }, {
        id: 'id',
        title: '名单生效/失效',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header',
        hideType: 'xs',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            return '<a href="#" style="cursor: pointer;" onclick="takeEffectRecord(\'' + record.id + '\')">名单生效/失效</a>';
        }
    }, {
        id: 'id',
        title: '删除',
        type: 'string',
        columnClass: 'text-center',
        headerClass: 'dlshouwen-grid-header',
        hideType: 'xs',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            return '<a href="#" style="cursor: pointer;" onclick="deleteRecord(\'' + record.id + '\')">删除</a>';
        }
    }];

function deleteRecord(id) {
    //webside.common.loadPage('/game/whiteList/editUI.html?id=' + id);
    if (!window.confirm("确定要删除吗？")) {
        return;
    }
    $.ajax({
        url: sys.rootPath + '/game/whiteList/deleteById.html',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        data: {
            id: id
        },
        timeout: 5000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {

        },
        success: function (data, textStatus, jqXHR) {
            layer.msg("删除成功！", {icon: 6,time:500});
            grid.refresh(true);
        },
        error: function (xhr, textStatus) {
            layer.msg("删除异常，删除失败！", {icon: 5,time:500});
            grid.refresh(true);
        },
        complete: function () {

        }
    });
}

function takeEffectRecord(id) {
    $.ajax({
        url: sys.rootPath + '/game/whiteList/takeEffectById.html',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        data: {
            id: id
        },
        timeout: 5000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend: function (xhr) {

        },
        success: function (data, textStatus, jqXHR) {
            layer.msg("操作成功！", {icon: 6,time:500});
            grid.refresh(true);
        },
        error: function (xhr, textStatus) {
            layer.msg("发生异常，操作失败！", {icon: 5,time:500});
            grid.refresh(true);
        },
        complete: function () {

        }
    });
}

function effectOrUnEffect(type) {
    $.ajax({
        type : "POST",
        async: true,    //或false,是否异步
        url : sys.rootPath + "/game/whiteList/updateEffectOrUnEffect.html",
        data : {
            type:type
        },
        dataType : "json",
        success : function(resultdata) {
            if (resultdata.success) {
                layer.msg(resultdata.message, {
                    icon : 1
                });
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
            grid.refresh(true);
        },
        error : function(data, errorMsg) {
            layer.msg(data.responseText, {
                icon : 2
            });
            grid.refresh(true);
        }
    });
}


//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: true,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/game/whiteList/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '房间配置列表',
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


