<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
    <link href="${pageContext.request.contextPath}/bui/css/bs3/dpl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bui/css/bs3/bui.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bui/js/jquery-1.8.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bui/js/sea.js"></script>
    <script src="${pageContext.request.contextPath}/bui/js/config.js"></script>
</head>

<body>
<div id="J_Uploader"></div>

<div>
    <input type="file" id="uploadInput">
    <button type="button" onclick="myUpload()">上传</button>
</div>


<script type="text/javascript">
    alert('${type}');
    BUI.use('bui/uploader', function (Uploader) {
        /*
         * 返回数据的格式
         *  默认是 {url : 'url'},否则认为上传失败
         *  可以通过isSuccess 更改判定成功失败的结构
         */
        var uploader = new Uploader.Uploader({
            //使用iframe上传类型，此类型不能实现进度条
            //type : 'iframe',
            render: '#J_Uploader',
            name: 'file',
            text: '上传图片',
            id: 'uploader',
            url: '${pageContext.request.contextPath}/upload.action'
        }).render();

        uploader.on('success', function (ev) {
            $(".bui-simple-list ul").hide();
            //获取上传返回的结果
            window.parent.window.uploadSuccess(ev);
        });
        uploader.on('error', function (ev) {
            //失败回调
        })
    });

    function myUpload(e) {
        //var _this=this;
        //$(_this).parent().find("img").attr("src","${ctx}/resources/images/loading.gif");
        var	oFile = this.files[0],
        var	oFile = $('#uploadInput').files[0],
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
            url: "${pageContext.request.contextPath}/upload.action",
            file: fd,//data
            success: function (data) {
                if (data.state == 200) {//上传成功,更新用户信息
                    alert(data);
                    console.log(data);
                    //$(_this).parent().find("img").attr("src",data.url);
                    //$(_this).parent().find("input[type='hidden']").val(data.path);
                    //$("#shopImg").val(data.path);
                }else{

                }

            }
        });
    }

</script>
</body>
</html>
