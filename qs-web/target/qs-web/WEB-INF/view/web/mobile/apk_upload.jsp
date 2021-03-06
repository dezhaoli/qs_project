<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="/common/common.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<div class="page-content">
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">APK包更新</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <%--<div class="clearfix" id="fileDiv">
                    <input  class="btn-upload" id="imgUpload" type="file"  accept="image/*"  multiple="multiple"/>
                    &lt;%&ndash;<input class="form-control" name="img" id="img" type="hidden" value=""/>&ndash;%&gt;
                </div>--%>
                <%--<a href="#" >
                    <span onclick='' id="uploadbt">上传</span>
                </a>--%>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <br>
                        <label for="apkVersion">APK版本号:  <span style="color: orangered;">* 示例105，表示第1个版本，第5个更新包</span></label>
                        <input type="number" class="form-control" id="apkVersion"
                               placeholder="三位数字，首位数字必须大于1">
                    </div>
                    <div class="form-group" id="fileDiv">
                        <input  class="btn-upload" id="imgUpload" type="file"  accept="image/*"  multiple="multiple"/>
                    </div>
                    <button type="button" class="btn btn-success" id="uploadbt">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function() {
    var apkId = '${id}';
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
        var apkVersion = $('#apkVersion').val();
        if (oFile == "") {
            layer.msg("请选择上传的文件!", {icon: 5});
            return;
        }
        if (apkVersion == null || apkVersion == "") {
            layer.msg("请填写版本号！", {icon: 5});
            return;
        }
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
            url: "${apkUploadUrl}upload.action",
            data: fd,
            success: function (data) {
                if (data.errorType == true) {
                    layer.msg("上传类型只能是apk", {
            			icon : 5
            		});
                    return;
                }
                if (data.state == 200) {//上传成功,更新用户信息
                    //console.log(data);
                    save2Db(apkId,data.size,data.originalFilename,apkVersion,data.path);
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

    function save2Db(apkId,apkSize,apkOrName,apkVersion,path) {//date后台再生成
        $.ajax({
            type: "POST",
            url: "/game/record/saveApkInfo",
            data: {
                apkId:apkId,
                apkSize:apkSize,
                apkOrName:apkOrName,
                apkVersion:apkVersion,
                apkPath:path
            },
            dataType: "json",
            success: function(data){
                if (data.success == true) {
                    layer.msg("上传成功", {
            			icon : 1
            		});
                    closeLayer();
                }
                if (data.success == false) {
                    layer.msg("上传失败", {
            			icon : 5
            		});
                    closeLayer();
                }
            },
            error:function (data) {
                layer.msg("上传失败", {
        			icon : 5
        		});
                closeLayer();
            }
        });
    }

    });

</script>
