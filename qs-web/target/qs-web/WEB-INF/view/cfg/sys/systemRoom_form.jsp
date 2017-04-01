<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<div class="page-header">
	<h1>
		<c:if test="${empty record}">
		新增房间配置
		</c:if>
		<c:if test="${!empty record}">
		编辑房间配置
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="systemRoomForm" name="systemRoomForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty record}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="id" value="${record.id }">
		</c:if>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="name">房间名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input class="form-control" name="name" id="name" type="text" 
		           value="${record.name }" placeholder="房间名称..."/>
		      </div>
		      </div>
		   </div>

		   
		    <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">房间开始ID</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="begin" id="begin" type="number" 
		           value="${record.begin }" placeholder="房间开始ID..."/>
		      </div>
		      </div>
		      
		      
		         <label class="control-label col-sm-1 no-padding-right">房间结束ID</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="end" id="end" type="number" 
		           value="${record.end}" placeholder="房间结束ID..."/>
		      </div>
		      </div>
		   </div>
		    
		   
		   	 <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">局数</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="ju" id="ju" type="number" 
		           value="${record.ju}" placeholder="局数..."/>
		      </div>
		      </div>
		      
		      <label class="control-label col-sm-1 no-padding-right">花费金币</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="cost" id="cost" type="number" 
		           value="${record.cost}" placeholder="花费金币..."/>
		      </div>
		      </div>
		      
		   </div>
		   
		   
		   	 <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">类型</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		           	<select class="form-control" id="type" name="type" style="width: 100%">
						<option value="1" <c:if test="${record.type eq '1' }">selected="selected"</c:if>>血流成河/推到胡</option>
						<option value="2" <c:if test="${record.type eq '2' }">selected="selected"</c:if>>血战到底/做牌推到胡</option>
						<option value="3" <c:if test="${record.type eq '3' }">selected="selected"</c:if>>转转麻将</option>
						
					</select>
		      </div>
		      </div>
		      
		            <label class="control-label col-sm-1 no-padding-right">服务器ID</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="serverid" id="serverid" type="number" 
		           value="${record.serverid}" placeholder="服务器ID..."/>
		      </div>
		      </div>
		      
		   </div>
	
		   
		      <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">固定抽水</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		        	<select class="form-control" id="taxesMode" name="taxesMode" style="width: 100%">
						<option value="0" <c:if test="${record.taxesMode eq 0 }">selected="selected"</c:if>>固定抽水</option>
						<option value="1" <c:if test="${record.taxesMode eq 1 }">selected="selected"</c:if>>按比例抽水</option>
					</select>
		      </div>
		      </div>
		      
		          <label class="control-label col-sm-1 no-padding-right">抽水(1/1000)</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="taxation" id="taxation" type="number" 
		           value="${record.taxation}" placeholder="抽水(1/1000)..."/>
		      </div>
		      </div>
		      
		   </div>

		   
		       <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">超时时间(秒)</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="bettime" id="bettime" type="number" 
		           value="${record.bettime}" placeholder="超时时间(秒)..."/>
		      </div>
		      </div>
		      
		           <label class="control-label col-sm-1 no-padding-right">底注</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="sb" id="sb" type="number" 
		           value="${record.sb}" placeholder="底注..."/>
		      </div>
		      </div>
		   </div>

		   
		    <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">最小携带</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="min" id="min" type="number" 
		           value="${record.min}" placeholder="最小携带..."/>
		      </div>
		      </div>
		      
		       <label class="control-label col-sm-1 no-padding-right">最大携带</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="max" id="max" type="number" 
		           value="${record.max}" placeholder="最大携带..."/>
		      </div>
		      </div>
		   </div>
		   
		   	     <div class="form-checkbox">
		      <label class="control-label col-sm-1 no-padding-right">是否打开</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		      <select class="form-control" id="status" name="status" style="width: 100%">
		                <option value="1" <c:if test="${record.status eq 1 }">selected="selected"</c:if>>打开</option>
						<option value="0" <c:if test="${record.status eq 0 }">selected="selected"</c:if>>关闭</option>
			  </select>
		           <%--   <input type="checkbox" name="status" id="status" value="1" <c:if BufferModel="${record.status eq 1 }">checked</c:if>> --%>
		      </div>
		      </div>
		   </div>
		

		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#systemRoomForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty record}">
		添加
		</c:if>
	  	<c:if test="${!empty record}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/cfg/systemRoom/listUI.html<c:if test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script type="text/javascript">
$(function() {
    //webside.form.systemRoom.validatesystemRoomForm();
    
    
    $('#systemRoomForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	name : {
                required : true
            },
            begin : {
                required : true
            },
            end: {
                required : true
            }
            ,
            ju : {
                required : true
            } ,
            cost : {
                required : true
            } ,
            serverid : {
                required : true
            }
            
        },
        messages : {
         
        	name : "房间名称",
        	begin : "房间开始ID",
        	end : "房间结束ID",
        	ju : "局数",
        	cost : "花费金币",
        	serverid : "服务器ID"
        },
        highlight : function(e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success : function(e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
            $(e).remove();
        },
        errorPlacement : function(error, element) {
            error.insertAfter(element.parent());
        },
        submitHandler : function(form) {
        	debugger
        	
            var id = $("#id").val();
            var url = "";
            if (id != undefined) {
                url = '/cfg/systemRoom/edit.html';
            } else {
                url = '/cfg/systemRoom/add.html';
            }
            webside.common.commit('systemRoomForm', url, '/cfg/systemRoom/listUI.html');
        }
    });
    

});
</script>