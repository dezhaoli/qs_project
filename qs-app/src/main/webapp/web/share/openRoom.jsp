<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" style="font-size: 75px;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>加入房间</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/web/share/files/joinRoom.css">
    <script type="text/javascript" src="${ctx }/lib/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/lib/md5/jquery.md5.js"></script> 
    <script type="text/javascript" src="${ctx }/web/share/files/rem2.js"></script>
    <style>
        /* 设置滚动条的样式 */
        ::-webkit-scrollbar {
            width: 12px;
        }
        /* 滚动槽 */
        ::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
            border-radius: 10px;
        }
        /* 滚动条滑块 */
        ::-webkit-scrollbar-thumb {
            border-radius: 10px;
            background: rgba(0,0,0,0.1);
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);
        }
        ::-webkit-scrollbar-thumb:window-inactive {
            background: rgba(255,0,0,0.4);
        }

        #denglu *{
            margin-left: 0rem;
            margin-right: 0rem;
            vertical-align:middle;  /* 居中对齐， */
            font-size:12px;
        }
        .user-head3{
            width: 1rem;
            height: 1rem;
            border-radius:0.2rem;
            margin-right: -2rem;
            margin-left: -2rem;
            border-radius: 50%;
        }

    </style>
</head>
<!-- /Head -->
<!-- Body -->
<body>

<div class="all-container">
    <div class="icon-bg">
        <div class="roomid-box">
            <p class="roomid-font">房间号:<span class="room-id">${roomid}</span></p>
            <p>${roomtitle}-${jushu}局</p>
        </div>
        <div class="room-play-box" style="overflow-y: auto;">

        </div>
    </div>
    <p class="room-font">房间成员</p>
    <div class="user-head">
    </div>
    <div class="username-div">
    </div>
    <!-- 用户未授权过 -->
   <a href="${url}" class="sJoinRoom" style="display: none;" id="authJoinRoom">
         <div class="joinRoom">加入房间</div>
    </a> 
   <a href="#" class="sJoinRoom" style="display: none;" id="ajaxJoinRoom">
         <div class="joinRoom">加入房间</div>
    </a>
   <div class="joinRoom okJoinRoom" style="background-color: #21dd7c;display: none;">已加入请打开app游戏……</div>
    <div class="openApp-android" style="">
        <p>加入成功</p>
        <p>手动打开游戏就能进入房间啦</p>
    </div>

    <div id="denglu" style="display: none;">
    　　<span>当前账号:</span>
        <img class="user-head3" id="nowUserIcon">
        <span id="nowUserMid"></span>
　　</div>

    <div class="joinRoom-loading" style="display:none">
        <div class="joinRoom-loading-bg"></div>
        <br>
        <span class="joinRoom-loading-font">正在加入房间</span>
    </div>
    <div class="join-fail" style="display:none">
        <p class="join-fail-font" style="display:none">加入失败</p>
        <!--在其他房间 加入失败-->
        <p class="join-fail-font1 join-fail-exit" style="display:none">你还在其它房间里呢<br>先把那边退了吧</p>
        <!--房间已满-->
        <p class="join-fail-font1 join-fail-roomFull" style="display:none">你来晚了，位置都被被人抢了</p>
        <!--房间解散了-->
        <p class="join-fail-font1 join-fail-dissolve" style="display:none">你来晚了，房间都解散了</p>
        <!--账号被封-->
        <p class="join-fail-font1 join-fail-close" style="display:none">你的账号已被查封<br>请联系公众号“地道游戏”</p>
        <!--其他错误-->
        <p class="join-fail-font1 join-fail-other" style="display:none">请稍候再试</p>
    </div>
