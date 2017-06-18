<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${ctx }/resources/js/jstree/themes/default/style.css"/>
<script type="text/javascript" src="${ctx }/resources/js/jstree/jstree.min.js"></script>   
<script type="text/javascript" src="${ctx }/resources/js/jstree/jstree.checkbox.js"></script> 
<script type="text/javascript" src="${ctx }/resources/js/underscore/underscore-min.js"></script> 
<script type="text/javascript">
$(function() {
        $('#leaderTree').jstree({
            "core" : {
                'data' : {
                    "url" : sys.rootPath + "/group/resourceLeaderTree.html?groupId=" + $("#groupId").val(),
                    "dataType" : "json"
                },
                "themes" : {
                    "responsive" : true
                },
                "multiple" : true,
                "animation" : 200,
                "dblclick_toggle" : true,
                "expand_selected_onload" : true
            },
            "checkbox" : {
                "keep_selected_style" : true,
                "three_state" : false,
                "cascade" : "up"
            },
            "plugins" : ["checkbox"]
        });
});
</script>  
<div class="page-header">
	<h1>
		分公司责任人设置-<small>[${groupEntity.userGroupName }]</small>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="permissionForm" name="permissionForm" class="form-horizontal" role="form" method="post">
		<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
		<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
		<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
		<input type="hidden" id="groupId" name="groupId" value="${groupEntity.id }">
		   <!-- 资源树 -->
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="description">商务数据列表</label>
		      <div class="col-sm-10">
		         <div id="leaderTree"></div>
		      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="commitLeader('/group/leaderPermission.html' , '/group/toGroupListUi.html')" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
		保存
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/group/toGroupListUi.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')" class="btn btn-info btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;返回
	</button>
</div>


<script>
var ids = [];
commitLeader = function(commitUrl, listUrl) {
	debugger;
    var tree = $('#leaderTree').jstree();
    //获取所有选中节点id
    var selectedIds = tree.get_checked();
    //获取所有选中节点
    var selected = tree.get_checked(true);
    //遍历节点，获取选中节点的所有父节点
    for (var i = 0; i < selected.length; i++) {
    	getBusinessParents(tree, selected[i]);
    }
    var index;
    $.ajax({
        type : "POST",
        url : sys.rootPath + commitUrl,
        data : {
            "groupId" : $("#groupId").val(),
            "resourceIds" : _.union(ids, selectedIds).join(',')
        },
        dataType : "json",
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
            layer.close(index);
            if (resultdata.success) {
                layer.msg(resultdata.message, {
                    icon : 1
                });
                webside.common.loadPage(listUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val());
            } else {
                layer.msg(resultdata.message, {
                    icon : 5
                });
            }
        },
        error : function(errorMsg) {
            layer.close(index);
            layer.msg('服务器未响应,请稍后再试', {
                icon : 2
            });
        }
    });
}

getBusinessParents = function(treeObj, nodeObj) {
    var parentId = treeObj.get_parent(nodeObj);
    if (parentId != "#") {
       ids.push(parentId);
       getBusinessParents(treeObj, treeObj.get_node(parentId));
    }
}

</script>