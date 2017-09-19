define(['app'],function(app){
	
    app.controller('ctrl.rankingTen',function($scope,$http,$myService,$location,$timeout,$state){
    	$(".qs_ul li").children("a").removeClass("cli_hover");
        $(".qs_ul li").children("a").eq(0).addClass("cli_hover");
    	  var parma={
          		"sesskey":sessionStorage.sesskey,
          		"signCode":$myService.getDate()};
          parma.sign=$myService.sortObjectKeys(parma);
          var hash =location.href;
          var url=hash.substring(0,hash.indexOf("/index.html")) 
          
          //用户信息查询67
          $.post(url+"/api/actiIntegral/rankingList.do", parma,
          		function(data){
        	  
        	  var result=JSON.parse(data);
          	if (result.svflag==1 || result.svflag==1001){
	        	  $timeout(function(){  
	          				$scope.data=JSON.parse(data).data.actiIntegralList;
	          	},100);
          	}else {
        		console.log(data);
        		window.datas=result;
        		$state.go('errormsg');
        	}
          });
       
    });
});