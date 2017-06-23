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
            				$scope.nowIntegral=JSON.parse(data).data.nowIntegral;
                		},100);
                	}else if (result.svflag==1001){
                		$scope.nowIntegral=0;	
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
        
        
     
        
        $scope.exchange=function (id,name){
        	   layer.confirm('确定要兑换'+name+'？', {
                   icon : 3,
                   title : '兑换提示'
               }, function(index, layero) {
            	layer.close(index);
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
              		if(result.svflag==1002) {
              			layer.msg("请完善地址信息！", { icon : 5, time : 1000 });
              			setTimeout(function (){$state.go("address");},1200);
              			return;
              		}
                  	if (result.svflag==1){
                  		$timeout(function(){  
                  				$scope.datas=JSON.parse(data).data;
                  				if ($scope.datas==1){
                  					type=1;
                  					layer.msg("兑换成功！", { icon : 6, time : 1000 });
                  					setTimeout(function (){/*$state.go("index");*/ $window.location.reload();},1000);
                  					return;
                  				}
                  		},500);
                  	}else if(result.svflag==1005 || result.svflag==1007){
                  		layer.msg("积分不足！", { icon : 5, time : 1000 });
                  	}else if(result.svflag==1006){
                  		layer.msg("当前暂无可用积分！", { icon : 5, time : 1000 });
                  	}else {
                  		console.log(data);
                  		window.datas=result;
                  		$state.go('errormsg');
                  	}
                  
                  });
            	   
           });
               
        }
        
    });
});