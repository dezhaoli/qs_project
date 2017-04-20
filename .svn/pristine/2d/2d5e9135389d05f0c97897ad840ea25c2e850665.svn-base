<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
            <c:if test="${!empty record}">
                <input type="hidden" name="id" id="id" value="${record.id }">
                <input type="hidden" name="status" id="status" value="${record.status }">
                <input type="hidden" name="createBy" id="createBy" value="${record.createBy}">
<%--                 <input  name="createDate" id="createDate" value="${record.createDate}">
 --%>                <input type="hidden" name="ee" id="parent_id2" value="${record.parentId }">
	                <input type="hidden" name="remarks2" id="remarks2" value="${record.remarks }">
            </c:if>
              <div class="form-group">
	             	<label class="control-label col-sm-1 no-padding-right">父级ID</label>
	                <div class="col-sm-5">
	                    <div class="clearfix">
	                        <select class="form-control" id="parent_id" name="parentId" style="width: 100%">
	                           
	                        </select>
	                    </div>
	                </div>
	                 <label class="control-label col-sm-1 no-padding-right">名称</label>
                <div class="col-sm-5">
                    <input class="form-control" name="name" id="name" type="text"
                               value="${record.name }" placeholder="名称..."/>
                </div>
                </div>

            <div class="form-group">
            	<label class="control-label col-sm-1 no-padding-right">编码</label>
                <div class="col-sm-5">
                    <input class="form-control" name="code" id="code" type="text"
                               value="${record.code }" placeholder="编码..."/>
                </div>
               
                 <label class="control-label col-sm-1 no-padding-right">排序（升序）</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="sort" id="sort" type="text"
                               value="${record.sort}" placeholder="排序..."/>
                    </div>
                </div>
               
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">描述</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="description" id="description" type="text"
                               value="${record.description }" placeholder="描述..."/>
                    </div>
                </div>
               
                 <label class="control-label col-sm-1 no-padding-right">扩展编号</label>
	                <div class="col-sm-5">
	                    <input class="form-control" name="extCode" id="extCode" type="text"
	                               value="${record.extCode }" placeholder="扩展编号..."/>
	                </div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">扩展字段1</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="extend1" id="extend1" type="text"
                               value="${record.extend1 }" placeholder="描述..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">扩展字段2</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="extend2" id="extend2" type="text"
                               value="${record.extend2 }" placeholder="名称..."/>
                    </div>
                </div>
            </div>
             <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">备注信息</label>
                <div class="col-sm-10">
                    	<textarea class="form-control" rows="6" cols="250" name="remarks" id="remarks" >
                    	</textarea>
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
		    <button id="btn" type="button" onclick="webside.common.loadPage('/dict/dictList.html<c:if
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
                    url = '/dict/edit.html';
                } else {
                    url = '/dict/add.html';
                }
                webside.common.commit('storeForm', url, '/dict/dictList.html');
            }
        });


    });
</script>

<script>
$(function() {
	//备注值
	$('#remarks').html($("#remarks2").val());
	$.ajax({
	    type: "GET",
	    url: "${pageContext.request.contextPath }/dict/selectByName.html",
	    dataType: "json",
	    success: function(data){
	                $('#parent_id').empty();   //清空resText里面的所有内容
	                var a = $('#parent_id2').val();
	                var html = '<option value="" >请选择...</option>'; 
	                $.each(data, function(commentIndex, comment){
	                	if(a==comment.id){
	                		html += '<option value="'+comment.id+'" selected="selected">' + comment.name
	                        + '</option>'; 
	                	}else{
	                		html += '<option value="'+comment.id+'">' + comment.name
	                        + '</option>'; 
	                	}
	                     
	                });
	                $('#parent_id').html(html);
	             }
	});

});

</script>