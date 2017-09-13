<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/_head.jsp" %>
<html>
<head>
<meta charset="utf-8">
<link href="${basePath }common/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${basePath }common/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${basePath }common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>mysqlbinlog管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> mysqlbinlog管理 <span class="c-gray en">&gt;</span> mysqlbinlog列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="mysqlbinlog_add('添加mysqlbinlog','${basePath }mysqlbinlog/add.do','','520')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加mysqlbinlog</a>
		</span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="5%">序号</th>
				<th width="40%">ip:端口</th>
				<th width="40%">正在运行</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="mysqlbinlog" varStatus="status">
				<tr class="text-c">
					<td>${status.index + 1 }</td>
					<td>${mysqlbinlog.ip }</td>
					<td>${mysqlbinlog.running }</td>
					<td class="td-manage">
						<a title="详情" href="javascript:;" onclick="mysqlbinlog_show('查看详情','${basePath }mysqlbinlog/showInfo.do?ipAndPort=${mysqlbinlog.ip }','','520')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe665;</i></a>
						<a title="修改" href="javascript:;" onclick="mysqlbinlog_edit('修改mysqlbinlog','${basePath }mysqlbinlog/edit.do?ipAndPort=${mysqlbinlog.ip }','','520')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
						<a title="删除" href="javascript:;" onclick="mysqlbinlog_del(this,'${mysqlbinlog.ip }')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
<script type="text/javascript" src="${basePath }common/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${basePath }common/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${basePath }common/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${basePath }common/js/H-ui.js"></script> 
<script type="text/javascript" src="${basePath }common/js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*详情*/
function mysqlbinlog_show(title,url,w,h) {
	layer_show(title,url,w,h);
}
/*mysqlbinlog-添加*/
function mysqlbinlog_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*mysqlbinlo-修改*/
function mysqlbinlog_edit(title,url,w,h){
	layer_show(title,url,w,h);	
}
/*mysqlbinlog-删除*/
function mysqlbinlog_del(obj,ipAndPort){
	layer.confirm('删除后停止采集, 确认要删除吗？',function(index){
		$.ajax({
			type:'POST',
			url:'${basePath }mysqlbinlog/delete.do',
			dataType:'json',
			data:{ipAndPort:ipAndPort},
			success:function(data) {
				if(data.code == 1) {
					$(obj).parents("tr").remove();
					//layer.alert('当前文件位置:'+data.position);
					layer.msg('已删除!',{icon:1,time:2000});
				} else {
					layer.alert(data.reason);
				}
			}
		});
	});
}
</script> 
</body>
</html>