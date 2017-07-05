var dtGridColumns = [
{
    id: 'id',
    title: 'ID',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}, {
    id: 'mid',
    title: 'mid',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" ' +
            'onclick="showUserInfo(\'' + value + '\')">'+value+'</a>';
    }
}, {
    id: 'alipay',
    title: '支付宝账号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, {
    id: 'realname',
    title: '真实姓名',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'phone',
    title: '手机号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id: 'qq',
    title: 'qq',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'mktime',
    title: '创建时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'lastlogintime',
    title: '上次登录',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}/*, {
    id: 'systempasswd',
    title: '系统明文密码',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}*/, {
    id: 'status',
    title: '状态',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNe, columnNo) {
        if (value == 0) {
            return "正常";
        }else {
            return "禁用";
        }
    }
},{
    id: 'alevel',
    title: '级别',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNe, columnNo) {
        return value;
    }
},{
    id: 'glevel',
    title: 'glevel',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'remark',
    title: '备注',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNe, columnNo) {
        return value;
    }
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    //hideType: 'xs',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<button class="btn btn-primary" onclick="edit(\'' + record.id + '\')">' +
            '编辑' +
            '</button>&nbsp;'
            +'<button class="btn btn-success" onclick="resetPwd(\'' + record.id + '\')">' +
            '重置密码' +
            '</button>&nbsp;'
            +'<button class="btn btn-warning" onclick="showMemberInfo(\'' + record.sitemid + '\')">' +
            '查看代理商信息' +
            '</button>&nbsp;'/*
            +'<button class="btn btn-purple" onclick="addPayTest(\'' + record.id + '\')">' +
            '充值测试' +
            '</button>'*/;
    }
}];

function edit(id) {
    layer.open({
        type: 2,
        title:'代理商用户信息编辑',
        area: ['70%','70%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/agent/authorization/editUI.html?id='+id
    });
}

function showUserInfo(mid) {
    webside.common.loadPage('/member/agent/showUserInfoUi.html?mid='+mid);
}

function showMemberInfo(sitemid) {
	
	var url = sys.rootPath +"/agent/authorization/setAgentLoginUserKey.html";
    $.ajax({
		type : "POST",
		url : url,
		data : {"sitemid":sitemid},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				var burl=msg.businessUrl+"?sitemid="+sitemid+"&sign="+msg.sign+"&currentTime="+msg.currentTime;
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
   /* layer.open({
        type: 2,
        title: '用户信息',
        area: ['90%', '90%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/member/showUserInfoUi.html?mid=' + mid,
        success: function (layero, index) {

        }
    });*/
}

function resetPwd(id) {
    layer.msg('确定要重置密码吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '返回']
        ,yes: function(index){
            layer.close(index);
            $.ajax({
                type: "POST",
                url: sys.rootPath + "/agent/authorization/resetPwd.html",
                data: {
                    id:id
                },
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success == true) {
                        grid.refresh(true);
                        layer.msg('重置密码成功!', {icon: 6});
                    }else {
                        grid.refresh(true);
                        layer.msg('重置密码失败!', {icon: 5});
                    }
                },
                error: function (msg) {
                    layer.msg(msg, {icon: 5});
                }
            });
        }
    });
}

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

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: false,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/agent/authorization/list.html',
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
    document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();//realname, phone
    grid.parameters['mid'] = $("#searchKey").val();
    grid.parameters['realname'] = $("#realname").val();
    grid.parameters['phone'] = $("#phone").val();
    grid.refresh(true);
}


