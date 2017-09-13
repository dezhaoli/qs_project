<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">
<html>
<head>
<meta charset="utf-8">
<link href="${basePath }common/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${basePath }common/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${basePath }common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<title>修改mysqlbinlog</title>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-member-edit">
			<input type="hidden"  value="${bean.ip }"  name="ip" >
			<input type="hidden" value="${bean.port }" name="port">
			<%-- <div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>ip：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.ip }" placeholder="" id="ip" name="ip" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>端口：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.port }" placeholder="" id="port" name="port" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>用户名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.user }" placeholder="" id="user" name="user" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>密码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.password }" placeholder="" id="password" name="password" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>schema用户名：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.userSchema }" placeholder="" id="userSchema" name="userSchema" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>schema密码：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.passwordSchema }" placeholder="" id="passwordSchema" name="passwordSchema" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div> --%>

			<div class="row cl">
				<label class="form-label col-3">文件-位置(如果不填取最近的)：</label>
				<div class="formControls col-5">
					<table>
						<tbody>
							<tr class="text-c">
								<td><input type="text" class="input-text" value="${bean.filename }" placeholder="文件名称" id="filename" name="filename"></td>
								<td>-</td>
								<td><input type="text" class="input-text" value="${bean.position }" placeholder="position" id="position" name="position"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-3">过滤规则：<a href="javascript:void(0);" class="label label-success radius" id="editFilterButton"><i class="Hui-iconfont">&#xe600;</i> 添加</a></label>
				<div class="formControls col-5">
					<table>
						<tbody id="filtercontent">
							<c:if test="${ empty bean.filters }">
								<c:set var="filterIndexVar" value="0"></c:set>
							</c:if>
							<c:forEach items="${bean.filters }" var="item" varStatus="status">
								<tr class="text-c">
									<td><input type="text" class="input-text" value="${item.database }" placeholder="database" name="filters[${status.index }].database"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.table }" placeholder="table" name="filters[${status.index }].table"></td>
									<td><span onclick="deleteFilter(this)" class="Validform_wrong"></span></td>
								</tr>
								<c:set var="filterIndexVar" value="${status.index + 1 }"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">分库分表规则：<a href="javascript:void(0);" class="label label-success radius" id="addShardingButton"><i class="Hui-iconfont">&#xe600;</i> 添加</a></label>
				<div class="formControls col-8">
					<table>
						<tbody id="shardingcontent">
							<c:if test="${ empty bean.shardings }">
								<c:set var="shardingIndexVar" value="0"></c:set>
							</c:if>
							<c:forEach items="${bean.shardings }" var="item" varStatus="status">
								<tr class="text-c">
									<td><input type="text" class="input-text" value="${item.databaseRule }" placeholder="分库规则(正则)" name="shardings[${status.index }].databaseRule"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.tableRule }" placeholder="分表规则(正则)" name="shardings[${status.index }].tableRule"></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="text" class="input-text" value="${item.database }" placeholder="真实库名" name="shardings[${status.index }].database"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.table }" placeholder="真实表名" name="shardings[${status.index }].table"></td>
									<c:set var="shardingIndexVar" value="${status.index + 1 }"></c:set>
									<td><span onclick="deleteSharding(this)" class="Validform_wrong"></span></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<div class="col-4"></div>
			</div>

			<div class="row cl">
				<div class="col-9 col-offset-3">
					<input class="btn btn-primary radius" id="editButton" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${basePath }common/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }common/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${basePath }common/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${basePath }common/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${basePath }common/js/H-ui.js"></script>
	<script type="text/javascript" src="${basePath }common/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		var filterIndex = 0;
		var shardingIndex = 0;
		$(function() {
			$("#form-member-edit").Validform({
				btnSubmit : '#editButton',
				tiptype : 2,
				beforeSubmit : function() {
					editMysqlbinlog();
					return false;
				}
			});

			$("#editFilterButton")
					.click(
							function() {
								if(filterIndex == 0) {
									filterIndex = ${filterIndexVar};
								}
								filterIndex = filterIndex + 1;
								$("#filtercontent")
										.append('<tr class="text-c"><td><input type="text" class="input-text" value="" placeholder="database" name="filters['+filterIndex+'].database"></td><td>-</td><td><input type="text" class="input-text" value="" placeholder="table" name="filters['+filterIndex+'].table"></td><td><span onclick="deleteFilter(this)" class="Validform_wrong"></span></td></tr>');
							});
			$("#addShardingButton").click(function() {
				if(shardingIndex == 0) {
					shardingIndex = ${shardingIndexVar};
				}
				shardingIndex = shardingIndex + 1;
				$("#shardingcontent").append('<tr class="text-c"><td><input type="text" class="input-text" value="" placeholder="分库规则(正则)" name="shardings['+shardingIndex+'].databaseRule"></td><td>-</td><td><input type="text" class="input-text" value="" placeholder="分表规则(正则)" name="shardings['+shardingIndex+'].tableRule"></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="text" class="input-text" value="" placeholder="真实库名" name="shardings['+shardingIndex+'].database"></td><td>-</td><td><input type="text" class="input-text" value="" placeholder="真实表名" name="shardings['+shardingIndex+'].table"></td><td><span onclick="deleteFilter(this)" class="Validform_wrong"></span></td></tr>');
			});
		});

		function deleteFilter(e) {
			$(e).parent().parent().remove();
		}
		function deleteSharding(e) {
			$(e).parent().parent().remove();
		}
		function editMysqlbinlog() {
			var filename = $("#filename").val();
			var position = $("#position").val();
			if((filename == '' && position != '')||(filename != '' && position == '')) {
				layer.alert("文件名和位置不能只填一列");
				return ;
			}
			var flag = false;
			$("#filtercontent tr").each(function(i, e){
				var $input = $(e).find("input");
				var db = $input.first().val();
				var tb = $input.last().val();
				if((db == '' && tb != '')||(db != '' && tb == '')) {
					flag = true;
					return;
				}
			});
			if(flag == true) {
				layer.alert("过滤规则不能只填一列");
				return ;
			}
			$('#form-member-edit').ajaxSubmit({
				url : '${basePath }mysqlbinlog/edit_submit.do',
				dataType : 'json',
				success : function(data) {
					if (data.code == 1) {
						parent.layer.msg('修改成功!', {
							icon : 1,
							time : 1000
						});
						var index = parent.layer.getFrameIndex(window.name);
						parent.location.href = '${basePath }mysqlbinlog/list.do';
						parent.layer.close(index);
					} else {
						layer.alert(data.reason);
					}
				}
			});
		}
	</script>
</body>
</html>