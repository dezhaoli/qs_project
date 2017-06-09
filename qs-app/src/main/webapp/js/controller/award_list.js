define(['app'],function(app){
    app.controller('ctrl.index',function($scope,$location,$http,$myService){
        var vm = $scope.vm = {};
        vm.title = '奖品兑换';
        var v=$location.absUrl() ;
        $scope.sesskey=v.substring(v.indexOf("=")+1,v.indexOf("#"));
        var parma={
	        		"sesskey":"52172-1496813525836-102-4503765942882aab22101a762ff454c8-0-6",
	        		"signCode":$myService.getDate(),
	        		"code":666666};
        parma.sign=$myService.sortObjectKeys(parma);
        $http({  
            method:'post',   url:'http://127.0.0.1:8080/app/js/controller/data.json',   data:{name:"aaa",id:1,age:20}  
        		}).success(function(data){  
	            console.log(data);  
	            $scope.data= data.award;
        });  
        
    });
});