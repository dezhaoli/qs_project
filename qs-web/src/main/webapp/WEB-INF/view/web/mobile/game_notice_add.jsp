<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script>
    jeDate({
        dateCell:"#stime",//isinitVal:true,
        format:"YYYY-MM-DD hh:mm",
        isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00"
    });
    jeDate({
        dateCell : '#etime',
        format:"YYYY-MM-DD hh:mm",
        isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00"
    });
</script>
<link rel="stylesheet" href="${ctx }/resources/js/cronGen/cronGen.css"/>
<script type="text/javascript" src="${ctx }/resources/js/cronGen/cronGen.min.js"></script>

<script type="text/javascript">
$("#corn").cronGen({
	direction : 'left' //可选：top|right|bottom|left
});
</script>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
     		<div class="form-group form-horizontal">
		            <label class="control-label col-sm-1 no-padding-right">请选择发布类型</label>
	                <div class="col-sm-2">
	                    	<select class="form-control" name="pushType" id="pushType" onchange="pushTypeChange(this.value);">
	                    		<option value="">请选择...</option>
	                    		<option value="1">发布在线公告</option>
	                    		<option value="3">发布定时公告</option>
	                    	</select>
	                </div>
	                <button id="btn0" type="button" onclick="webside.common.loadPage('/game/notice/mainListUi.html<c:if
			            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
			            class="btn btn-info btn-sm">
			        <i class="fa fa-undo"></i>&nbsp;返回 
			    </button>
	         </div>
	         
	         
	   <div style="display: none" id="div1">  
        <form id="storeForm1" name="storeForm" class="form-horizontal" role="form" method="post">
                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
                <input type="hidden" name="id" id="id" value="${record.id }">
                <input type="hidden" name="pushType" id="pushType" value="1">
	         
	             	<h1 style="border-bottom: solid 2px #438EB9;margin-top: 70px;margin-bottom: 20px">发布在线公告</h1>
	             	<div class="form-group">
			           		<label class="control-label col-sm-1  no-padding-right">内容</label>
			                <div class="col-sm-6">
			                    	<textarea rows="5" cols="230" id="content" name="content" required="required"></textarea>
			                </div>
		            </div>
        <div class="center">
		    <button id="btnAdd" type="button" onclick="javascript:$('#storeForm1').submit();" class="btn btn-success btn-sm">
		        <i class="fa fa-user-plus"></i>&nbsp;
		        <c:if test="${empty record}">
		            添加
		        </c:if>
		        <c:if test="${!empty record}">
		            保存
		        </c:if>
		    </button>
		    <button id="btn" type="button" onclick="webside.common.loadPage('/game/notice/mainListUi.html<c:if
		            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		            class="btn btn-info btn-sm">
		        <i class="fa fa-undo"></i>&nbsp;返回
		    </button>
		</div>
      </form>
     </div>
     
     
     
    <div style="display: none" id="div2">   
        <form id="storeForm2" name="storeForm" class="form-horizontal" role="form" method="post">
	                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
	                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
	                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
	                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
	                <input type="hidden" name="id" id="id" value="${record.id }">
	                <input type="hidden" name="pushType" id="pushType" value="2">
	             
	             	<h1 style="border-bottom: solid 2px #438EB9;margin-top: 70px;margin-bottom: 20px">发布停服公告</h1>
	             	<div class="form-group">
			           		<label class="control-label col-sm-1  no-padding-right">内容</label>
			                <div class="col-sm-6">
			                    	<textarea rows="5" cols="230" id="content"  name="content" required="required"></textarea>
			                </div>
		            </div>
	             
	        <div class="center">
			    <button id="btnAdd" type="button" onclick="javascript:$('#storeForm2').submit();" class="btn btn-success btn-sm">
			        <i class="fa fa-user-plus"></i>&nbsp;
			        <c:if test="${empty record}">
			            添加
			        </c:if>
			        <c:if test="${!empty record}">
			            保存
			        </c:if>
			    </button>
			    <button id="btn" type="button" onclick="webside.common.loadPage('/game/notice/mainListUi.html<c:if
			            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
			            class="btn btn-info btn-sm">
			        <i class="fa fa-undo"></i>&nbsp;返回
			    </button>
			</div>
        </form>
       </div> 
       
       
       
       
       <div style="display: none" id="div3">
        <form id="storeForm3" name="storeForm" class="form-horizontal" role="form" method="post">
		                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
		                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
		                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
		                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
		                <input type="hidden" name="id" id="id" value="${record.id }">
		                <input type="hidden" name="pushType" id="pushType" value="3">
		           		<h1 style="border-bottom: solid 2px #438EB9;margin-top: 70px;margin-bottom: 20px">发布定时公告</h1>
			              <div class="form-group">
				            <label class="control-label col-sm-1 no-padding-right">标题</label>
			                <div class="col-sm-5">
			                    <input class="form-control"  id="title" name="title" type="text" onblur="titleValidate(this.value);"
			                               value="${record.name }" placeholder="标题..."  required="required"/>
			                </div>
			              </div>
			              <div class="form-group">
				            <label class="control-label col-sm-1 no-padding-right">开始日期</label>
			                <div class="col-sm-5">
			                    <input class="form-control"  id="stime" name="stime" type="text"
			                               value="${record.name }" placeholder="开始日期..." readonly="readonly"  required="required"/>
			                </div>
			              </div>
			              <div class="form-group">
				            <label class="control-label col-sm-1 no-padding-right">截止日期</label>
			                <div class="col-sm-5">
			                    <input class="form-control" id="etime" name="etime" type="text"
			                               value="${record.code }" placeholder="结束日期..." readonly="readonly"  required="required"/>
			                </div>
			              </div>
						 <div class="form-group">
				            <label class="control-label col-sm-1 no-padding-right">活动类型</label>
			                <div class="col-sm-5">
			                    	<select class="form-control" id="type" name="type">
			                    	<option value="">请选择...</option>
			                    		<option value="1">官方公告</option>
			                    		<option value="2">游戏活动</option>
			                    		<option value="3">页面活动</option>
			                    	</select>
			                </div>
			         	</div>
			          	  <div class="form-group">
				            <label class="control-label col-sm-1 no-padding-right">间隔时间</label>
			                <div class="col-sm-5">
			                    <input class="form-control" id="corn" name="corn" type="text"
			                               value="${record.corn }" placeholder="间隔时间..." readonly="readonly"  required="required"/>
			                </div>
			              </div>
			              	<div class="form-group">
				           		<label class="control-label col-sm-1  no-padding-right">内容</label>
				                <div class="col-sm-6">
				                    	<textarea rows="5" cols="230"  id="content"  name="content" required="required"></textarea>
				                </div>
			           	 	</div>
		        <div class="center">
				    <button id="btnAdd" type="button" onclick="javascript:$('#storeForm3').submit();" class="btn btn-success btn-sm">
				        <i class="fa fa-user-plus"></i>&nbsp;
				        <c:if test="${empty record}">
				            添加
				        </c:if>
				        <c:if test="${!empty record}">
				            保存
				        </c:if>
				    </button>
				    <button id="btn" type="button" onclick="webside.common.loadPage('/game/notice/mainListUi.html<c:if
				            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
				            class="btn btn-info btn-sm">
				        <i class="fa fa-undo"></i>&nbsp;返回
				    </button>
				</div>
        </form>
       </div>
       
       
       
    </div>