</div>
<script>
    function setCookie(cname, cvalue, exdays){
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }

    //获取浏览器中的Cookie
    function getCookie(cname){
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return false;
    }

    //删除cookies
    function delCookie(name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval=getCookie(name);
        if(cval!=null)
            document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }


    var wanfas = '';
    var list = '${wfList}';
    if (list != undefined && list != null && list != '') {
        list = list.split("_");
        for (var i = 0; i < list.length; i++) {
            wanfas += '<p class="room-play"><span>'+list[i]+'</span></p>';
        }
        $('.room-play-box').html(wanfas);
    }

    function authJoinRoomResult() {
        var data = '${joinRoomResult}' == '' ? '' : JSON.parse('${joinRoomResult}');
        if (data) {
            $('.joinRoom-loading').hide();
            $("#authJoinRoom").hide();
            $("#ajaxJoinRoom").hide();

            if(data.success){
                $(".okJoinRoom").show();
                $('.sJoinRoom').hide();
            }else {
                var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">'+data.message+'</p>';
                $('.join-fail').show().html(a);
                failHide();
            }
        }
    }

	function initRoomInfo(nowUserMid,roomInfos) {
        var roomInfo='${seats}';
        if (roomInfos != null) {
            roomInfo = roomInfos;
        }
        var listHead="";
        var listName="";

        if (roomInfo) {
            roomInfo=JSON.parse(roomInfo);
            var ii = true;
            for (var i = 0; i < roomInfo.length; i++) {
                if (roomInfo[i].mid == nowUserMid) {
                    $(".okJoinRoom").show();
                    $('#authJoinRoom').hide();
                    $('#ajaxJoinRoom').hide();
                    ii = false;
                }else {
                    if (ii && nowUserMid != null) {
                        $('#ajaxJoinRoom').show();
                    }else if (ii && nowUserMid == null) {
                        $('#authJoinRoom').show();
                    }
                }
                listHead +="<div class='user-head2'><img src='"+roomInfo[i].icon+"'></div>";
                listName +="  <p class='username'>"+roomInfo[i].name+"</p>"
            }
            if(roomInfo.length <4) {
                for (var i = 0; i < 4-roomInfo.length; i++) {
                    listHead +='<div class="user-head1"><span>等待<br>加入</span></div>';
                }
            }
            $('.user-head').html(listHead);
            $('.username-div').html(listName);
            authJoinRoomResult();
        }
    }

    function failHide() {
        $('.room-font').hide();
        $('.user-head').hide();
        $('.username-div').hide();
        $('.icon-bg').hide();
        $('.sJoinRoom').hide();
        $('.okJoinRoom').hide();
    }

    function successShow() {
        $('.room-font').show();
        $('.user-head').show();
        $('.username-div').show();
        $('.icon-bg').show();
        $('.sJoinRoom').show();
        $('.okJoinRoom').show();
    }

    var state = '${state}';
    var code = getCookie("code");
    if (code == false) {
        if ('${code}' != ''){
            delCookie("code");
            setCookie("code",'${code}',15);
            code = getCookie("code");
            getUserInfo(code);
        }else {
            initRoomInfo(null,null);
        }
    }else if ('${code}' != '' && code != '${code}'){
        delCookie("code");
        getUserInfo('${code}');
    } else {
        getUserInfo(code);
    }

    $(function () {
        $('#ajaxJoinRoom').on('click', function () {
            $.ajax({
                type : "POST",
                url : "${ctx}/api/shareLink/cookieJoinRoom.html",
                data : {
                    code : code,
                    state : state
                },
                dataType : "json",
                success : function(resultdata) {
                    if (resultdata.success == false) {
                        $('#ajaxJoinRoom').hide();
                        $('#authJoinRoom').show();
                    }else {
                        $('#ajaxJoinRoom').hide();
                        $(".okJoinRoom").show();
                        window.location.reload();
                        initRoomInfo(resultdata.nowUserMid,resultdata.roomInfo);
                    }
                },
                error : function(errorMsg) {
                    $('#ajaxJoinRoom').hide();
                    $('#authJoinRoom').show();
                }
            });
        });
    });

    function getUserInfo(code) {
        var nowUserIcon = "";
        var nowUserName = "";
        var nowUserMid = "";
        $.ajax({
            type : "POST",
            url : "${ctx}/api/shareLink/cookieJoinRoom.html",
            data : {
                code : code
                //roomid : $('.room-id').text()
            },
            dataType : "json",
            success : function(resultdata) {
                if (resultdata.success == true) {
                    nowUserIcon = resultdata.nowUserIcon;
                    nowUserName = resultdata.nowUserName;
                    nowUserMid = resultdata.nowUserMid;
                    $("#nowUserIcon").attr('src',nowUserIcon);
                    $('#nowUserMid').text(nowUserMid);
                    $('#denglu').css('display', 'block');
                    initRoomInfo(nowUserMid,null);
                }else {
                    initRoomInfo(null,null);
                }
            },
            error : function(errorMsg) {
                initRoomInfo(null,null);
            }
        });
    }

</script>
<!--  /Body -->
</body>
</html>