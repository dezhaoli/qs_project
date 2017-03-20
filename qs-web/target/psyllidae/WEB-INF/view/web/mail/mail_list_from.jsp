<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/mail_list.js"></script>
<div class="page-header">
    <h1>
        邮件管理
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#listMail" data-toggle="tab">邮件列表</a></li>

            <li><a href="#addMail" data-toggle="tab">添加邮件</a></li>
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

                        <div class="form-horizontal" role="form">
                            <%--<div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">游戏</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="optionsRadios" id="allGame"
                                                       value="allGame">全部
                                            </label>
                                            <label>
                                                <input type="radio" name="optionsRadios" id="optionGame"
                                                       value="optionGame">指定游戏
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>--%>
                            <div class="form-group">
                                <div class="col-sm-1"></div>
                                <div class="col-sm-4">
                                    <div class="clearfix">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" disabled="disabled"
                                                       name="gameType" value="1" checked>
                                                牵手四川麻将
                                            </label>
                                            <%--<label>
                                                <input type="checkbox" name="gameType" value="1">
                                                牵手跑得快
                                            </label>
                                            <label>
                                                <input type="checkbox" name="gameType" value="2">
                                                牵手跑胡子
                                            </label>
                                            <label>
                                                <input type="checkbox" name="gameType" value="3">
                                                疯狂斗牛OL
                                            </label>
                                            <label>
                                                <input type="checkbox" name="gameType" value="4">
                                                牵手湖南麻将
                                            </label>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">用户</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <div>
                                            <label class="checkbox-inline">
                                                <input type="radio" name="user"
                                                       value="allUser" checked>全部
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="radio" name="user"
                                                       value="optionUser">指定用户
                                            </label>
                                            <input type="text" id="users" class="form-control" disabled
                                                   placeholder="用户ID,多个用户ID用英文逗号隔开...">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">邮件标题</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <input class="form-control" name="mailTitle" type="text" id="mailTitle"
                                               placeholder="长度不能超过30个字..."/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">邮件内容</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <textarea class="form-control" id="mailContent"
                                                  name="mailContent" placeholder="邮件内容..."></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">邮件到期时间</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <input class="form-control" name="expireTime"
                                               id="expireTime" type="text"
                                               value="" placeholder="邮件到期时间..."/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-1 no-padding-right">是否重要</label>
                                <div class="col-sm-5">
                                    <div class="clearfix">
                                        <select class="form-control" id="important" name="important" style="width: 100%">
                                            <option value="1">
                                                是
                                            </option>
                                            <option value="0" selected="selected">
                                                否
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group" >
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-1">
                                    <button id="btnSelect" class='btn btn-sm btn-warning' type="button">添加金币</button>
                                </div>
                                <div class="col-sm-4">

                                </div>
                            </div>

                            <div class="form-group" >
                                <div class="col-sm-1">
                                </div>
                                <div class="col-sm-5">
                                <table class="table table-condensed table-striped table-hover table-bordered"
                                       id="selectedTable" >
                                </table>
                                </div>
                            </div>

                            <div class="form-group" >
                                <div class="col-sm-5">
                                </div>
                                <div class="col-sm-1">
                                    <button class='btn-success btn-sm' type="button"
                                    id="saveMail">添加邮件</button>
                                </div>
                            </div>

                        </div>

                    </div>

                    </div>
                </div>
            </div>

            <div class="hr hr-dotted"></div>
        </div>
    </div>
</div>

