<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<div class="page-header">
	<h1>
		<c:if test="${empty record}">
		新增商城配置
		</c:if>
		<c:if test="${!empty record}">
		编辑商城配置
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
		<c:if test="${!empty record}">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="id" id="id" value="${record.id }">
		</c:if>
        <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">金额(元)</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="money" id="money" type="number" 
		           value="${record.money }" placeholder="金额..."/>
		      </div>
		      </div>
		      
		      <label class="control-label col-sm-1 no-padding-right">金币</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="form-control" name="gold" id="gold" type="number" 
		           value="${record.gold }" placeholder="金币..."/>
		      </div>
		      </div>
		      
		   </div>

		    <div class="form-group">
	               <label class="control-label col-sm-1 no-padding-right">赠送金币</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		         <input class="form-control" name="song" id="song" type="number" 
		           value="${record.song}" placeholder="赠送金币..."/>
		      </div>
		      </div>
		      
		             <label class="control-label col-sm-1 no-padding-right">图片路径</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		                  <input class="form-control" name="img" id="img" type="text" 
		           value="${record.img}" placeholder="图片路径..."/>
		      </div>
		      </div>
		   </div>

		   
		   	 <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right">活动开始时间</label>
		      <div class="col-sm-5">
		      <div class="clearfix">
		       <input class="datainp" class="form-control"  name="starttimeStr" id="starttimeStr" type="text"
				          value="${record.starttimeStr}" placeholder="活动开始时间..."  />
		      </div>
		      </div>
		      
		      <label class="control-label col-sm-1 no-padding-right">活动结束时间</label>
		      <div class="col-sm-4">
		      <div class="clearfix">
		         <input class="datainp" class="form-control" name="endtimeStr" id="endtimeStr" type="text" 
		           value="${record.endtimeStr}" placeholder="活动结束时间..." />
		      </div>
		      </div>
		      
		   </div>

		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-success btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;
	  	<c:if test="${empty record}">
		添加
		</c:if>
	  	<c:if test="${!empty record}">
		保存
		</c:if>
	</button>
		<button id="btn" type="button" onclick="webside.common.loadPage('/cfg/store/listUI.html<c:if test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>

<script type="text/javascript">
$(function() {

	
	  jeDate({
		    dateCell: '#starttimeStr',
			isinitVal:false,
		    format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月 
		    minDate: '1900-06-01', //最小日期
		    maxDate: '2050-06-01' //最大日期
		});
		
		 jeDate({
		    dateCell: '#endtimeStr',
			isinitVal:false,
		    format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月 
		    minDate: '1900-06-01', //最小日期
		    maxDate: '2050-06-01' //最大日期
		});

    
    
    $('#storeForm').validate({
        errorElement : 'div',
        errorClass : 'help-block',
        focusInvalid : false,
        ignore : "",
        rules : {
        	money : {
                required : true
            },
            gold : {
                required : true
            },
            song: {
                required : true
            }
            ,
            img : {
                required : true
            } ,
            starttimeStr : {
                required : true
            } ,
            endtimeStr : {
                required : true
            }
            
        },
        messages : {
         
        	money : "金额",
        	gold : "金币",
        	song : "赠送金币",
        	img : "图片路径",
        	starttimeStr : "活动开始时间",
        	endtimeStr : "活动结束时间",
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
                url = '/cfg/store/edit.html';
            } else {
                url = '/cfg/store/add.html';
            }
            webside.common.commit('storeForm', url, '/cfg/store/listUI.html');
        }
    });
    

});
</script>