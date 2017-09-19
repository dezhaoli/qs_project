<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/mail_list.js"></script>--%>
<div class="page-content">
    <div class="center">
        <div class="page-header">
            <h1>
                编辑奖品概率
            </h1>
        </div>
        <div class="row" style="margin-top:5px;">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <!-- actiAwardPro  actiAward -->
                                </div>
                                <label class="control-label col-sm-1 no-padding-right">奖品名称</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input class="form-control" name="awardName" type="text" id="awardName"
                                               value="${actiAward.name}" placeholder="奖品名称..." disabled/>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">奖品概率</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input class="form-control" name="awardPro" type="number" id="awardPro"
                                               value="${actiAwardPro.awardPro}" placeholder="奖品概率"/>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">奖品概率库存</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input class="form-control" name="awardProStock" type="number"
                                               id="awardProStock"
                                               value="${actiAwardPro.awardProStock}" placeholder="奖品概率库存"/>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">何时参与抽奖</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <input class="form-control" name="producDate" id="producDate" type="text"
                                               value="<fmt:formatDate value="${actiAwardPro.producDate}" type="both" pattern="yyyy-MM-dd"/>"
                                               placeholder="何时参与抽奖..."/>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <hr/>

                            <div class="form-group">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">启用高级选项</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <div class="clearfix">
                                            <select class="form-control" id="status" name="status" style="width: 100%">
                                                <option value="1" <c:if test="${actiAwardPro.status eq '1' }">selected="selected"</c:if>>
                                                    否
                                                </option>
                                                <option value="0" <c:if test="${actiAwardPro.status eq '0' }">selected="selected"</c:if>>
                                                    是
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group" id="mrzj" style="display: none;">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">每日增加/减少概率</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <div class="clearfix">
                                            <input class="form-control" name="daliAddPro" type="number" id="daliAddPro"
                                                   value="${actiAwardPro.daliAddPro}" placeholder="每日增加/减少概率"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group" id="hszj" style="display: none;">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">何时开始增/减概率</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <div class="clearfix">
                                            <input class="form-control" name="addProDate" id="addProDate" type="text"
                                                   value="<fmt:formatDate value="${actiAwardPro.addProDate}" type="both" pattern="yyyy-MM-dd"/>"
                                                   placeholder="何时开始增加/减少概率..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group" id="glly" style="display: none;">
                                <div class="col-sm-2">

                                </div>
                                <label class="control-label col-sm-1 no-padding-right">概率来源</label>
                                <div class="col-sm-6">
                                    <div class="clearfix">
                                        <div class="clearfix">
                                            <select class="form-control" name="proSources" id="proSources">
                                                <c:forEach items="${actiAwardList}" var="act">
                                                    <option value="${act.id}"
                                                            <c:if test="${actiAwardPro.proSources eq act.id}">selected="selected"</c:if>>${act.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3">

                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-1">
                                    <span class="input-group-btn">
                                        <button class='btn btn-app btn-primary btn-xs' type="button"
                                                id="addGold">
                                            <i class="icon-trash bigger-200"></i>提交
                                        </button>
                                    </span>
                                </div>
                                <div class="col-sm-1">
                                   <span class="input-group-btn">
                                        <button class="btn btn-app btn-danger btn-xs" type="button"
                                                onclick="closeLayer()">
                                            <i class="icon-trash bigger-200"></i>关闭
                                        </button>
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <input type="hidden" value="${actiAward.id}" id="awardId"/>
        <input type="hidden" value="${actiAward.type}" id="actiType"/>

        <div class="hr hr-dotted"></div>

    </div>
</div>

<script>
    jeDate({
        dateCell: "#producDate",//isinitVal:true,
        format: "YYYY-MM-DD",
        //isinitVal:true,
        isTime: true, //isClear:false,
        minDate: "2014-09-19"
    });
    jeDate({
        dateCell: "#addProDate",//isinitVal:true,
        format: "YYYY-MM-DD",
        //isinitVal:true,
        isTime: true, //isClear:false,
        minDate: "2014-09-19"
    });

    function closeLayer() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }

    if ($('#status').val() == 0) {
        $('#mrzj').show();
        $('#hszj').show();
        $('#glly').show();
    }else {
        $('#mrzj').hide();
        $('#hszj').hide();
        $('#glly').hide();
    }

    $('#status').change(function () {
        if ($('#status').val() == 0) {
            $('#mrzj').show();
            $('#hszj').show();
            $('#glly').show();
        }else {
            $('#mrzj').hide();
            $('#hszj').hide();
            $('#glly').hide();
        }
    });

    //alert($("#status").find("option:selected").text());

    $('#addGold').on('click', function () {
        var awardId = $('#awardId').val();
        var actiType = $('#actiType').val();
        var awardPro = $('#awardPro').val();
        var awardProStock = $('#awardProStock').val();
        var producDate = $('#producDate').val();
        var status = $('#status').val();
            var daliAddPro = $('#daliAddPro').val();
            var addProDate = $('#addProDate').val();
            var proSources = $('#proSources').val();

        if (awardId == undefined || awardId == null || awardId == '') {
            layer.msg("奖品id为空!", {icon: 5});//修改失败
            return;
        }
        if (actiType == undefined || actiType == null || actiType == '') {
            layer.msg("活动类型为空!", {icon: 5});//修改失败
            return;
        }
        if (producDate == undefined || producDate == null || producDate == '') {
            layer.msg("请选择参与抽奖日期!", {icon: 5});//修改失败
            return;
        }
        if (awardPro == undefined || awardPro == null || awardPro == '') {
            layer.msg("请填写奖品概率!", {icon: 5});//修改失败
            return;
        }
        if (awardProStock == undefined || awardProStock == null || awardProStock == '') {
            layer.msg("请填写奖品库存概率!", {icon: 5});//修改失败
            return;
        }
        if ((daliAddPro == undefined || daliAddPro == null || daliAddPro == '') &&
            (addProDate == undefined || addProDate == null || addProDate == '')) {
            proSources = 0;
        }else if (daliAddPro != '' && addProDate!= '') {

        } else {
            layer.msg("请把特殊选项选好或者全不填!", {icon: 5});//修改失败
            return;
        }

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath }/award/pro/edit.html",
            data: {
                awardId: awardId,
                actiType: actiType,
                awardProStock: awardProStock,
                producDate: producDate,
                daliAddPro: daliAddPro,
                addProDate: addProDate,
                proSources: proSources,
                awardPro: awardPro,
                status:status
            },//将对象序列化成JSON字符串
            dataType: "json",
            //contentType : 'application/json;charset=utf-8', //设置请求头信息
            success: function (data) {
                if (data.success == true) {
                    layer.msg(data.message, {icon: 6});//修改成功
                    setInterval(closeLayer, 1200);// 注意函数名没有引号和括弧！
                    //closeLayer();
                } else {
                    layer.msg(data.message, {icon: 5});//修改失败
                    setInterval(closeLayer, 1200);// 注意函数名没有引号和括弧！
                }
            },
            error: function (res) {
                layer.msg(res, {icon: 5, time: 500});//发生错误
            }
        });
    });

</script>