<script>


    $(function () {
        jeDate({
            dateCell: '#expireTime',
            isinitVal: false,
            format: 'YYYY-MM-DD hh:mm:ss', // 分隔符可以任意定义，该例子表示只显示年月
            minDate: '1900-06-01 00:00:00', //最小日期
            maxDate: '2050-06-01 23:59:59' //最大日期
        });


       $("input[name='user']").on('click',function () {
           var value = $(this).val();
           if ("optionUser" == value) {
               $('#users').removeAttr('disabled');
           }else if ("allUser" == value) {
               $("#users").val(null);
               $('#users').attr('disabled','');
           }
       });

        $("#selectedTable").on('click', ".delBtn", function (){
            var $td=$(this).parent().parent().children('td');
            $(this).parent().parent().remove();
        });

        $("#btnSelect").click(function(){
            var btn = $("<input class='delBtn btn btn-xs btn-danger' type='button' value='删除' />");
            var input=$("<input type='number' name='attach' class='form-control' " +
                " placeholder='输入金币数量...'/>");
            var newRow = $("<tr>").append($("<td>").append(input)).append($("<td align='center;'>").append(btn));
            $("#selectedTable").append(newRow);
        });

        $('#saveMail').on('click',function () {
            var result = handleSaveMailData(true);
            var result1 = null;
            if (result != null) {
                layer.msg(result, {icon : 0});
                return;
            }else {
                result1 = handleSaveMailData(false);
                result1 = JSON.parse(result1);
                //console.log(result1);
            }

            //alert(result1.expired);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath }/game/mail/save.html",
                data:result1,//将对象序列化成JSON字符串
                dataType:"json",
                //contentType : 'application/json;charset=utf-8', //设置请求头信息
                success: function(data){
                    if (data.success == true){
                        webside.common.loadPage('/game/mail/listUi.html');
                    }else{
                        layer.msg(data.message, {icon : 5});
                    }
                },
                error: function(res){

                }
            });

            function handleSaveMailData(debug) {
                var usersValues = $('#users').val();
                if (typeof($("#users").attr("disabled")) == "undefined") {
                    if (usersValues == null || usersValues == "") {
                        return "请填写用户ID"
                    }
                }
                var gameType = $("input[name='gameType']").val();//目前只有四川麻将，已经是所以不用判断。

                var mailTitle = $('#mailTitle').val();//mailContent   expireTime important  attach
                if (null == mailTitle || mailTitle == "") {
                    return "请填写邮件标题";
                }
                var mailContent = $('#mailContent').val();
                if (null == mailContent || mailContent == "") {
                    return "请填写邮件内容";
                }
                var expireTime = $('#expireTime').val();
                if (null == expireTime || expireTime == "") {
                    return "请选择过期时间";
                }
                var important = $('#important').val();
                //var attach = $('#attach').val();
                var attach = new Array;
                $("input[name='attach']").each(function(i){
                    if (null != $(this).val() && "" != $(this).val()) {
                        attach[i] = $(this).val();
                    }
                });
                for (var i=0;i<attach.length;i++) {
                    if (attach[i] == "" || attach[i] == null) {
                        return "请填写金币值";
                    }
                }
                var attachs = attach.join(',');

                if (debug) {
                    return null;
                }else {
                    //debugger; // []
                    /*if (attach == null || attach == "") {
                        var content = jsonBuilder.add("users",usersValues).add("mailTitle",mailTitle)
                            .add("gameType",gameType).add("mailType",mailTitle)
                            .add("mailContent",mailContent).add("expireTime",expireTime)
                            .add("important",important).getJson();
                        return content;
                    }else {
                        var content = jsonBuilder.add("users",usersValues).add("mailTitle",mailTitle)
                            .add("gameType",gameType).add("mailType",mailTitle)
                            .add("mailContent",mailContent).add("expireTime",expireTime)
                            .add("important",important).add("attachs",attachs).getJson();
                        return content;
                    }*/
                    var target ;
                    if (usersValues == null || usersValues == "") {//选择全部用户
                        //usersValues = null ;//users
                        target = 1;
                    }else {
                        target = 2;
                    }
                    if (attach == null || attach == "") {
                        var content = jsonBuilder.add("target",target)
                            .add("gametype",gameType).add("title",mailTitle)
                            .add("content",mailContent).add("expired",expireTime)
                            .add("important",important).getJson();
                        return content;
                    }else {
                        var content = jsonBuilder.add("target",target).add("mids",usersValues)
                            .add("gametype",gameType).add("title",mailTitle)
                            .add("content",mailContent).add("expired",expireTime)
                            .add("important",important).add("fujian",attachs).getJson();
                        return content;
                    }
                }
            }

        });

    });

    var jsonBuilder = {
        add:function (key,value) {
            this.startJson += "\""+key+"\"" +":" + "\""+value+"\",";
            return this;
        },
        startJson:"{",
        getJson:function () {
            var aa = this.startJson.substring(0,this.startJson.length - 1) + "}";
            this.startJson = "{";
            return aa;
        }
    }

</script>

