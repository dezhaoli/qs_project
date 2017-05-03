var dtGridColumns = [{
    id : 'id',
    title : 'ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'name',
    title : '真实名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'phone',
    title : '手机号码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cname',
    title : '分公司',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totalinvite',
    title : '招募人数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totalpay',
    title : '充值总额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cname',
    title : '代理商覆盖区域',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return "";
        /*'<button class="btn btn-primary" onclick="showAreaDetail(\'' + record.id + '\')">查看详情</button>&nbsp;';*/
    }
},{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        //return '<a href="#" style="cursor: pointer;" onclick="edit(\''+record.id+'\')">编辑</a>';
        return '<button class="btn btn-primary" onclick="fixPhoneNumber(\'' + record.id + '\')">编辑</button>&nbsp;'
            +'<button class="btn btn-success" onclick="resetPwd(\'' + record.id + '\')">重置密码</button>&nbsp;'
            +'<button class="btn btn-warning" onclick="showBusinessBackstage(\'' + record.id + '\')">查看商务后台</button>';
    }
}];

function resetPwd(id) {
    /*layer.msg('确定要重置密码吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            layer.open({
                type: 2,
                title:'重置密码',
                area: ['70%','70%'],
                fixed: false, //不固定
                maxmin: true,
                content: '/agent/business/restPwdUi.html?id='+id
            });
        }
    });*/
    layer.open({
        type: 2,
        title:'重置密码',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/business/restPwdUi.html?id='+id
    });
}

function showBusinessBackstage(id) {
	var url = sys.rootPath +"/agent/business/setBusinessLoginUserKey.html";
    $.ajax({
		type : "POST",
		url : url,
		data : {"id":id},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				var burl=msg.businessUrl+"?id="+id+"&sign="+msg.sign+"&currentTime="+msg.currentTime;
				$('#businessUrl').attr('href',burl); 
				$("#selectBusinessById").click();
			} else {
				layer.msg("请尝试安全登入！", {
					icon : 5,
					time : 500
				});
			}
		}

	});
    //webside.common.loadPage('/game/ip/editUI.html?id=' + id);
}

function fixPhoneNumber(id) {
    layer.open({
        type: 2,
        title:'修改商务信息',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/business/updatePhoneNumberUi.html?id='+id
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
    loadURL : sys.rootPath + '/agent/business/list.html',
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
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
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
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}

//详情信息
function showAreaDetail(id){
	 layer.open({
	        type: 2,
	        title:'代理商覆盖区域业绩',
	        area: ['80%','80%'],
	        fixed: false, //不固定
	        maxmin: true,
	        content: sys.rootPath + '/agent/business/detailInfo.html?id='+id
	    });
}
