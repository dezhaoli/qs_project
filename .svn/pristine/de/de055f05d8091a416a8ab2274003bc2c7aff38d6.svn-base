var dtGridColumns = [{
    id: 'id',
    title: '序号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return dataNo + 1;
    }
}, {
    id: 'mktime',
    title: '时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}, {
    id: 'title',
    title: '邮件标题',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'expired',
    title: '过期时间',
    type: 'date',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'important',
    title: '是否重要邮件',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">否</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ">是</span>';
        }
    }
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<button class="btn btn-primary" onclick="show(\'' + record.id + '\')">查看详细</button>' + ' | ' +
            '<button class="btn btn-warning" onclick="delMail(\'' + record.id + '\')">删除</button>';
    }
}];


function show(id) {
    layer.open({
        type: 2,
        title:'邮件详情',
        area: ['80%','80%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/game/mail/showDetail.html?id='+id
    });
}

function delMail(id) {
    layer.open({
        content: '确定要删除？'
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            parent.layer.close(index);
            $.ajax({
                type: "POST",
                url: sys.rootPath + "/game/mail/delMail.html",
                data:{
                    id :id
                },//将对象序列化成JSON字符串
                dataType:"json",
                //contentType : 'application/json;charset=utf-8', //设置请求头信息
                success: function(data){
                    if (data.success == true){
                        layer.msg(data.message, {icon : 6});
                        grid.refresh(true);
                    }else{
                        layer.msg(data.message, {icon : 5});
                    }
                },
                error: function(res){
                    layer.msg('系统异常，删除失败！', {icon : 5});
                }
            });
        }
        ,btn2: function(index, layero){
            parent.layer.close(index);

        }
        ,cancel: function(){
            parent.layer.close(index);
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
    loadURL: sys.rootPath + '/game/mail/list.html',
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

    grid.parameters = new Object();
    grid.parameters['startTime'] = $("#startTime").val();
    grid.parameters['endTime'] = $("#endTime").val();

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
    //grid.parameters['name'] = $("#searchKey").val();
    grid.parameters['startTime'] = $("#startTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.refresh(true);
}

$('#mailListTap').on('click',function () {
    $('#searchTap').show();
    grid.refresh(true);
});

$('#addMailTap').on('click',function () {
    $('#searchTap').hide();
});