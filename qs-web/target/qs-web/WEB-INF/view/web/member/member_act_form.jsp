<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增移动版本
        </c:if>
        <c:if test="${!empty record}">
            编辑移动版本
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
                <label class="control-label col-sm-1 no-padding-right">平台</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="site" name="site" style="width: 100%">
                            <option value="1" <c:if test="${record.site eq '1' }">selected="selected"</c:if>>
                                安卓
                            </option>
                            <option value="2" <c:if test="${record.site eq '2' }">selected="selected"</c:if>>
                                ios
                            </option>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">渠道</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="channel" id="channel" type="number"
                               value="${record.channel}" placeholder="渠道..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">大版本号</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="bigversion" id="bigversion" type="number"
                               value="${record.bigversion }" placeholder="大版本号..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">版本号</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="version" id="version" type="number"
                               value="${record.version }" placeholder="版本号..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">是否可以跳过</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="isskip" name="isskip" style="width: 100%">
                            <option value="0" <c:if test="${record.site eq '0' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="1" <c:if test="${record.site eq '1' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">更新描述</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="des" id="des" type="text"
                               value="${record.des }" placeholder="更新描述..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">最新的下载地址</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="url" id="url" type="text"
                               value="${record.url }" placeholder="最新的下载地址..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">上线版本</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="onlineversion" id="onlineversion" type="text"
                               value="${record.onlineversion }" placeholder="上线版本..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">测试设备号列表</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="devicelistTest" id="devicelistTest" type="text"
                               value="${record.devicelistTest }" placeholder="测试设备号列表..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">测试URL</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="urlTest" id="urlTest" type="text"
                               value="${record.urlTest }" placeholder="测试URL..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">强更URL</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="forceurl" id="forceurl" type="text"
                               value="${record.forceurl }" placeholder="强更URL..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">游戏类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                       <%-- <input class="form-control" name="gameType" id="gameType" type="number"
                               value="${record.gameType }" placeholder="游戏类型..."/>--%>
                        <select class="form-control" id="gameType" name="gameType" style="width: 100%">
                            <option value="0" <c:if test="${record.gameType eq '0' }">selected="selected"</c:if>>
                                上好麻将
                            </option>
                            <option value="1" <c:if test="${record.gameType eq '1' }">selected="selected"</c:if>>
                                跑得快
                            </option>
                            <option value="2" <c:if test="${record.gameType eq '2' }">selected="selected"</c:if>>
                                疯狂斗牛
                            </option>
                            <option value="3" <c:if test="${record.gameType eq '3' }">selected="selected"</c:if>>
                                木虱
                            </option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">上次更新时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="lasttime" id="lasttime" disabled="disabled"
                               value="<fmt:formatDate value="${record.lasttime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               placeholder="上次更新时间...">
                        <%--<input class="form-control" name="lasttime" id="" type="text"
                               value="${record.lasttime }" disabled="disabled" placeholder="上次更新时间..."/>--%>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>
</div>
</div>
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
    <button id="btn" type="button" onclick="webside.common.loadPage('/game/version/listUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    $(function () {
/*
*
*  `site` tinyint(4) NOT NULL COMMENT '平台',
 `channel` int(11) NOT NULL COMMENT '渠道',
 `bigversion` tinyint(4) NOT NULL COMMENT '大版本号',
 `version` smallint(6) NOT NULL COMMENT '版本号',
 `isskip` tinyint(4) NOT NULL COMMENT '是否可以跳过',
 `des` varchar(1000) NOT NULL COMMENT '更新描叙',
 `url` varchar(500) NOT NULL COMMENT '最新的下载地址',
 `onlineversion` varchar(20) NOT NULL COMMENT '线上版本',
 `devicelist_test` varchar(2000) NOT NULL COMMENT '测试设备号列表',
 `url_test` varchar(500) NOT NULL COMMENT '测试URL',
 `lasttime` int(11) NOT NULL COMMENT '上次更新时间',
 `forceurl` varchar(500) NOT NULL COMMENT '强更URL',
 `game_type` int(11) NOT NULL COMMENT '游戏类型',
* */
        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                site: {
                    required: true
                },
                channel: {
                    required: true
                },
                version: {
                    required: true
                },
                isskip: {
                    required: true
                },
                des: {
                    required: true
                },
                url: {
                    required: true
                },
                onlineversion: {
                    required: true
                },
                devicelistTest: {
                    required: true
                },
                urlTest: {
                    required: true
                },
                forceurl: {
                    required: true
                },
                gameType: {
                    required: true
                },
                bigversion: {
                    required: true
                }
            },
            messages: {
                site: "平台",
                channel: "渠道",
                version: "版本号",
                isskip: "是否可以跳过",
                des: "更新描述",
                url: "URL(测试时务必不填写此地址)",
                onlineversion: "线上版本",
                devicelistTest: "测试设备号列表",
                urlTest: "测试URL",
                forceurl: "强更url",
                gameType: "游戏类型",
                bigversion:"大版本号"
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
                debugger;
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/game/version/edit.html';
                } else {
                    url = '/game/version/add.html';
                }
                webside.common.commit('storeForm', url, '/game/version/listUi.html');
            }
        });


    });
</script>