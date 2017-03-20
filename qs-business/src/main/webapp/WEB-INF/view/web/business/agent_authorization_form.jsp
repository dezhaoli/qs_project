<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        代理商授权
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
                <div class="col-sm-2">

                </div>
                <label class="control-label col-sm-1 no-padding-right">选择游戏</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" type="text"
                               value="四川麻将" readonly/>
                        <input type="hidden" id="gameId" name="gameId" value="6">
                        <!-- 四川麻将的gameId未设置。开发时测试的是跑得快的gameId。 -->
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2">

                </div>
                <label class="control-label col-sm-1 no-padding-right">用户ID</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="userId" id="userId" type="number"
                               value="" placeholder="用户ID..."/>
                        <%--<input id="agentId" value="${agentId}" name="agentId" type="hidden"/>--%>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="center">
    <button id="searchUserBtn" type="button" onclick=""
            class="btn btn-success btn-sm">
        <i class="fa fa-search"></i>&nbsp;查询
    </button>

    <%--<button id="btn" type="button" onclick="closeL()"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>--%>
</div>

<div class="hr hr-dotted"></div>
<div class="row" style="margin-top:5px; display: none;" id="showSearch">
    <div class="col-xs-12">

    </div>
</div>

<div class="row" style="margin-top:5px; display: none;" id="saveAgent">
    <div class="col-xs-12">
        <div class="center">
            <button id="queryUserBtn" type="button" onclick="executeSaveAgent()" class="btn btn-danger btn-sm">
                <i class="fa fa-adn"></i>&nbsp;授权代理商
            </button>
        </div>
    </div>
</div>


