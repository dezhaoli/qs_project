var dtGridColumns = [{
    id : 'mid',
    title : 'mid',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'gameType',
    title : '游戏类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'money',
    title : '金额',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs'
}, {
    id : 'creatorId',
    title : '创建人ID',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'createTime',
    title : '操作时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype:'string', 
    oformat:'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}, {
    id : 'mid',
    title : '操作',
    type : 'date',
    otype:'string', 
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
            return "<button class='btn btn-pink' style='border-radius: 5px;'" +
                'onclick="deleteInfo('+record.id+')">删除</button>';
    }
}];
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/payWhite/getPayWhiteInfoListUi.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '用户登录信息',
    pageSize : 10,
    pageSizeLimit : [10, 20, 30]
};
var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	debugger;
	if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
	grid.load();
    grid.parameters = new Object();
    grid.parameters['mid'] = $("#searchKey").val();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});

//自定义查询
function customSearch() {
    grid.parameters = new Object();
    grid.parameters['mid'] = $("#searchKey").val();
    grid.refresh(true);
}

function deleteInfo(id){
	 $.ajax({
         type : "POST",
         url : sys.rootPath + "/payWhite/deleteToMid.html",
         data : {
             "id":id
         },
         dataType : "json",
         success : function(resultdata) {
             if (resultdata.success == true) {
                 layer.msg(resultdata.message, {icon: 6});
                 customSearch();
             }else {
                 layer.msg(resultdata.message, {icon: 5});
                 customSearch();
             }
             count = 0;
         }
     });
}

/*
function submitPayWhite() {

	var mid = $("#mid").val();
	var money = $("#money").val();
	var gameType = $("#gameType").val();
	var url=sys.rootPath + "/payWhite/insertSelectiveInfo.html";
	debugger;
	if(mid =="" || mid <=0){
		 layer.msg("mid不能为空！", {icon: 5,time:1000});
		 return;
	}
	if(money =="" || money <=0){
		 layer.msg("金额不能为空！", {icon: 5,time:1000});
		 return;
	}
	if(gameType =="" || gameType <=0){
		 layer.msg("请选择游戏类型！", {icon: 5,time:1000});
		 return;
	}
	 $.ajax({
		    type: "POST",
		    url: url,
		    data:{"mid":mid,
		          "money":money,
		          "gameType":gameType},
		    dataType: "json",
		    success: function (msg) {
		   	 if (msg.success==true){
		   		 layer.msg(msg.message, {icon: 6,time:1000});
		       webside.common.loadPage("/payWhite/getPayWhiteInfoList.html");
		       grid.parameters = new Object();
		       grid.load();
		       grid.refresh(true);
		   	 }else {
		   		 layer.msg(msg.message, {icon: 5,time:1000});
		   	 }
		    }
		    
		});
}*/