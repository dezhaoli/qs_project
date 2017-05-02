<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="container">
    <div class="page-title">
        <h4>添加金币</h4>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">温馨提示：</div>
        <div class="panel-body">
            1、每个ID每天最多添加1000金币。<br />
            2、每天添加金币的上限为10000金币。
        </div>
    </div>
    <form id="storeForm" class="gold-form" name="add_gold_form">
        <div class="form-group">
            <label class="control-label" for="userId">请输入玩家ID</label>
            <input class="form-control" id="mid" type="text" name="mid" placeholder="请输入玩家ID" autocomplete="off">
        </div>
        <div class="form-group">
            <label class="control-label" for="goldNum">请输入金币数</label>
            <input class="form-control" id="gold" type="text" name="gold" placeholder="请输入金币数，不能超过1000金币" autocomplete="off">
        </div>
        <div class="form-group">
            <label class="control-label" for="goldReason">添加原因</label>
            <textarea class="form-control" name="remark" id="remark" rows="3" placeholder="添加原因" autocomplete="off"></textarea>
        </div>
        <div class="form-group">
            <input class="btn btn-primary" onclick="javascript:$('#storeForm').submit();"  id="addGoldBtn" type="button" value="添加">
        </div>
    </form>
</div>
<script>
$(function () {
	var submitCount = 1;
	
    $('#storeForm').validate({
        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        ignore: "",
        rules: {
        	mid: {
                required: true,
            },
            gold: {
                required: true,
            },
            remark: {
                required: true,
            }
        },
        messages: {
        	mid: "请输入玩家ID!",
        	gold: "请输入金币数！",
        	remark: "请输入添加原因！"
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
        	if (submitCount ==1){
        		submitCount++;
	        	var data = $("#storeForm").serialize();
	            var url = '/business/user/goldAddSubmit.html';
	        	$.ajax({
	                type : "POST",
	                url : sys.rootPath + url,
	                data : data,
	                dataType : "json",
	                success : function(resultdata) {
	                    if (resultdata.success) {
	                        layer.msg(resultdata.message, {
	                            icon : 1
	                        });
	                        setTimeout(function (){
	                        webside.index.initHomePage();
	                        },800);
	                    } else {
	                        layer.msg(resultdata.message, {
	                            icon : 5
	                        });
	                    }
	                }
	            });
        	}else {
                layer.msg("请勿多次提交！！！", {icon: 5});
        	}

           // webside.common.commit('storeForm', url, '/home.html');
        }
    });

});
</script>