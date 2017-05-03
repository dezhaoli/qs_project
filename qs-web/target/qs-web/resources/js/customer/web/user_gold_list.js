var  queryType = $('#queryType').val();

var dtGridColumns = [{
    id : 'mid',
    title : 'MID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
        /*return '<a href="#" style="cursor: pointer;" ' +
            'onclick="showUserInfoByName(\'' + record.mid + '\')">'+value+'</a>';*/
    }
}, {
    id : 'name',
    title : '用户昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'sex',
    title : '性别',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 0) return '男';
        if (value == 1) return '女';
        return value;
    }
},{
    id : 'icon',
    title : '头像',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<img src="' + value + '" style="width: 50px;height: 50px;"/>';
    }
},{
    id : 'email',
    title : '邮箱',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'address',
    title : '地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'identity',
    title : '身份证',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'tel',
    title : '手机号码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'mtime',
    title : '注册时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'gold',
    title : '拥有金币',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'mid',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return "<button class='btn btn-mini btn-success' style='border-radius: 5px;'" +
            'onclick="showUserInfo(\''+value+'\')">查看用户信息</button>';
    }
}];

/*function edit(mid) {
    webside.common.loadPage('/member/userInfo/editUI.html?id=' + mid);
}
*/

function showUserInfo(mid) {
    layer.open({
        type: 2,
        title: '用户信息',
        area: ['90%', '90%'],
        fixed: false, //不固定
        maxmin: true,
        //content: sys.rootPath + '/query/'+queryType+'/showUserInfoUi.html?mid=' + mid,
        content: sys.rootPath + '/member/showUserInfoUi.html?mid=' + mid,
        success: function (layero, index) {

        }
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
    loadURL : sys.rootPath + '/query/user/userAndGoldList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '充值与金币查询',
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
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.parameters['realname'] = $('#realname').val();
    grid.parameters['gold'] = $('#gold').val();

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
    grid.parameters['startTime'] = $('#startTime').val();
    grid.parameters['endTime'] = $('#endTime').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.parameters['realname'] = $('#realname').val();
    grid.parameters['gold'] = $('#gold').val();
    grid.refresh(true);
}









