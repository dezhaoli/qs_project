define(['app',],function(app){
    app.controller('ctrl.converted',function($scope,$http,$myService){
        /*var vm = $scope.vm = {};
        vm.title = '已兑换';
        
        $http({  
            method:'post',   url:'http://127.0.0.1:8080/app/js/controller/data.json',   data:{name:"aaa",id:1,age:20}  
        		}).success(function(data){  
 	            console.log(data);  
 	            $scope.data= data.converted;
        });*/  
    	
    	var hash =location.href;
        var url=hash.substring(0,hash.indexOf("/index.html")); 
        
        var parma={
        		"sesskey":sessionStorage.sesskey,
        		"signCode":$myService.getDate()};
        parma.sign=$myService.sortObjectKeys(parma);
        //用户信息查询
        $.post(url+"/api/actiAwardRecord/getConvertedInfo.do", parma,
        		function(data){
        	if (data.svflag!=3){
        		setInterval(function(){  
        			$scope.$apply(function(){  
        				$scope.data=JSON.parse(data).data;
        			});  
        		},100);
        	}
        });
        
    });
});