<script type="text/javascript">

   /* var addAgentBt = "<div class=\"center\"><button id=\"queryUserBtn\" type=\"button\" onclick=\"executeSaveAgent()\"\n" +
        "            class=\"btn btn-success btn-sm\">\n" +
        "        <i class=\"fa fa-adn\"></i>&nbsp;授权代理商\n" +
        "    </button></div>";*/

    function showAgentView(bizname,parentId,isAgent,belongAgent) {
        var resultForm = "<form class=\"form-horizontal\" role=\"form\" method=\"post\">\n" +
            "\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"col-sm-2\">\n" +
            "\n" +
            "                </div>\n" +
            "                <label class=\"control-label col-sm-1 no-padding-right\">用户昵称</label>\n" +
            "                <div class=\"col-sm-5\">\n" +
            "                    <div class=\"clearfix\">\n" +
            "                        <input class=\"form-control\" type=\"text\" id=\"bizname\" " +
            "                           name=\"bizname\" value=\""+bizname+"\" placeholder=\"用户昵称...\" readonly>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"col-sm-2\">\n" +
            "\n" +
            "                </div>\n" +
            "                <label class=\"control-label col-sm-1 no-padding-right\">绑定用户</label>\n" +
            "                <div class=\"col-sm-5\">\n" +
            "                    <div class=\"clearfix\">\n" +
            "                        <input class=\"form-control\" name=\"parent_id\" id=\"parent_id\" type=\"number\"\n" +
            "                               value=\""+parentId+"\" placeholder=\"绑定用户...\" readonly/>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"form-group\">\n" +
            "                <div class=\"col-sm-2\">\n" +
            "\n" +
            "                </div>\n" +
            "                <label class=\"control-label col-sm-1 no-padding-right\">是否代理商</label>\n" +
            "                <div class=\"col-sm-5\">\n" +
            "                    <div class=\"clearfix\">\n" +
            "                        <input class=\"form-control\" type=\"text\"\n" +
            "                               value=\""+isAgent+"\" placeholder=\"是否代理商...\" readonly/>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n";

        var fromEnd = "        </form>";

        var belongName =  "            <div class=\"form-group\">\n" +
            "                <div class=\"col-sm-2\">\n" +
            "\n" +
            "                </div>\n" +
            "                <label class=\"control-label col-sm-1 no-padding-right\">所属商务</label>\n" +
            "                <div class=\"col-sm-5\">\n" +
            "                    <div class=\"clearfix\">\n" +
            "                        <input class=\"form-control\" type=\"text\"\n" +
            "                               value=\""+belongAgent+"\" placeholder=\"所属商务...\" readonly/>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" ;
            if (belongAgent != null) {
                return resultForm + belongName + fromEnd;//已经授权代理商
            }else {
                return resultForm + fromEnd ;//未授权代理商
            }
    }

    function executeSaveAgent() {
        var formObj = $('form[name="storeForm"]');
        var msgBox = $('<div class="aler alert-danger" style="text-align: center;" id="queryUserTip"></div>');
        var gameId = $('#gameId').val();
        var userId = $.trim($('#userId').val());
        var regNotInt = /[^\d]+?/;

        if(formObj.find('#queryUserTip').length > 0) $('#queryUserTip').remove();
        if(gameId == -1){
            formObj.prepend(msgBox.html('请选择游戏'));
            return false;
        }
        if(userId == ''){
            formObj.prepend(msgBox.html('请输入用户ID'));
            return false;
        }
        if(regNotInt.test(userId)){
            formObj.prepend(msgBox.html('用户ID有误'));
            return false;
        }

        $.ajax({
            type: "POST",
            url: "${ctx}/business/searchUserInfo.html",
            data: {
                gameId:gameId,
                userId:userId
            },
            dataType: "json",
            beforeSend: function(xhr){
                $('.query-content').html('<div class="info-tip"><span class="icon-spinner icon-2x icon-spin">' +
                    '</span></div>');
            },
            success: function(data){
                console.log(data.data);
                if (data.success == false) {
                    layer.msg(data.message, {icon: 5},1000);
                    if (data.message == "该用户已经是代理商") {
                        var bizname = data.data.bizname;
                        var parentId = data.data.parent_id;
                        var belongAgent = data.data.belongAgent;
                        var result = showAgentView(bizname,parentId,"是",belongAgent);
                        $('#showSearch').show().html(result);
                    }else {
                        $('#showSearch').show().html(data.message);
                    }
                }else {
                    var result = showAgentView(data.data.memberFides.name,data.data.memberFides.invite,"否",null);
                    $('#showSearch').show().html(result);
                }

            }
        });
    }

    $('#searchUserBtn').on('click',function () {
        searchUserClick();
    });

    function searchUserClick() {
        var formObj = $('form[name="storeForm"]');
        var msgBox = $('<div class="aler alert-danger" style="text-align: center;" id="queryUserTip"></div>');
        var gameId = $('#gameId').val();
        var userId = $.trim($('#userId').val());
        var regNotInt = /[^\d]+?/;

        if(formObj.find('#queryUserTip').length > 0) $('#queryUserTip').remove();
        if(gameId == -1){
            formObj.prepend(msgBox.html('请选择游戏'));
            return false;
        }
        if(userId == ''){
            formObj.prepend(msgBox.html('请输入用户ID'));
            return false;
        }
        if(regNotInt.test(userId)){
            formObj.prepend(msgBox.html('用户ID有误'));
            return false;
        }

        $.ajax({
            type: "POST",
            url: "${ctx}/business/searchUserInfo.html",
            data: {
                gameId:gameId,
                userId:userId
            },
            dataType: "json",
            beforeSend: function(xhr){
                $('.query-content').html('<div class="info-tip"><span class="icon-spinner icon-2x icon-spin">' +
                    '</span></div>');
            },
            success: function(data){
                console.log(data.data);
                if (data.success == false) {
                    layer.msg(data.message, {icon: 5},1000);
                    if (data.message == "该用户已经是代理商") {
                        var bizname = data.data.bizname;
                        var parentId = data.data.parent_id;
                        var belongAgent = data.data.belongAgent;
                        var result = showAgentView(bizname,parentId,"是",belongAgent);
                        $('#showSearch').show().html(result);
                    }else {
                        $('#showSearch').show().html(data.message);
                    }
                    $('#saveAgent').hide();
                }else {
                    //console.log(data.data.memberInvite);
                    //console.log(data.data.memberFides);
                    //console.log(data.data.memberBusiness);
                    //console.log(data.data.parentAgentInfo);

                    var result = showAgentView(data.data.memberFides.name,data.data.memberFides.invite,"否",null);
                    $('#showSearch').show().html(result);
                    $('#saveAgent').show();
                }

            }
        });
    }


    $('#queryUserBtn').on('click', function(){
        var formObj = $('form[name="storeForm"]');
        var msgBox = $('<div class="aler alert-danger" style="text-align: center;" id="queryUserTip"></div>');
        var gameId = $('#gameId').val();
        var userId = $.trim($('#userId').val());
        var regNotInt = /[^\d]+?/;

        if(formObj.find('#queryUserTip').length > 0) $('#queryUserTip').remove();
        if(gameId == -1){
            formObj.prepend(msgBox.html('请选择游戏'));
            return false;
        }
        if(userId == ''){
            formObj.prepend(msgBox.html('请输入用户ID'));
            return false;
        }
        if(regNotInt.test(userId)){
            formObj.prepend(msgBox.html('用户ID有误'));
            return false;
        }

        //询问框
        layer.confirm('您是要将玩家ID:'+userId+'添加为代理商吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            //var callUrl = formObj.attr('action');
            //var sendData= formObj.serialize();

            $.ajax({
                type: "POST",
                url: "${ctx}/business/authorization.html",
                data: {
                    gameId:gameId,
                    userId:userId
                },
                dataType: "json",
                beforeSend: function(xhr){
                    $('.query-content').html('<div class="info-tip"><span class="icon-spinner icon-2x icon-spin">' +
                        '</span></div>');
                },
                success: function(data){
                    if (data.success == false) {
                        layer.msg(data.message, {icon: 5},1000);
                    }else {
                        layer.msg("授权成功!", {icon: 6},250);

                        //使用方法名字执行方法
                        //var t1 = window.setTimeout(reFresh,500);
                        //var t2 = window.setTimeout("hello()",3000);//使用字符串执行方法
                        //window.clearTimeout(t1);//去掉定时器
                    }
                    //$('.query-content').html(msg);
                }
            });
        }, function(){
            /*layer.msg('已取消', {
                time: 200 //20s后自动关闭
            });*/
        });

        function reFresh() {
            webside.common.loadPage('/business/authorizationUi.html');
        }
    });

    function doAjaxTextReturn(obj, cls){
        /*if(msg){
            doAjaxTextReturn(msg, ".query-content")
        }*/

        if(typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase()
            == "[object object]" && !obj.length){
            window.location.href = obj.data;
        }else{
            $(cls).html(obj);
        }
    }

   //注册回车键事件
   document.onkeypress = function(e){
       var ev = document.all ? window.event : e;
       if(ev.keyCode==13) {
           searchUserClick();
       }
   };

</script>