var dtGridColumns = [{
    id : 'memberFides.mid',
    title : '游戏ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.memberFides != null) {
            return record.memberFides.mid;
        }
        return value;
    }
},{
    id : 'inviteCode',
    title : '邀请码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'memberFides.name',
    title : '游戏昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.memberFides != null) {
            return record.memberFides.name;
        }
        return value;
    }
},{
    id : 'memberFides.icon',
    title : '头像',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.memberFides != null && record.memberFides.icon != null) {
            return '<img src="' + record.memberFides.icon + '" style="width: 50px;height: 50px;"/>';
        }
        return value;
    }
},{
    id : 'memberFides.mtimeString',
    title : '加入时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.memberFides != null) {
            return record.memberFides.mtimeString;
        }
        return value;
    }
},{
    id : 'parentAgentInfo',
    title : '上级代理商游戏ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.parentAgentInfo != null) {
            return record.parentAgentInfo.mid;
        }
        return value;
    }
},{
    id : 'parentAgentInfo',
    title : '上级代理商名字',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.parentAgentInfo != null) {
            return record.parentAgentInfo.realname;
        }
        return value;
    }
},{
    id : 'business.name',
    title : '所属商务',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (record != null && record.business != null) {
            return record.business.name;
        }
        return value;
    }//parentAgentInfo
}];

/*function edit(mid) {
    webside.common.loadPage('/member/userInfo/editUI.html?id=' + mid);
}

function showUserInfoByName(mid) {
    layer.open({
        type: 2,
        title: '用户信息',
        area: ['90%', '90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/member/showUserInfoUi.html?mid=' + mid,
        success:function (layero, index) {

        }
    });
}*/

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/business/showUserInfo.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '用户信息列表',
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
    grid.parameters['mid'] = $('#midSearch').val();

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
    grid.parameters['mid'] = $('#midSearch').val();
    grid.refresh(true);
}









