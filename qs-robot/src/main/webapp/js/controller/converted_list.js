define(['app',],function(app){
    app.controller('ctrl.converted',function($scope,$http,$myService,$timeout,$state){
    	$(".qs_ul li").children("a").removeClass("cli_hover");
        $(".qs_ul li").children("a").eq(2).addClass("cli_hover");
    	var hash =location.href;
        var url=hash.substring(0,hash.indexOf("/index.html")); 
        
        var parma={
        		"sesskey":sessionStorage.sesskey,
        		"signCode":$myService.getDate()};
        parma.sign=$myService.sortObjectKeys(parma);
        //用户信息查询
        $.post(url+"/api/actiAwardRecord/getConvertedInfo.do", parma,
        		function(data){
        	var result=JSON.parse(data);
        	if (result.svflag==1){
        		$timeout(function(){  
        				$scope.data=JSON.parse(data).data;
        				if ($scope.data.length==0){
        					$("#notData").show();
        					$("#yesData").hide();
        				}
        		},100);
        	}else {
        		console.log(data);
        		window.datas=result;
        		$state.go('errormsg');
        	}
        });
        
    });
});