<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" style="font-size: 75px;">
<!-- Head -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>加入房间</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/web/share/files/joinRoom.css?v=8">
<%--     <script src="${ctx }/web/share/files/mlink.min.js"></script> --%>
<%--     <script src="${ctx }/web/share/files/jweixin-1.0.0.js"></script> --%>
<%--     <script type="text/javascript" src="${ctx }/web/share/files/zepto.min.js"></script> --%>
    <script type="text/javascript" src="${ctx }/lib/jquery/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/lib/md5/jquery.md5.js"></script> 
    <script type="text/javascript" src="${ctx }/web/share/files/rem2.js"></script>
    <style>
        /* 设置滚动条的样式 */
        ::-webkit-scrollbar {
            width: 0px;
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

        hr{
            display: block;
            -webkit-margin-before: 0.2em;
            -webkit-margin-after: 0.2em;
            -webkit-margin-start: auto;
            -webkit-margin-end: auto;
            border-style: inset;
            border-width: 1px;

            height:1px;
            border:none;
            border-top:1px solid #f1e9e9;
        }
    </style>
</head>
<!-- /Head -->
<!-- Body -->
<body>

<div class="all-container">
    <div class="" id="bgimg">
        <div class="roomid-box">
            <%--<p class="roomid-font">房间号:<span class="room-id">${roomid}</span></p>
            <p>${roomtitle}-${jushu}局</p>--%>
        </div>
        <div class="room-play-box" style="overflow-y: auto;">

            <%--<p class="room-play"><span>满堂鸡</span></p>
            <p class="room-play"><span>连庄</span></p>--%>
        </div>
    </div>
    <div style="margin-bottom: 5px;padding-top: 1px;height: 1rem;font-size: 18px;" id="roomNum">
        <span style="margin-top: 0px;margin-left:-1.5rem;color: #64ba24;">
            <span class="room-id" id="roomType">

            </span>
            <span class="room-id" style="margin-left: 1rem;" id="roomTile">

            </span>
        </span>
    <div class="room-hr" style="display: none;" id="roomHr"></div>
    </div>

    <p class="room-font" style="margin-top: -5px;display: none;" id="roomMember">房间成员</p>
    <div class="user-head">
       <%-- <div class="user-head2"><img src="${ctx }/web/share/files/200.jpg"></div>
        <div class="user-head1"><span>等待<br>加入</span></div>
        <div class="user-head1"><span>等待<br>加入</span></div>
        <div class="user-head1"><span>等待<br>加入</span></div>--%>
    </div>
    <div class="username-div">
        <%--<p class="username">Zun</p>
        <p class="username"></p>
        <p class="username"></p>
        <p class="username"></p>--%>
    </div>
    <!-- 用户未授权过 -->
   <a href="${url}" class="sJoinRoom" style="display: none;" id="authJoinRoom">
         <div class="joinRoom">加入房间</div>
    </a> 
   <a href="#" class="sJoinRoom" style="display: none;" id="ajaxJoinRoom">
         <div class="joinRoom">加入房间</div>
    </a>
    <div class="joinRoom okJoinRoom" style="background-color: #64ba24;display: none;">
        <p style="margin: -11px;">加入成功</p>
        <p style="margin: -29px 0px;font-size: small;color: red;" id="iosOpenApp">手动打开游戏就能进入房间啦！</p>
    </div>
    <div class="openApp-android" style="">
        <p>加入成功</p>
        <p>手动打开游戏就能进入房间啦</p>
    </div>

    <div id="denglu" style="display: none;margin-top: -0.4rem;">
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
    <div class="yindao-img" style="display: none;" id="jroom"></div>
    <input id="rroomid" type="hidden" value="${roomid}"/>
</div>
<script>
    var gameType = '${gameType}';
    var downloadUrl = "";
    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    if (isiOS) {
        $('#roomHr').addClass('room-hr-ios').removeClass("room-hr");
        $('#iosOpenApp').text("点击打开游戏！");

        if (gameType== 6) {
            downloadUrl = "https://itunes.apple.com/cn/app/id1216981340?mt=8";
        }else if (gameType== 17) {
            downloadUrl = "https://itunes.apple.com/us/app/id1212725123";
        } else {
            downloadUrl = "http://downloads.jiaheyx.com/rundownload/";
        }
    }else {
        $('.joinRoom.okJoinRoom').css('background-color', 'white')
            .find('p').css('color','#64ba24').css('font-size','');

        if (gameType== 6) {
            downloadUrl = "http://downloads.jiaheyx.com/rundownload/";
        }else if (gameType== 17) {
            downloadUrl = "http://downloads.jiaheyx.com/rundownload/?type=kx";
        } else {
            downloadUrl = "http://downloads.jiaheyx.com/rundownload/";
        }
    }

    /*
     在cookie的名或值中不能使用分号（;）、逗号（,）、等号（=）以及空格。在cookie的名中做到这点很容易，但要保存的值是不确定的。如何
     来存储这些值呢？方法是用encodeURI()函数进行编码，它能将一些特殊符号使用十六进制表示，例如空格将会编码为“20%”，从而可以存储于
     cookie值中，而且使用此种方案还可以避免中文乱码的出现。在获取cookie时可以通过decodeURI()方法对cookie进行解码。

     Cookie的保存格式为："name=value; expires=evalue; path=pvalue;"
     每个属性之间通过：'分号+空格'(; )隔开;

     参数值的意思:
     name:表示要存入对象的名称，唯一必须设置（通常使用：user@domain格式命名，user为本地用户，domain为所访问网站的域名）。value：表示要存入得值。
     expires：该对象的有效时间（可选）（默认为当前浏览器会话有用，关闭浏览器就消失）;
     path:指定该Cookie作用范围（可选）(即：在那些网页上可用)；
     domain:设置web服务器的Cookie共享域（可选）(如：test*.com)表示域名为test*.com的服务器共享该Cookie
     secure：设置Cookie的传输过程是否加密（可选）（一般为SSH加密）
     */
    function setCookie(cname, cvalue, exdays){
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires;
    }
    /**
     获取浏览器中的Cookie时，只能够一次获取所有的cookie值，而不能指定cookie名称来获得指定的值，这正是处理cookie值最麻 烦的一部分。
     用户必须自己分析这个字符串，来获取指定的cookie值
     **/
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
            wanfas += '<p class="room-play" style="text-align: left;"><span>'+list[i]+'</span></p>';
        }
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
                setBeiJing();//设置背景图
            }else {
                if (data.message == "用户未注册") {
                    failHide();
                    var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">'
                        +'<a href="'+downloadUrl+'" style="text-decoration:none;" target="view_window">'
                        + data.message +'(点击前往下载注册)'+'</a>'
                        +'</p>';
                    $('.join-fail').show().html(a);
                }else if (undefined == data.message || null == data.message) {

                } else {
                    var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">' + data.message + '</p>';
                    $('.join-fail').show().html(a);
                    failHide();
                }
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
            setBeiJing();//设置背景图
            roomInfo=JSON.parse(roomInfo);
            var players = 4;
            var ii = true;

            var isNowUser = false;
            for (var i = 0; i < roomInfo.length; i++) {
                if(i == 0){
                    players = roomInfo[i].players;
                }
                if (roomInfo[i].mid == nowUserMid) {
                    isNowUser = true;
                    ii = false;
                }
            }
            if (!isNowUser && roomInfo.length == players) {
                var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">'+
                    '你来晚了，房间已满!'+'</p>';
                $('.join-fail').show().html(a);
                failHide();
                return;
            }

            for (var i = 0; i < roomInfo.length; i++) {
                if (!ii && roomInfo[i].mid == nowUserMid) {
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
                var fangzhu = "";
                var d = ""
                if (i == 0) {
                    $('#roomType').text(roomInfo[i].roomType);
                    fangzhu = "<div class='fangzhu-img'style='border-radius:50%;'>"
                        + "<img src='../../web/share/files/img/ui_fangzhu.png'>"
                    + "</div>";
                    d += "<div style='background-image:url("+roomInfo[i].icon+");" +
                        "background-repeat:no-repeat;width: 2rem;height:2rem;border-radius:50%;" +
                        "background-size: contain;'></div>";
                }else {
                    d += "<img src='"+roomInfo[i].icon+"'>" ;
                }
                listHead +="<div class='user-head2'>" +
                    fangzhu + d +
                    "</div>";
                listName +="  <p class='username'>"+roomInfo[i].name+"</p>"
            }
            if(roomInfo.length < players) {
                for (var i = 0; i < players - roomInfo.length; i++) {
                    listHead +='<div class="user-head1"><span>等待<br>加入</span></div>';
                }
            }
            if (players < 4) {
                for (var i = 0; i < 4 - players; i++) {
                    listHead +='<div class="user-head1" style="background: white;"><span>等待<br>加入</span></div>';
                    listName +="  <p class='username' style='color: white;'>name</p>"
                }
            }
            $('.user-head').html(listHead);
            $('.username-div').html(listName);
            authJoinRoomResult();
        }else {
            var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">'+
                '你才来啊，房间早解散啦!'+'</p>';
            $('.join-fail').show().html(a);
            failHide();
        }
    }

    function failHide() {
        $('.room-font').hide();
        $('.user-head').hide();
        $('.username-div').hide();
        $('.icon-bg').hide();
        $('.sJoinRoom').hide();
        $('.okJoinRoom').hide();
        $('#roomNum').hide();
        $('#bgimg').hide();
        $('#denglu').hide();
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

    function setBeiJing() {//设置背景图
        if (gameType == 6) {
            $('#bgimg').addClass('icon-bg-gdmajiang');
        }else if(gameType == 17) {
            $('#bgimg').addClass('icon-bg-kxpaohuzi');
        }else {

        }
        $('#roomHr').show();
        $('#roomMember').show();
        $('#roomTile').text('${roomtitle}${jushu}');
        $('.room-play-box').html(wanfas);
    }


    $(function () {
        $('#authJoinRoom').on('click', function () {
            $(this).addClass('disable-aLink');
        });

        $('#ajaxJoinRoom').on('click', function () {
            $(this).addClass('disable-aLink');
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
                        if (undefined != resultdata.message && null != resultdata.message) {
                            var a = '<p class="join-fail-font1 join-fail-dissolve" style="display:block;">'+resultdata.message+'</p>';
                            $('.join-fail').show().html(a);
                            failHide();
                        }
                    }else {
                        setBeiJing();//设置背景图
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
                code : code,
                rid : $('#rroomid').val()
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


    var browser = {
        versions: function() {
            var u = navigator.userAgent,
                app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1,                        /*IE内核*/
                presto: u.indexOf('Presto') > -1,          /*opera内核*/
                webKit: u.indexOf('AppleWebKit') > -1, /*苹果、谷歌内核*/
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,        /*火狐内核*/
                mobile: !!u.match(/AppleWebKit.*Mobile.*/),        /*是否为移动终端*/
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), /*ios终端*/
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, /*android终端或者uc浏览器*/
                iPhone: u.indexOf('iPhone') > -1,          /*是否为iPhone或者QQHD浏览器*/
                iPad: u.indexOf('iPad') > -1,      /*是否iPad*/
                webApp: u.indexOf('Safari') == -1,          /*是否web应该程序，没有头部与底部*/
                souyue: u.indexOf('souyue') > -1,
                superapp: u.indexOf('superapp') > -1,
                weixin:u.toLowerCase().indexOf('micromessenger') > -1,
                Safari:u.indexOf('Safari') > -1
            };
        }(),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    };

    if (browser.versions.weixin) {

    }else {
        if (browser.versions.android && !browser.versions.ios) {
            openApp();
        }
    }

    $('.joinRoom.okJoinRoom').on('click', function () {
        if (browser.versions.weixin && !browser.versions.ios) {
            //failHide();
            //$('#denglu').hide();
            //$('#jroom').show();
        }else {
            openApp();
        }
    });

    function openApp() {
        if (browser.versions.ios) {
            //window.location.href = "gdmajiang://com.lewan.gdmajiangnew";
            //window.location.href = "https://axfs9y.mlinks.cc/A0Ow";
            /*    setTimeout(function(){
                window.location.href = downloadUrl;
                window.location.href = downloadUrl;
            },4000)*/
        }else if (browser.versions.android){
            /*window.location.href = "qqsgame://com.lewan.GDmajiang";
            setTimeout(function(){
                window.location.href = downloadUrl;
            },4000)*/
        }
    }

</script>
<script src="https://static.mlinks.cc/scripts/dist/mlink.min.js"></script>
<script>
    if (browser.versions.ios) {
        if (gameType==6) {
            var options = {
                mlink:  "https://aaavzs.mlinks.cc/A0EN",
                button: $('.joinRoom.okJoinRoom'),
                autoLaunchApp: false,
            };
            new Mlink(options);
        }else if (gameType==17) {
            var options = {
                mlink:  "https://aaavzs.mlinks.cc/A0Ei",
                button: $('.joinRoom.okJoinRoom'),
                autoLaunchApp: false,
            };
            new Mlink(options);
        }
    }
</script>
<!--  /Body -->
</body>
</html>