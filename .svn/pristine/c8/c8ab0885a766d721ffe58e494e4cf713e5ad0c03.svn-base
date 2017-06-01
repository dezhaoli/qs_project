<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增活动
        </c:if>
        <c:if test="${!empty record}">
            编辑活动
        </c:if>
    </h1>
</div>
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
                <label class="control-label col-sm-1 no-padding-right">活动标题</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="title" id="title" type="text"
                               value="${record.title}" placeholder="活动标题..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">描述</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="descr" id="descr" type="text"
                               value="${record.descr}" placeholder="描述..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">活动类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="type" name="type" style="width: 100%">
                            <c:forEach items="${activityList}" var="act">
                                <option value="${act.code}"
                                        <c:if test="${record.type eq act.code}">selected="selected"</c:if>>${act.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">是否启用</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="status" name="status" style="width: 100%">
                            <option value="0" <c:if test="${record.status eq '0' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="1" <c:if test="${record.status eq '1' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">排序</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="sort" id="sort" type="number"
                               value="${record.sort }" placeholder="排序..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">上传按钮图片</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <%--<input class="form-control" name="btnUrl" id="btnUrl" type="text"
                               value="${record.btnUrl }" placeholder="按钮url..."/>--%>
                        <input  class="btn-upload" id="btnImgUpload" type="file" onchange="uploadImg('btnImgUpload')" accept="image/*"  multiple="multiple"/>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">跳转url</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="jumpUrl" id="jumpUrl" type="text"
                               value="${record.jumpUrl }" placeholder="跳转url..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">上传活动图片</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <%--<input class="form-control" name="actImgUrl" id="actImgUrl" type="text"
                               value="${record.actImgUrl }" placeholder="活动图片url..."/>--%>
                        <input  class="btn-upload" id="actImgUpload" type="file" onchange="uploadImg('actImgUpload')" accept="image/*"  multiple="multiple"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">活动开始时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="startTime" id="startTime" type="text"
                               value="<fmt:formatDate value="${record.startTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               placeholder="活动开始时间..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">活动结束时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="endTime" id="endTime" type="text"
                               value="<fmt:formatDate value="${record.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               placeholder="活动结束时间..."/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">关闭面板时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="closeTime" id="closeTime" type="text"
                               value="<fmt:formatDate value="${record.closeTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               placeholder="关闭面板时间..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">活动时间段</label>
                <div class="col-sm-2">
                    <div class="clearfix">
                        <input class="form-control Wdate" name="periodStartTime" id="periodStartTime" type="text"
                               onfocus="WdatePicker({dateFmt:'H:mm:ss',minDate:'00:00:00',maxDate:'23:59:59'})"
                               value="${record.periodStartTime}" style="height: 32px;"
                               placeholder="活动开始时段..."/>
                    </div>
                </div>
                <div class="col-sm-1">

                </div>
                <div class="col-sm-2">
                    <div class="clearfix">
                        <input class="form-control Wdate"  name="periodEndTime" id="periodEndTime" type="text"
                               onfocus="WdatePicker({dateFmt:'H:mm:ss',minDate:'00:00:00',maxDate:'23:59:59'})"
                                   value="${record.periodEndTime}" style="height: 32px;"
                               placeholder="活动结束时段..."/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">玩牌局数</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="cardRecord" id="cardRecord" type="text"
                               value="${record.cardRecord}" placeholder="玩牌局数..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">奖励</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="reward" id="reward" type="text"
                               value="${record.reward }" placeholder="奖励..."/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">活动状态</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="actiStatus" name="actiStatus" style="width: 100%">
                            <c:forEach items="${astl}" var="act">
                                <option value="${act.code}"
                                        <c:if test="${record.actiStatus eq act.code}">selected="selected"</c:if>>${act.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>

            <input type="hidden" id="btnUrl" name="btnUrl" value="${record.btnUrl}">
            <input type="hidden" id="actImgUrl" name="actImgUrl" value="${record.actImgUrl}">

        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/acti/listUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    jeDate({
        dateCell:"#startTime",//isinitVal:true,
        format:"YYYY-MM-DD hh:mm:ss",
        //isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00:00"
    });
    jeDate({
        dateCell : '#endTime',
        format:"YYYY-MM-DD hh:mm:ss",
        //isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00:00"
    });
    jeDate({
        dateCell : '#closeTime',
        format:"YYYY-MM-DD hh:mm:ss",
        //isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00:00"
    });
   /* jeDate({
        dateCell : '#periodStartTime',
        format:"YYYY-MM-DD hh:mm:ss",
        //isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00:00"
    });
    jeDate({
        dateCell : '#periodEndTime',
        format:"YYYY-MM-DD hh:mm:ss",
        //isinitVal:true,
        isTime:true, //isClear:false,
        minDate:"2014-09-19 00:00:00"
    });*/

    $(function () {
        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                title: {
                    required: true
                },
                type: {
                    required: true
                },
                sort: {
                    required: true
                },
                startTime: {
                    required: true
                },
                endTime: {
                    required: true
                },
                status: {
                    required: true
                }
            },
            messages: {
                title: "活动标题不能为空",
                sort: "请为活动排序",
                startTime: "活动开始时间不能为空",
                endTime: "活动结束时间不能为空",
                status: "请选择活动是否启用",
                type: "活动类型不能为空"
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
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/acti/edit.html';
                } else {
                    url = '/acti/add.html';
                }
                commit('storeForm', url, '/acti/listUi.html');
                //webside.common.commit('storeForm', url, '/acti/listUi.html');
            }
        });

        function commit(formId, commitUrl, jumpUrl) {
            debugger;
            //组装表单数据
            var index;
            var data = $("#" + formId).serialize();
            if (undefined != $("#pageNum").val()) {
                jumpUrl = jumpUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val();
            }
            $.ajax({
                type : "POST",
                url : sys.rootPath + commitUrl,
                data : data,
                dataType : "json",
                beforeSend : function() {
                    index = layer.load();
                },
                success : function(resultdata) {
                    layer.close(index);
                    if (resultdata.success) {
                        layer.msg(resultdata.message, {
                            icon : 1
                        });
                        webside.common.loadPage(jumpUrl);
                    } else {
                        layer.msg(resultdata.message, {
                            icon : 5
                        });
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
    });

    function uploadImg(img) {// btnImgUpload   actImgUpload
        var	oFile = document.getElementById(img).files[0];
        var ii = null;
        if (img == "btnImgUpload"){
            ii = "btnUrl";
        }else if ("actImgUpload" == img){
            ii = "actImgUrl";
        }
        if (oFile == undefined || oFile == "") {
            layer.msg("请选择上传的文件!", {icon: 5});
            return;
        }
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
                console.log(data);
                if (data.errorType == true) {
                    layer.msg("请选择图片类型！", {
                        icon : 5
                    });
                    return;
                }
                if (data.state == 200) {//上传成功,更新用户信息
                    layer.msg("上传成功", {
                        icon : 1
                    });
                    $('#' + ii).val(data.url);
                    //document.getElementById(ii).val(data.url);
                    oFile = "";
                }else{
                    layer.msg("上传失败", {
                        icon : 5
                    });
                    oFile = "";
                }
            },
            error:function () {
                oFile = "";
            }
        });
    }

</script>