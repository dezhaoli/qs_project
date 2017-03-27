var dtGridColumns = [{
    id : 'mid',
    title : 'MID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" ' +
            'onclick="showUserInfoByName(\'' + record.mid + '\')">'+value+'</a>';
    }
}, {
    id : 'name',
    title : '用户名',
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
        if (value == 1) return '男';
        if (value == 0) return '女';
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
    id : 'mid',
    title : '编辑',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<button class="btn btn-primary" onclick="edit(\'' + record.mid + '\')">编辑</button>';
    }
}];

function edit(mid) {
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
}

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/member/management.html',
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
    grid.parameters['name'] = $("#userName").val();
    grid.parameters['sitemid'] = $('#sitemidSearch').val();
    grid.parameters['mid'] = $('#midSearch').val();
    grid.refresh(true);
}



//////////////////////////////////////////////////////////////////////////////////////

function cardRecord(mid) {
    //var mid = $('#mid').val();
    layer.open({
        type: 2,
        title: '牌局记录',
        area: ['90%', '90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath +'/member/agent/cardRecord/listUi.html?mid=' + mid,
        success:function (layero, index) {

        }
    });
}

function goldOrigin(mid) {
    //var mid = $('#mid').val();
    layer.open({
        type: 2,
        title: '金币来源',
        area: ['90%', '90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath +'/member/agent/goldOrigin/listUi.html?mid=' + mid,
        success:function (layero, index) {

        }
    });
}


$(function () {

    function searchUser(sitemidSearch,midSearch) {
        $.ajax({
            type: "POST",
            url: sys.rootPath +"/member/userInfo/edit.html",
            data: {
                sitemid:sitemidSearch,
                mid:midSearch
            },
            dataType: "json",
            success: function (data) {
                layer.msg("查询成功！", {icon: 6,time:250});
                setElementValue(data);
            },
            error: function (msg) {
                layer.msg("没有查询到结果！", {icon: 5,time:500});
            }
        });
    }

    function setElementValue(data) {
        $('#name').val(data.name);
        $('#lastLogin').val(data.lastLogin);
        $('#city').val(data.city);
        $('#idCard').val(data.idCard);
        $('#yellowvip').val(data.yellowvip);
        $('#icon').val(data.icon);
        $('#mid').val(data.mid);
        $('#sitemid').val(data.sitemid);
        $('#gold').val(data.gold);
        $('#bindtime').val(data.bindTime);
        $('#comeFrom').val(data.comeFrom);
        $('#isyearvip').val(data.isyearvip);
        $('#sex').val(data.sex);
        $('#born').val(data.born);
        $('#bankpasswd').val(data.bankpasswd);
        $('#mktime').val(data.mktime);
        $('#belongid').val(data.belongid);
        $('#invite').val(data.invite);
        $('#status').val(data.status);
        $('#passwd').val(data.sbId);
    }

});



function addPayTest(id) {
    layer.open({
        type: 2,
        title:'测试充值',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/authorization/testPayUi.html?id='+id
    });
}








