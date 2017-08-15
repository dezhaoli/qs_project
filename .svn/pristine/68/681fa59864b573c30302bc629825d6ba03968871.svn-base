var dtGridColumns = [{
    id : 'mid',
    title : '用户ID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
},{
    id : 'name',
    title : '用户昵称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'kaifang',
    title : '开房数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
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
    loadURL : sys.rootPath + '/vip/openRoomSumList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '直属会员开房数查询',
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
	  grid.parameters['querymid'] =$("#mid").val();
	  grid.parameters['sDate'] = $('#startTime').val();
	  grid.parameters['eDate'] = $('#endTime').val();
      grid.load();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };
    /*$("#oprenRoom").text('${resultParam.oprenRoom }');
    $("#countGold").text('${resultParam.countGold }');*/

});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['querymid'] = $('#mid').val();
    grid.parameters['sDate'] = $('#startTime').val();
	grid.parameters['eDate'] = $('#endTime').val();
    grid.refresh(true);
   getParam();
    /*$("#oprenRoom").text('${resultParam.oprenRoom }');
    $("#countGold").text('${resultParam.countGold }');*/
}

