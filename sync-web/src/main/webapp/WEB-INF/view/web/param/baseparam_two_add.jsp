<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
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
	             	
	            <label class="control-label col-sm-1 no-padding-right">名称</label>
                <div class="col-sm-5">
                    <input class="form-control" name="name" id="name" type="text"
                               value="${record.name }" placeholder="名称..."/>
                </div>
                
                <label class="control-label col-sm-1 no-padding-right">编码</label>
                <div class="col-sm-5">
                    <input class="form-control" name="code" id="code" type="text"
                               value="${record.code }" placeholder="编码..."/>
                </div>
	                 	
                </div>

            <div class="form-group">
            	<label class="control-label col-sm-1 no-padding-right">值</label>
                <div class="col-sm-5">
                    <input class="form-control" name="value" id="value" type="text"
                               value="${record.value }" placeholder="数据值..."/>
                </div>
                
               <label class="control-label col-sm-1 no-padding-right">排序（升序）</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="sort" id="sort" type="text"
                               value="${record.sort}" placeholder="排序..."/>
                    </div>
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
		    <button id="btn" type="button" onclick="webside.common.loadPage('/baseparam/baseparamTwo.html<c:if
		            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		            class="btn btn-info btn-sm">
		        <i class="fa fa-undo"></i>&nbsp;返回
		    </button>
		</div>
	        

        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>


<script type="text/javascript">
    $(function () {
        $('#storeForm').validate({/* 
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                type: {
                    required: true
                },
                gameType: {
                    required: true,
                    number:true
                },
                gameShieId: {
                    required: true
                }
            },
            messages: {
                name: "名称",
                type: "类型",
                gameShieId: "游戏盾值",
                gameType:{
                	required:"对应Game值",
                	number:"请输入数字"
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
            }, */
            submitHandler: function (form) {
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/baseparam/add.html';
                } else {
                    url = '/baseparam/add.html';
                }
                webside.common.commit('storeForm', url, '/baseparam/baseparamTwo.html');
            }
        });


    });
</script>
<script>
$(function () {
	/* initManageCombox();
	function initManageCombox(){
		$("#parent_id").ajaxComboBox('${pageContext.request.contextPath }/dict/selectByName.html',{
	    	lang: 'cn',
	      	plugin_type: 'simple',
	      	db_table: 'name',	
	      	per_page: 10,
	      	navi_num: 3,
	      	primary_key:'id',
	      	search_field:'text,id',
	      	show_field:"id",
	      	field:"text",
	      	select_only:true
	  	});
	} */
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