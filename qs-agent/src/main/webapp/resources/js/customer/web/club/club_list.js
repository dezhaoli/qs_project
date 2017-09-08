var dtGridColumns = [{
    id : 'mid',
    title : '会员ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	var result=value;
    	if (record.invite !=record.clubid ){
    		result+="<span style='color:red'>(非代理)</span>"
    	}
    	
        return result ;
    }
},{
    id : 'name',
    title : '会员昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
}/*,{
    id : 'gold',
    title : '房卡',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
}*/,{
    id : 'openroom',
    title : '代开房',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
    	var but="";
    	if (value==0){
    		but='<button class="btn btn-danger table-btn agent_but" style="width: 56px;  margin-left: -5px;" onclick="openRoom(' + record.mid + ',0)">取消</button>'
    	}else {
    		but='<button class="btn btn-info table-btn agent_but" style="width: 56px;" onclick="openRoom(' + record.mid + ',1)">代开房</button> '
    	}
    	return but;
    }

},{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
    	return '<button class="btn btn-info table-btn agent_but" style="width: 56px;" onclick="deleteGlubMumber(\'' + record.mid + '\',1)">移出</button> '
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
    loadURL : sys.rootPath + '/agentClub/getClubMemberInfo.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '我的俱乐部',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	
	if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
		grid.sortParameter.columnId = $("#orderByColumn").val();
		grid.sortParameter.sortType = $("#orderByType").val();
	}
	  grid.parameters = new Object();
	   grid.parameters['mid'] =$("#searchMid").val();
    grid.load();
   // $("#type").click(customSearch);
    $("#btnSearch").click(customSearch);
    //$("#types").click(customSearch);
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
    grid.parameters = new Object();
    grid.parameters['mid'] = $('#searchMid').val();

    grid.refresh(true);
}

