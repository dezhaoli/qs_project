<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript">

</script>
<div class="page-header">
	<h1>
		基本参数设置
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		
		
		<form class="form-inline" role="form">
           <%-- <div class="form-group">
            <label class="control-label col-sm-1 no-padding-right">配置文件版本号:</label>
                <div class="col-sm-10">
		      <div class="clearfix">
              <input type="hidden" id="configVersionId">
           <input class="form-control" name="configVersion" id="configVersion" type="text" 
		           value="${configVersion}" placeholder="配置文件版本号(房间、商城)..."/>

                    </div>
		      </div>
        </div>
        <button type="button" class="btn btn-success btn-sm" id="configVersionUpdate">配置文件版本号</button> --%>
       </form>
		  <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="name">配置文件版本号:</label>
		      <div class="col-sm-9 ">
		      <div class="clearfix ">
		           <input type="hidden" id="configVersionId"     value="${baseParam.id}">
                   <input class="form-control" name="configVersion" id="configVersion" type="text" 
		           value="${baseParam.value}" placeholder="配置文件版本号(房间、商城)..."/>
		           
		      </div>
		      </div>
		        <button type="button"  col-sm-1 class="btn btn-success btn-sm" id="configVersionUpdate">更新</button>
		    
		   </div> 
			
	
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">

</div>

<script>
    /*$('#searchVersion').on('click',function (e) {
        $('#seacrhVersionKey').fadeToggle(100);
    });
    $('#seacrhVersionKey').on('change',function (e) {
        var optionValue = $(this).children('option:selected').val();
        grid.parameters = new Object();
        grid.parameters['site'] = optionValue;
        grid.refresh(true);
    });*/
    $(document).ready(function () {
        
        $('#configVersionUpdate').on('click',function () {
            var versionValue = $('#configVersion').val() + "";
            var id = $('#hiddenConfigVersionId').val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath }/base/param/updateBaseParam.html",
                data: {
                    versionValue:versionValue,
                    id:id
                },
                //contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {//android //configVersion
                    if (data.success == true) {
                    	layer.msg(data.message,{icon: 6});
                    }else{
                    	
                    	layer.msg(data.message,{icon: 5});
                    }
                    
                },
                error: function (msg) {
                    layer.msg(msg, {icon: 5});
                }
            });
        });

    });


</script>
