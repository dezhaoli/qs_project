<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<style>
 body { 
 	background: #FFF; 
 } 
input::-webkit-inner-spin-button {
-webkit-appearance: none;
}
input::-webkit-outer-spin-button {
-webkit-appearance: none;
}
.withdImput{width: 40px}
</style>
                <div class="widget-header">
                    <h4 class="widget-title lighter">俱乐部房间房间扣费编辑</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="table-responsive">
                <form id="storeForm"  method="post">
					<table class="table">
			               <thead>
				            <tr>
				                <th>牌局数\扣费类型</th>
				                <th>代付费</th>
				                <th>自费</th>
				                <th>大赢家</th>
				            </tr>
				            </thead>
				            <tbody>
				            <tr>
				                <td>${roomLow }局</td>
				                <td><input type="number" class="withdImput" name="replaceLows" id="replaceLows"> </td>
				                <td><input type="number" class="withdImput" name="yoursLows"  id="yoursLows"> </td>
				                <td> <input type="number" class="withdImput" name="bigLows"  id="bigLows"> </td>
				            </tr>
				            <tr>
				                 <td>${roomMid }局</td>
				                <td><input type="number" class="withdImput" name="replaceMid"  id="replaceMid"> </td>
				                <td><input type="number" class="withdImput" name="yoursMid"  id="yoursMid"> </td>
				                <td><input type="number" class="withdImput" name="bigMid"  id="bigMid"> </td>
				
				            </tr>
				            <tr>
				            	<td>${roomHig }局</td>
				              	<td><input type="number" class="withdImput" name="replaceHig"  id="replaceHig"> </td>
				                <td><input type="number" class="withdImput" name="yoursHig"  id="yoursHig"> </td>
				                <td><input type="number" class="withdImput" name="bigHig"  id="bigHig"> </td>
				
				            </tr>
				           
				            </tbody>
           			 </table>
           			 </from>
           			 
           			  <div class="center">
				        <button id="btnAdd" type="button"  onclick="editClubRoom();" class="btn btn-success btn-sm">
<!-- 				        onclick="javascript:$('#storeForm').submit();" -->
				            <i class="fa fa-user-plus"></i>&nbsp;保存
				        </button>
				        <button id="btn" type="button" onclick="closeClubEdit()"
				                class="btn btn-info btn-sm">
				            <i class="fa fa-undo"></i>&nbsp;返回
				        </button>
				    </div>
    <!-- 
           			 <div class="text-center">
				            	<input type="button " class="btn btn-primary"  value="提交修改" onclick="javascript:$('#storeForm').submit();">
				            	<input type="button" class="btn btn-success" value="返回">
           			 </div>
           			 </div> -->
</div>

