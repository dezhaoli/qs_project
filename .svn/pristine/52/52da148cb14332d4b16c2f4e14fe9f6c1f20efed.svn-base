define(['app',"layer"],function(app){
    app.controller('ctrl.index',function($scope,$location,$http,$myService,$state,$timeout,$window,$state ){
    	
    	var hash =location.href;
        var url=hash.substring(0,hash.indexOf("/index.html")); 
        $(".qs_ul li").children("a").removeClass("cli_hover");
        $(".qs_ul li").children("a").eq(0).addClass("cli_hover");
        
        var parma={
        		"sesskey":sessionStorage.sesskey,
        		"signCode":$myService.getDate()};
        parma.sign=$myService.sortObjectKeys(parma);
        //用户信息查询
        $scope.getuserinfo=function (){
        	$.post(url+"/api/actiIntegral/getRankingByUserMid.do", parma,
        			function(data){
                	var result=JSON.parse(data);
                	if (result.svflag==1){
                		$timeout(function(){  
            				$scope.user=JSON.parse(data).data;
                		},100);
                	}else {
                		console.log(data);
                		window.datas=result;
                		$state.go('errormsg');
                	}
        		
        	});
        }
      //用户信息查询
        $scope.getlist=function (){
        	
        	$.post(url+"/api/actiAward/getCommodityList.do", parma,
        			function(data){
        		var result=JSON.parse(data);
            	if (result.svflag==1){
        			$timeout(function(){  
        					$scope.data=JSON.parse(data).data;
        			},500);
            	}else {
            		console.log(data);
            		window.datas=result;
            		$state.go('errormsg');
            	}
        	});
        }
        $scope.getuserinfo();
        $scope.getlist();
        $scope.exchange=function (id){
        	var type=0;
        	 var par={
        			"id":id,
        			"number":1,
             		"sesskey":sessionStorage.sesskey,
             		"signCode":$myService.getDate()
             		};
        	 par.sign=$myService.sortObjectKeys(par);
        	$.post(url+"/api/actiAwardRecord/exchangePrizes.do", par,
            		function(data){
        		
        		var result=JSON.parse(data);
            	if (result.svflag==1){
            		$timeout(function(){  
            				$scope.datas=JSON.parse(data).data;
            				if($scope.datas==-104) {
            					layer.msg("请完善地址信息！", { icon : 5, time : 500 });
            					setTimeout(function (){$state.go("address");},500);
            					return;
            				}
            				if ($scope.datas==1){
            					type=1;
            					layer.msg("兑换成功！", { icon : 6, time : 500 });
            					setTimeout(function (){/*$state.go("index");*/ $window.location.reload();},500);
            					return;
            				}
            		},500);
            	}else {
            		console.log(data);
            		window.datas=result;
            		$state.go('errormsg');
            	}
            });
        }
        
    });
});