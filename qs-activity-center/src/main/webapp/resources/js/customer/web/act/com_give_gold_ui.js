/**
 * Created by QS on 2017/5/22.
 */
function adjust(obj){
    var header = document.getElementById("header");
    var content = document.getElementById("content");
    var footer = document.getElementById("footer");
    var h  = document.body.clientHeight;
    var hh = h * 1/15;
    var ch = h * 13/15;
    var fh = h * 1/15;
    header.style.height = hh;
    content.style.height = ch;
    footer.style.height = fh;
    /* var w  = document.body.clientWidth;
     header.style.width = w/15;
     content.style.width = w * 13/15;
     footer.style.width = w/15*/

    $('#headerRow').css('padding-top',((hh) - $('#h1Height').height()) * (1/2))
        .parent().css('text-align','center').css('background-color','#f5f5f5');
    $('#footerRow').css('padding-top',((fh) - $('#footerH1Height').height()) * (1/2))
        .parent().css('text-align','center').css('background-color','#f5f5f5');

}
window.onload=function(){
    window.onresize = adjust;
    adjust();
};

// return handleInitResponse.execute(value);

var handleInitResponse = {
    execute:function (data) {
        return eval('handleInitResponse.handle' + data + "()");
    },
    handle0:function () {//
        return "评论送金币";
    },
    handle1:function () {//
        return "充值购买";
    },
    handle2:function () {//
        return "每日登陆";
    },
    handle3:function () {//
        return "VIP每日登陆";
    },
    handle4:function () {//
        return "生成房间消耗";
    },
    handle5:function () {//
        return "退还房间费用";
    },
    handle6:function () {//
        return "绑定奖励";
    },
    handle7:function () {//
        return "后台添加";
    },
    handle8:function () {//
        return "初始赠送";
    },
    handle9:function () {//
        return "比赛金币消耗";
    },
    handle10:function () {//
        return "比赛金币退还";
    },
    handle11:function () {//
        return "比赛金币奖励";
    },
    handle12:function () {//
        return "开服充值赠送";
    },
    handle13:function () {//
        return "商务添加";
    },
    handle14:function () {//
        return "充值大特惠";
    },
    handle15:function () {//
        return "更新补偿";
    },
    handle16:function () {//
        return "邮件赠送";
    },
    handle17:function () {//
        return "成就奖励";
    }
};