<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<%-- <script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_simplePay_list.js"></script> --%>
<style>
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
</style>
<div class="page-content">
    <div class="row">
        <%-- <div class="col-sm-4">
            <div class="well" style="padding: 5px;">
                <h4 class="blue smaller lighter" style="font-size:18px;margin-bottom: 2px;">
                    上周结算: ${lastMonday} ~ ${lastSunday}
                </h4>
            </div>
        </div> --%>

<%--         <input type="hidden" id="lastMonday" value="${lastMonday}"/>
        <input type="hidden" id="lastSunday" value="${lastSunday}"/>
        <input type="hidden" id="gameType" value="${gameType}"/> --%>

        <div class="col-sm-2">
            <select class="form-control" name="gameType" id="gameType">
								<option value="runfast">牵手跑得快/跑胡子</option>
								<option value="bull">疯狂斗牛OL</option>
								<option value="majiang">牵手湖南麻将</option>
								<option value="9">牵手木虱</option>
								<option value="5">金溪麻将</option>
			</select>
        </div>
        <div class="col-sm-2">
            <div class="input-group" style="width: 100%;">
                <input type="number" class="input form-control" name="mid" id="mid" placeholder="请输入MID"/>
                <span class="input-group-btn">
                <button id="btnSearch" class="btn btn-primary btn-sm" type="button" onclick="btnSearch();">
                    <i class="fa fa-search"></i>查询
                </button>
            </span>
            </div>
        </div>

        
        <div class="col-sm-2">

        </div>
    </div>

    <div class="page-content col-sm-10" id="footBody" style="margin-top:25px;background: #f5f5f5;display: none;">
    <table class="table">
			<tbody>
				<tr>
				   <td>mid</td>
				   <td class="amid"></td>
				 </tr>
				 <tr>
				   <td>微信openid</td>
				    <td class="openid"></td>
				 </tr>
				 <tr>
				     <td>真实姓名</td>
				     <td class="name"></td>
				  </tr>
				  <tr>
				       <td>联系电话</td>
				       <td class="phone"></td>
				   </tr>
				   <tr>
				       <td>支付金额</td>
				       <td class="playtimes">
				       <div class="col-sm-4">
				       <input type="number" class="input form-control" name="rebatetotal" id="rebatetotal" placeholder="请输入支付金额……"/>
				       </div>
				       </td>
				    </tr>
				    
				    <tr>
				       <td>备注</td>
				       <td class="playtimes">
				       <div class="col-sm-4">
				          <input type="text" class="input form-control" name="msg" id="msg" placeholder="备注……"/>
				       </div>
				       </td>
				    </tr>
				    
				    <tr>
				       <td colspan="2">
				       <div class="col-sm-10 center" >
					       <button id="submit" class="btn btn-success btn-sm" type="button" onclick="submit();">
			                  	  确认支付
			                </button>
				       </div>
		                </td>
				    </tr>
				</tbody>
          </table>
    </div>
</div>

<script>

   //根据MID查用户信息
    function btnSearch(){
	   
    	var gameType= $("#gameType :selected").val();
    	var mid=$("#mid").val();
    	var url='${ctx}/corpPay/getAgentByMid.html';
    	
    	$.ajax({
             type : "POST",
             url :url,
             data : {
                 "gameType":gameType,
                 "mid":mid
             },
             dataType : "json",
             success : function(resultdata) {
                 if (resultdata.success == true) {
                	 
                	 $(".amid").text(resultdata.data.mid);
                	 $(".openid").text(resultdata.data.openid);
                	 $(".name").text(resultdata.data.realname);
                	 $(".phone").text(resultdata.data.phone);
                	 $("#footBody").show();
                     //layer.msg(resultdata.message, {icon: 6});
                 }else {
                     layer.msg(resultdata.message, {icon: 5});
                 }
             }
         });
    }
   
  //确认支付
  var count = 0;
     function submit(){
    	var gameType= $("#gameType :selected").val();
    	var mid=$(".amid").text();
   	    var openid=$(".openid").text();
   	    var money=$("#rebatetotal").val();
   	    var msg=$("#msg").val();
   	    console.log(msg);
    	var url='${ctx}/corpPay/saveSimplePay.html';

        if (count == 2) {
             layer.msg("正在支付中，请耐心等待！请勿重复点击按钮，防止重复支付！！！", {icon: 5});
             return;
        }
    	if (count != 0) {
    	        return;
		}
    	    count = 1;
		if (mid == null || mid == undefined || mid == '') {
			count = 0;
			layer.msg('mid为空，无法支付！',{time:800});
			return;
		}
		if (openid == null || openid == undefined || openid == '') {
			count = 0;
			layer.msg('openid为空，无法支付！',{time:800});
			return;
		}
		if (money == null || money == undefined || money == '') {
			count = 0;
			layer.msg('请输入金额，无法支付！',{time:800});
			return;
		} 
		
		if (money<1 || money >1000) {
			count = 0;
			layer.msg('请输入金额为1到1000元！',{time:800});
			return;
		}

         layer.open({
             content: '确认要支付吗？'
             ,btn: ['确定', '取消']
             ,yes: function(index, layero){
                 if (count != 1) {
                     layer.msg("正在支付中，请耐心等待！请勿重复点击确定按钮，防止重复支付！！！", {icon: 5});
                     return;
                 }
                 count = 2;

                 $("#submit").prop('disabled', true).html('正在支付中...');

                 $.ajax({
                     type : "POST",
                     url : url,
                     data : {
                         "mid" : mid,
                         "money" : money,
                         "openid" : openid,
                         "gameType" :gameType,
                         "msg" : msg
                     },
                     dataType : "json",
                     success : function(resultdata) {
                         if (resultdata.success == true) {
                             count = 0;
                             $("#footBody").hide();
                             layer.msg(resultdata.message, {icon: 6});
                         } else {
                             count = 0;
                             layer.msg(resultdata.message, {icon: 5});
                         }
                         $("#submit").prop('disabled', false).html('确认支付');
                     }
                 });
             }
             ,btn2: function(index, layero){
                 if (count == 2) {
                     return;
                 }
                 $("#submit").prop('disabled', false).html('确认支付');
                 count = 0;
             }
             ,cancel: function(){
                 //右上角关闭回调
                 if (count == 2) {
                     return;
                 }
                 $("#submit").prop('disabled', false).html('确认支付');
                 count = 0;
             }
         });

	}
</script>