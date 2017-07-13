define(['app',"layer"],function(app){
    app.controller('ctrl.address',function($scope,$location,$http,$myService,$timeout,$state){
    	$(".qs_ul li").children("a").removeClass("cli_hover");
        $(".qs_ul li").children("a").eq(4).addClass("cli_hover");
    	var hash =location.href;
        var url=hash.substring(0,hash.indexOf("/index.html")); 
        $scope.onEdit=function (){
        	$(".qs_address_ul").find('input').removeAttr("disabled");
        }
        
        var parma={
        		"sesskey":sessionStorage.sesskey,
        		"signCode":$myService.getDate()};
        parma.sign=$myService.sortObjectKeys(parma);
        var hash = $location.hash();
        //用户信息查询
        $.post(url+"/api/activity/getAddressInfo.do", parma,
        		function(data){
        	var result=JSON.parse(data);
        	if (result.svflag==1){
        		$timeout(function(){  
        			$scope.data=JSON.parse(data).data;
        			if ($scope.data==null){
        				 $scope.onEdit();
        			}
        		},100);
        	}else {
        		console.log(data);
        		window.datas=result;
        		$state.go('errormsg');
        	}
        });
        
        //修改用户地址信息
        $scope.updateAddress=function (ids){
        	var id = $("#userid").val();
        	var name = $("#name").val();
        	var qq = $("#qq").val();
//        	var wechat = $("#wechat").val();
//        	var email = $("#email").val();
        	var phone = $("#phone").val();
        	var address = $("#address").val();
        	//if (!id){/*layer.msg("ID为空！", { icon : 5,time : 800 });*/return }
        	if (!name){layer.msg("姓名不能为空！", { icon : 5,time : 1000 });return }
        	if (!qq){layer.msg("QQ不能为空！", { icon : 5,time : 1000 });return }
//        	if (!wechat){layer.msg("微信不能为空！", { icon : 5,time : 1000 });return }
//        	if (!email){layer.msg("邮箱不能为空！", { icon : 5,time : 1000 });return }
        	if (!phone){layer.msg("手机号不能为空！", { icon : 5,time : 1000 });return }
        	if (!address){layer.msg("地址不能为空！", { icon : 5,time : 1000 }); return
        	}
        	var re = /^((13|15|18|14|17)+\d{9})$/;
        	if (!re.test($.trim(phone))) {
        		layer.msg("请输入正确格式手机号！", { icon : 5,time : 1000 });
        		return
        	} else {
        	}
        	
        	var par={
        			"sesskey":sessionStorage.sesskey,
            		"signCode":$myService.getDate(),
        			"id":id,
        			"name":name,
        			"qq":qq,
//        			"wechat":wechat,
//        			"email":email,
        			"phone":phone,
        			"address":address
        	}
        	par.sign=$myService.sortObjectKeys(par);
        	$.post(url+"/api/activity/updateAddress.do", par,
            		function(data){
        		var result=JSON.parse(data);
            	if (result.svflag==1){
            		
	        		$scope.data=JSON.parse(data).data;
	        			layer.msg($scope.data.message, {
	        				icon : 6,
	        				time : 1000
	        			});
	        		$(".qs_address_ul").find('input').attr("disabled","disabled");
	        		$timeout(function(){
	        			$state.go('index');
	        		},1200);
	            	}else {
	            		console.log(data);
	            		window.datas=result;
	            		$state.go('errormsg');
	            	}
            });
        	
        	
        }
        
    });
});