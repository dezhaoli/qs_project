var gameType = $('#gameType').val();

var dtGridColumns = [{
    id : 'mid',
    title : 'MID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'openid',
    title : '微信openid',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'date',
    title : '结算时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'realname',
    title : '真实姓名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'phone',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'paytotal',
    title : '本周充值总额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'rebatetotal',
    title : '本周返利金额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'bindpeople',
    title : '本周招募人数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'isaward',
    title : '返现状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (0 == value) {
            return "未审核";
        }
        if (1 == value) {
            return "审核通过";
        }
        if (2 == value) {
            return "已发放";
        }
        return value;
    }
},{
    id : 'isaward',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {//rebatetotal
        if (1 == value) {
            return "<button class='btn btn-success' style='border-radius: 5px;'" +
                'onclick="confrimPay(\''+record.openid+'\',\''+record.date+'\',\''+record.mid+'\',\''+record.rebatetotal+'\')">支付</button>';
        }
        return '';
    }
}];

var count = 0;
function confrimPay(openid, date, mid,rebatetotal) {
    if (count == 2) {
        layer.msg("正在支付中，请耐心等待！请勿重复点击按钮，防止重复支付！！！", {icon: 5});
        return;
    }
    if (count != 0) {
        return;
    }
    count = 1;
    if (openid == null || openid == undefined || openid == '') {
        count = 0;
        layer.msg('openid为空，无法支付！',{time:800});
        return;
    }
    if (date == null || date == undefined || date == '') {
        count = 0;
        layer.msg('本期结算日期为空，无法支付！',{time:800});
        return;
    }
    if (mid == null || mid == undefined || mid == '') {
        count = 0;
        layer.msg('mid为空，无法支付！',{time:800});
        return;
    }
    if (rebatetotal == null || rebatetotal == undefined || rebatetotal == '') {
        count = 0;
        layer.msg('本周返利金额为空，无法支付！',{time:800});
        return;
    }
    if (rebatetotal < 1 || rebatetotal > 20000) {
        count = 0;
        layer.msg('本周返利金额不能小于1且大于20000，无法支付！',{time:800});
        return;
    }

    if (rebatetotal > 200) {
        //询问框
        layer.confirm('确认要支付吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            if (count != 1) {
                layer.msg("正在支付中，请耐心等待！请勿重复点击确定按钮，防止重复支付！！！", {icon: 5});
                return;
            }
            count = 2;

            $.ajax({
                type : "POST",
                url : sys.rootPath + '/' + gameType + "/activityPay.html",
                data : {
                    openid:openid,
                    date:date,
                    mid:mid
                },
                dataType : "json",
                beforeSend : function() {

                },
                success : function(resultdata) {
                    if (resultdata.success == true) {
                        layer.msg(resultdata.message, {icon: 6});
                        refreshDataGrid();
                    }else {
                        layer.msg(resultdata.message, {icon: 5});
                        refreshDataGrid();
                    }
                    count = 0;
                },
                error : function(data, errorMsg) {
                    layer.msg("系统繁忙，支付失败！", {icon: 5});
                    refreshDataGrid();
                    count = 0;
                }
            });
        }, function(){
            if (count == 2) {
                return;
            }
            count = 0;
        });
    }else {
        $.ajax({
            type : "POST",
            url : sys.rootPath + '/' + gameType + "/activityPay.html",
            data : {
                openid:openid,
                date:date,
                mid:mid
            },
            dataType : "json",
            beforeSend : function() {
                //index = layer.load();
            },
            success : function(resultdata) {
                /*layer.close(index);
                 if (resultdata.success) {
                 layer.msg(resultdata.message, {
                 icon : 1
                 });
                 webside.common.loadPage(jumpUrl);
                 } else {
                 layer.msg(resultdata.message, {
                 icon : 5
                 });
                 }*/
                if (resultdata.success == true) {
                    layer.msg(resultdata.message, {icon: 6});
                    refreshDataGrid();
                }else {
                    layer.msg(resultdata.message, {icon: 5});
                    refreshDataGrid();
                }
                count = 0;
            },
            error : function(data, errorMsg) {
                /* layer.close(index);
                 layer.msg(data.responseText, {
                 icon : 2
                 });*/
                layer.msg("系统繁忙，支付失败！", {icon: 5});
                refreshDataGrid();
                count = 0;
            }
        });
    }
}

