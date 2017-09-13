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
<title>查看mysqlbinlog</title>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-member-edit">
			<div class="row cl">
				<label class="form-label col-3">ip：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.ip }" placeholder="" id="ip" name="ip" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>

			<div class="row cl">
				<label class="form-label col-3">端口：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.port }" placeholder="" id="port" name="port" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			<div class="row cl">
				<label class="form-label col-3">clientID：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${bean.clientID }" placeholder="" id="clientID" name="clientID" disabled="disabled">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">用户名：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.user }" placeholder="" id="user" name="user" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			<div class="row cl">
				<label class="form-label col-3">密码：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.password }" placeholder="" id="password" name="password" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">schema用户名：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.userSchema }" placeholder="" id="userSchema" name="userSchema" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			<div class="row cl">
				<label class="form-label col-3">schema密码：</label>
				<div class="formControls col-5" style="width:420px">
					<input type="text" class="input-text" value="${bean.passwordSchema }" placeholder="" id="passwordSchema" name="passwordSchema" disabled="disabled">
				</div>
				<!--<div class="col-4"></div>-->
			</div>

			<div class="row cl">
				<label class="form-label col-3">文件-位置：</label>
				<div class="formControls col-5" style="width:420px">
					<table>
						<tbody>
							<tr class="text-c">
								<td><input type="text" class="input-text" value="${bean.filename }" placeholder="文件名称" id="filename" name="filename" disabled="disabled"></td>
								<td>-</td>
								<td><input type="text" class="input-text" value="${bean.position }" placeholder="position" id="position" name="position" disabled="disabled"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--<div class="col-4"></div>-->
			</div>

			<div class="row cl">
				<label class="form-label col-3">过滤规则：</label>
				<div class="formControls col-5" style="width:420px">
					<table>
						<tbody id="filtercontent">
							<c:forEach items="${bean.filters }" var="item" varStatus="status">
								<tr class="text-c">
									<td><input type="text" class="input-text" value="${item.database }" disabled="disabled"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.table }" disabled="disabled"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">分库分表规则：</label>
				<div class="formControls col-8">
					<table>
						<tbody id="shardingcontent">
							<c:forEach items="${bean.shardings }" var="item" varStatus="status">
								<tr class="text-c">
									<td><input type="text" class="input-text" value="${item.databaseRule }" disabled="disabled"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.tableRule }" disabled="disabled"></td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="text" class="input-text" value="${item.database }" disabled="disabled"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.table }" disabled="disabled"></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<div class="col-4"></div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">当前文件-位置：</label>
				<div class="formControls col-5" style="width:420px">
					<table>
						<tbody>
							<tr class="text-c">
								<td><input type="text" class="input-text" value="${current }" placeholder="文件名称" id="filename" name="filename" disabled="disabled"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--<div class="col-4"></div>-->
			</div>
			
			<div class="row cl">
				<label class="form-label col-3">历史位置：</label>
				<div class="formControls col-5" style="width:420px">
					<table>
						<tbody id="filtercontent">
							<c:forEach items="${history }" var="item" varStatus="status">
								<tr class="text-c">
									<td style="width:100px"><input type="text" class="input-text"  value="${item.key }" disabled="disabled"></td>
									<td>-</td>
									<td><input type="text" class="input-text" value="${item.value }" disabled="disabled"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!--<div class="col-4"></div>-->
			</div>

		</form>
	</div>
	<script type="text/javascript" src="${basePath }common/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }common/lib/jquery.form.js"></script>
	<script type="text/javascript" src="${basePath }common/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${basePath }common/js/H-ui.js"></script>
	<script type="text/javascript" src="${basePath }common/js/H-ui.admin.js"></script>
</body>
</html>