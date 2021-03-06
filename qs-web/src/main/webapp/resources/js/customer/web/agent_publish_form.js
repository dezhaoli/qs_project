var dtGridColumns = [{
    id : 'id',
    title : '序号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {

        return dataNo+1;
    }
},{
    id : 'name',
    title : '公告名称',
    type : 'String',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'imgName',
    title : '公告内容',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
    	if (record.type==0){
    		return record.body;
    	}else {
    		return "<img style='width: 30px; height: 40px' alt='' src='"+value+"'>";
    	}
    }
},{
    id : 'stime',
    title : '公告开始时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
},{
    id : 'etime',
    title : '公告结束时间',
    type : 'etime',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
},{
    id : 'mktime',
    title : '公告创建时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
},{
    id : 'id',
    title : '操作',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return '<button class="btn btn-primary" onclick="deletePublish(\'' + record.id + '\')">删除</button>';
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
    loadURL : sys.rootPath + '/publish/agentPublishList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '代理商公告列表',
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
    grid.refresh(true);
}
//公告删除
function deletePublish(id){
	var url="/publish/delete.html";
	$.ajax({
        type : "POST",
        url : sys.rootPath + url,
        data : {
            "id" : id
        },
        dataType : "json",
        success : function(resultdata) {
            if (resultdata.success) {
                layer.msg(resultdata.message, {
                    icon : 1
                });
                customSearch();
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        }
    });
}

