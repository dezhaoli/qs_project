var dtGridColumns = [{
    id : '',
    title : '编码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return record.name+'-'+record.id;
       }
},{
    id : 'name',
    title : '房间名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'begin',
    title : '房间开始ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'end',
    title : '房间结束ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'ju',
    title : '局数',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'cost',
    title : '花费金币',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'type',
    title : '类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
       	  if(value =='1'){
                 return '<span class="label label-sm label-info arrowed arrowed-righ">血流成河</span>';
             }else  if(value =='2'){
                 return '<span class="label label-sm label-info arrowed arrowed-righ">血战到底</span>';
             }
       }
},{
    id : 'serverid',
    title : '服务器ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
       	  if(value ==1){
                 return '<span class="label label-sm label-info arrowed arrowed-righ">打开 </span>';
             }else{
            	 return '<span class="label label-sm label-error arrowed arrowed-righ">关闭 </span>'; 
             }
       	  
       }
},{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	// return '<span style="cursor: pointer;" onclick="deleteOrder(\''+record.id+'\')">修改</span>';
        return '<a href="#" style="cursor: pointer;" onclick="edit(\''+record.id+'\')">编辑</a>';
    }
}];

function edit(id){
	webside.common.loadPage('/cfg/systemRoom/editUI.html?id='+id)
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
    loadURL : sys.rootPath + '/cfg/systemRoom/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,pdf,txt]',
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


