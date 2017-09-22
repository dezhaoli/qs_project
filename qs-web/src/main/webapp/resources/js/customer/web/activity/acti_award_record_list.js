var dtGridColumns = [{
    id : 'no',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo+1;
    }
},{
    id : 'mid',
    title : 'MID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="showActiUserAddress(\'' + record.mid + '\')">'+value+'</a> ';
    }
},{
    id : 'name',
    title : '奖品名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'integral',
    title : '兑换积分',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'isReview',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return "未审核";
        }else if (value == 1) {
            return "已审核";
        }else if (value == 2) {
            return "无须审核";
        }else if (value == 3) {
            return "忽略类";
        }
        return value;
    }
},{
    id : 'type',
    title : '活动类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return getActiNameByType(value);
    }
},{
    id : 'createTime',
    title : '创建时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'sendTime',
    title : '发放时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'descr',
    title : '描述',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value) {
            return value + "元";
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
         if (record.isReview == 0) {//未审核
             return '<button class="btn btn-primary btn-sm" style="border-radius: 5px;" onclick="check(\'' + record.id + '\')">发放</button>';
         }else if (record.isReview == 1) {
             return '<span class="label label-sm label-info arrowed arrowed-righ">已审核</span>';
         }else if (record.isReview == 2) {
             return '<span class="label label-sm label-info arrowed arrowed-righ">无须审核</span>';
         }else if (record.isReview == 3) {
             return '<span class="label label-sm label-info arrowed arrowed-righ">忽略类</span>';
         }else {
             return record.isReview;
         }
     }
 }];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/actiAwardRecord/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '活动中心奖品兑换记录表',
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
    grid.parameters['type'] = $('#type').val();
    grid.parameters['isReview'] = $('#pstatus').val();
    
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
    grid.parameters['type'] = $('#type').val();
    grid.parameters['isReview'] = $('#pstatus').val();
    
    grid.refresh(true);
}

function showActiUserAddress(mid) {
    layer.open({
        type: 2,
        title:"用户联系方式",
        area: ['95%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/actiAwardAddress/showAddressByMidUi.html' +
        '?mid='+mid
    });
}

function check(id) {
    layer.msg('确定要发放吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            $.ajax({
                type:'post',
                url:sys.rootPath + '/actiAwardRecord/checkById.html',
                data:{
                    id:id
                },
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success:function(datas){
                    datas = JSON.parse(datas);
                    if (datas.success == true) {
                        layer.msg(datas.message, {icon : 6});
                        customSearch();
                    }else {
                        layer.msg(datas.message, {icon : 5});
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    layer.msg("系统异常!", {icon : 5});
                }
            });
        }
    });
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