function GetDateStr(date,AddDayCount) {
    var dd = new Date(date);
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

/*document.write("前天："+GetDateStr(-2));
document.write("<br />昨天："+GetDateStr(-1));
document.write("<br />今天："+GetDateStr(0));
document.write("<br />明天："+GetDateStr(1));
document.write("<br />后天："+GetDateStr(2));
document.write("<br />大后天："+GetDateStr(3));*/

var countPay = 0;
$('#oneKeyPay').on('click',function () {
    if (countPay == 2) {
        layer.msg("正在批量支付中，请耐心等待！请勿重复点击按钮，防止重复支付！！！", {icon: 5});
        return;
    }
    if (countPay != 0) {
        return;
    }

    countPay = 1;

    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    var requirtDate = searchYear + "-" + searchDate;
    if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        //layer.msg('请选择查询时间',{time:800});
        //return;
        requirtDate = $('#lastSunday').val();
    }

    var  first = GetDateStr(requirtDate,-6);
    var last = requirtDate;

    layer.open({
        content: '确认要一键支付'+first + '--' + last+'吗？,这个操作大概需要几十秒钟，系统提示支付成功后，再进行操作，切勿多次点击确定、防止重复提交!'
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            if (countPay != 1) {
                layer.msg("正在批量支付中，请耐心等待！请勿重复点击确定按钮，防止重复支付！！！", {icon: 5});
                return;
            }
            countPay = 2;

            $("#oneKeyPay").prop('disabled', true).html('正在支付中...');
            //$("#oneKeyPay").prop('disabled', false).html('一键审核');

            $.ajax({
                type : "POST",
                url : sys.rootPath + '/' + gameType + "/activityBatchPay.html",
                data : {
                    searchDate:requirtDate
                },
                dataType : "json",
                beforeSend : function() {

                },
                success : function(resultdata) {
                    if (resultdata.success == true) {
                        layer.msg(resultdata.message, {icon: 6});
                        refreshDataGrid();
                    }else {
                        layer.msg(resultdata.message, {icon: 5});
                        refreshDataGrid();
                    }
                    $("#oneKeyPay").prop('disabled', false).html('一键支付');
                    countPay = 0;
                },
                error : function(data, errorMsg) {
                    layer.msg("系统繁忙，支付失败！", {icon: 5});
                    refreshDataGrid();
                    countPay = 0;
                    $("#oneKeyPay").prop('disabled', false).html('一键支付');
                }
            });
        }
        ,btn2: function(index, layero){
            if (countPay == 2) {
                return;
            }
            $("#oneKeyPay").prop('disabled', false).html('一键支付');
            countPay = 0;
        }
        ,cancel: function(){
            //右上角关闭回调
            if (countPay == 2) {
                return;
            }
            $("#oneKeyPay").prop('disabled', false).html('一键支付');
            countPay = 0;
        }
    });

});

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var lastMonday = $('#lastMonday').val();
var lastSunday = $('#lastSunday').val();

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/'+gameType+'/agentActivitySta.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|export[excel]',//'refresh|print|export[excel,csv,pdf,txt]'
    exportFileName : '代理商上周结算发放详情' + lastMonday + "--" + lastSunday,
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30,100,200]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }

    grid.parameters = new Object();
    grid.parameters['searchDate'] = $('#lastSunday').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.parameters['isaward'] = $('#isaward').val();

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
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    var requirtDate = searchYear + "-" + searchDate;
    if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        //layer.msg('请选择查询时间',{time:800});
        //return;
        requirtDate = $('#lastSunday').val();
    }
    grid.parameters = new Object();
    grid.parameters['searchDate'] = requirtDate;
    //grid.parameters['searchDate'] = $('#lastSunday').val();
    grid.parameters['isaward'] = $('#isaward').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.refresh(true);
}

function refreshDataGrid() {
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    var requirtDate = searchYear + "-" + searchDate;
    if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        //layer.msg('请选择查询时间',{time:800});
        //return;
        requirtDate = $('#lastSunday').val();
    }
    grid.parameters = new Object();
    grid.parameters['searchDate'] = requirtDate;
    //grid.parameters['searchDate'] = $('#lastSunday').val();
    grid.parameters['isaward'] = $('#isaward').val();
    grid.parameters['mid'] = $('#mid').val();
    grid.refresh(true);
}







