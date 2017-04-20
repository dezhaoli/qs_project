<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/agent_publish_form.js"></script>
<div class="page-header">
    <h1>
        返利比例管理
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#listMail" data-toggle="tab">公告列表</a></li>

            <li><a href="#addMail" data-toggle="tab">代理商后台公告</a></li>
        </ul>

        <div id="myTabContent" class="tab-content">
        
            <div class="tab-pane fade in active" id="listMail">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="widget-box transparent ui-sortable-handle"
                             style="opacity: 1; z-index: 0;">
                            <div class="widget-header">
                                <%--<h4 class="widget-title lighter">APK更新日志</h4>--%>
                                <div class="widget-toolbar no-border">
                                    <a href="#" data-action="fullscreen" class="orange2">
                                        <i class="ace-icon fa fa-arrows-alt"></i>
                                    </a>
                                    <a href="#" data-action="collapse" class="green">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="widget-body" style="display: block;">
                                <div class="widget-main padding-6 no-padding-left no-padding-right">
                                    <input id="pageNum" type="hidden" value="${page.pageNum }">
                                    <input id="pageSize" type="hidden" value="${page.pageSize }">
                                    <input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
                                    <input id="orderByType" type="hidden" value="${page.orderByType }">
                                    <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                                    <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="tab-pane fade" id="addMail">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
					<form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
                        <div class="form-horizontal" role="form">
                           <!--  <div class="form-group clear">
						        <label class="col-sm-1 control-label">公告图片名称</label>
						        <div class="col-sm-2">
						            <input class="form-control" type="text" name="p_img_name" placeholder="公告图片名称">
						        </div>
						        <div class="col-sm-3"><span class="text-danger">* 必须由英文字母组成，长度不能超过30，示例 yuandan</span></div>
						    </div> -->
						   <div class="form-group clear">
						        <label class="col-sm-1 control-label">上传公告图片</label>
						        <div class="col-sm-2">
						           <div class="form-control" id="fileDiv" style="border: 0px ;">
				                        <input  class="btn-upload" id="imgUpload" type="file"  accept="image/*"  multiple="multiple"/>
				                    </div>
						        </div>
						    </div> 
						    <div class="form-group clear">
						        <label class="col-sm-1 control-label">活动开始时间</label>
						        <div class="col-sm-2">
						            <input class="form-control" name="sDate" id="sDate"
									type="text" value="" placeholder="开始时间..." />
						        </div>
						        <div class="col-sm-3"><span class="text-danger">* 开始时间必须与公告图片中开始时间一致</span></div>
						    </div>
						    <div class="form-group clear">
						        <label class="col-sm-1 control-label">活动结束时间</label>
						        <div class="col-sm-2">
						             <input class="form-control" name="eDate" id="eDate" 
													type="text" value="" placeholder="结束时间..." />
						        </div>
						        <div class="col-sm-3"><span class="text-danger">* 结束时间必须与公告图片中结束时间一致</span></div>
						    </div>
						    <div class="form-group clear">
						        <label class="col-sm-1 control-label">显示状态</label>
						        <div class="col-sm-2">
						            <div class="radio">
						                <label>
						                    <input type="radio" name="p_show_status" value="0" checked="checked">一直显示
						                </label>
						                <label>
						                    <input type="radio" name="p_show_status" value="1">只显示一次
						                </label>
						            </div>
						        </div>
						        <div class="col-sm-3"><span class="text-danger">* 只在活动时间内显示</span></div>
						    </div>
						    <div class="form-group" >
                                <div class="col-sm-2">
                                </div>
                                <div class="col-sm-1">
                                    <button class='btn-success btn-sm' type="button" 
                                    id="uploadbt">添加</button> <!-- onclick="javascript:$('#storeForm').submit();" -->
                                </div>
                            </div>

                        </div>

					</form>
                    </div>

                    </div>
                </div>
                
            </div>

            <div class="hr hr-dotted"></div>
        </div>
    </div>
</div>

<script>
$(function() {
	jeDate({
		dateCell : '#sDate',
		isinitVal : true,
		format : 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
		minDate : '1900-06-01', //最小日期
		maxDate : '2050-06-01' //最大日期
	});
	jeDate({
		dateCell : '#eDate',
		isinitVal : true,
		format : 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
		minDate : '1900-06-01', //最小日期
		maxDate : '2050-06-01' //最大日期
	});
});

$(function() {
    //var apkId = '${id}';
    //上传消费凭证到服务器
    var	oFile = "";
     $('.btn-upload').change(function () {
         oFile = this.files[0];
     });

    $("#uploadbt").on('click',function(){
        //debugger
        //var loadImg="${ctx}/resources/images/loading.gif";
        //$("#imgStr").attr("src",loadImg);
        //var	oFile = this.files[0],
        //var apkVersion = $('#apkVersion').val();
        if (oFile == "") {
            layer.msg("请选择上传的文件!", {icon: 5});
            return;
        }
        /* if (apkVersion == null || apkVersion == "") {
            layer.msg("请填写版本号！", {icon: 5});
            return;
        } */
            sFileType = oFile.type;
        //var datatype = $(this).attr("datatype");
        var fd = new FormData();
        fd.append("file", oFile);
        fd.append("fileName",  oFile.name || oFile.fileName);
        fd.append("fileSize",  oFile.size || oFile.fileSize);
        $.ajax({
            type: "POST",
            contentType:false, //必须false才会避开jQuery对 formdata 的默认处理 , XMLHttpRequest会对 formdata 进行正确的处理
            processData: false, //必须false才会自动加上正确的Content-Type
            url: "${imgUploadUrl}img/upload.action",
            data: fd,
            success: function (data) {
                if (data.errorType == true) {
                    layer.msg("请选择图片类型！", {
            			icon : 5
            		});
                    return;
                }
                if (data.state == 200) {//上传成功,更新用户信息
                    //console.log(data);
                    save2Db(data.url);
                    oFile = "";
                }else{
                    oFile = "";
                }
            },
            error:function () {
                oFile = "";
            }
        });
    });

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }

    function save2Db(url) {//date后台再生成
    	var type=$("input[name='p_show_status']:checked").val(); 
    //var type=$("#radio input:checked").val();
    	var sTime=$("#sDate").val();
    	var eTime=$("#eDate").val();
        $.ajax({
            type: "POST",
            url: "${ctx}/publish/agentPublishAdd.html",
            data: {
                "sTime":sTime,
                "eTime":eTime,
                "type":type,
                "url":url
            },
            dataType: "json",
            success: function(data){
                if (data.success == true) {
                    layer.msg("上传成功", {
            			icon : 1
            		});
                    $(".page-content").load("${ctx}/publish/agentPublishUi.html");
                }
                if (data.success == false) {
                    layer.msg("上传失败", {
            			icon : 5
            		});
                }
            },
            error:function (data) {
                layer.msg("上传失败", {
        			icon : 5
        		});
            }
        });
    }

    });
</script>

