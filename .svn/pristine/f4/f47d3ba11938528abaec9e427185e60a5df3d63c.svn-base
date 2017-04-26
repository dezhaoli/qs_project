var dtGridColumns = [{//SELECT a.fmid,SUM(a.pamount) AS ptotal,b.name AS username
    id : 'paytime',
    title : '日期',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'orderNo',
    title : '订单号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'payman',
    title : '充值用户ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<a onclick="showUserInfo(\''+value+'\')">'+value+'</a>';
    }
},{
    id : 'paymanName',
    title : '昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'recmen',
    title : '被充值人ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<a onclick="showUserInfo(\''+value+'\')">'+value+'</a>';
    }
},{
    id : 'loginCount',
    title : '登录',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'pamount',
    title : '充值金额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'pmoneynow',
    title : '充值时金币',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'pcoins',
    title : '获得金币',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'pstatus',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 2) {
            return "成功";
        }else if (value == 1) {
            return "取消订单";
        }else{
            return "失败";
        }
    }
},{
    id : 'ptransno',
    title : '平台订单号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'pmode',
    title : '充值方式',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 1) {
            return "支付宝";
        }else if (value == 2) {
            return "QQ";
        }else if (value == 3) {
            return "豌豆夹";
        }else if (value == 4) {
            return "苹果";
        }else if (value == 5) {
            return "360支付";
        }else if (value == 6) {
            return "微信支付";
        }else if (value == 7) {
            return "XY支付";
        }else{
            return "未知";
        }
    }
},{
    id : 'parentid',
    title : '父ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'isagent',
    title : '代理',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return "否";
        }else {
            return "是";
        }
        //return value;
    }
},{
    id : 'bizid',
    title : '商务ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}];

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

/*from_unixtime(a.ptime,'%Y-%m-%d %H:%m:%s') AS paytime, -- 日期
(SELECT CONCAT('QS',from_unixtime(a.ptime,'%Y%m%d'),a.pid)) AS orderNo, -- 订单号
a.fmid AS payman, -- 充值用户ID
(SELECT realname FROM memberfides0 b WHERE b.realname = a.fmid LIMIT 1) AS paymanName, -- 用户名
a.tmid AS recmen, -- 被充值人ID
a.pcard AS loginCount, -- 登录
a.pamount, -- 充值金额
a.pmoneynow, -- 充值时金币
a.pcoins, -- 获得金币
a.pstatus, -- 状态
a.ptransno, -- 平台订单号
a.pmode, -- 充值方式
a.parentid, -- 父ID
a.isagent, -- 代理
a.bizid -- 商务ID*/

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/query/orderDetail.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|export[excel]',
    exportFileName : '订单详情',
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
    grid.parameters['parentid'] = $('#parentid').val();
    grid.parameters['pstatus'] = $('#pstatus').val();
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
    grid.parameters['parentid'] = $('#parentid').val();
    grid.parameters['pstatus'] = $('#pstatus').val();
    grid.refresh(true);
}