</div>
<div class="hr hr-dotted"></div>


<script type="text/javascript">
    $(function () {
        $('#storeForm1').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	push_type: {
                    required: true
                }
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }, 
            submitHandler: function (form) {
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/notice/gameNoticeAdd.html';
                } else {
                    url = '/game/notice/gameNoticeAdd.html';
                }
                webside.common.commit('storeForm1', url, '/game/notice/mainListUi.html');
            }
        });


    });
    $(function () {
        $('#storeForm2').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	push_type: {
                    required: true
                }
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }, 
            submitHandler: function (form) {
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/notice/gameNoticeAdd.html';
                } else {
                    url = '/game/notice/gameNoticeAdd.html';
                }
                webside.common.commit('storeForm2', url, '/game/notice/mainListUi.html');
            }
        });


    });
    $(function () {
        $('#storeForm3').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
            	push_type: {
                    required: true
                }
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }, 
            submitHandler: function (form) {
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/notice/gameNoticeAdd.html';
                } else {
                    url = '/game/notice/gameNoticeAdd.html';
                }
                webside.common.commit('storeForm3', url, '/game/notice/mainListUi.html');
            }
        });


    });
</script>
<script>
$(function () {
	$.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath }/dict/selectByName.html",
        dataType: "json",
        success: function(data){
                    $('#parent_id').empty();   //清空resText里面的所有内容
                    var html = ''; 
                    $.each(data, function(commentIndex, comment){
                    	console.log(comment);
                         html += '<option value="'+comment.id+'">' + comment.name
                                    + '</option>'; 
                    });
                    console.log(html);
                    $('#parent_id').html(html);
                 }
    });
});
</script>

<script  type="text/javascript">
 function pushTypeChange(_val){
	 if(_val != ""){
		 if(_val == 1){
			 $("#div1").css('display','block');  
			 $("#div2").css('display','none'); 
			 $("#div3").css('display','none'); 
			 $("#btn0").css('display','none'); 
		 }
		 if(_val == 2){
			 $("#div1").css('display','none');  
			 $("#div2").css('display','block'); 
			 $("#div3").css('display','none'); 
			 $("#btn0").css('display','none'); 
		 }
		 if(_val == 3){
			 $("#div1").css('display','none');  
			 $("#div2").css('display','none'); 
			 $("#div3").css('display','block'); 
			 $("#btn0").css('display','none'); 
		 }
	 }
	 if(_val == ""){
		 $("#div1").css('display','none');  
		 $("#div2").css('display','none'); 
		 $("#div3").css('display','none'); 
		 $("#btn0").css('display','block'); 
	 }
 }
</script>


<%--<script>
    $(function () {
        jeDate({
            dateCell: '#stime',
            isinitVal:new Date(),
            isinitVal:true,
            format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01 00:00', //最小日期
            maxDate: '2050-06-01 00:00' //最大日期
        });
        jeDate({
            dateCell: '#etime',
            isinitVal:new Date(),
            isinitVal:true,
            format: 'YYYY-MM-DD hh:mm', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01 00:00', //最小日期
            maxDate: '2050-06-01 00:00' //最大日期
        });
    });
</script>--%>

<script>
function titleValidate(_val){
    $.ajax({
        type : "POST",
        url : sys.rootPath + "/game/notice/gameNoticeByTitle.html",
        data : {"title":_val},
        dataType : "json",
        beforeSend : function() {
            index = layer.load();
        },
        success : function(resultdata) {
            layer.close(index);
            if (resultdata.success) {
                layer.msg("标题重复,请重新输入", {
                    icon : 1
                });
                $("#title").val("");
            } else {
               /*  layer.msg(resultdata.message, {
                    icon : 5
                }); */
            }
        },
        error : function(data, errorMsg) {
            layer.close(index);
            layer.msg(data.responseText, {
                icon : 2
            });
        }
    });
}


</script>


