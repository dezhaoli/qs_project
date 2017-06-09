define(['app',],function(app){
    app.controller('ctrl.converted',function($scope,$http){
        var vm = $scope.vm = {};
        vm.title = '已兑换';
        
        $http({  
            method:'post',   url:'http://127.0.0.1:8080/app/js/controller/data.json',   data:{name:"aaa",id:1,age:20}  
        		}).success(function(data){  
 	            console.log(data);  
 	            $scope.data= data.converted;
        });  
        
    });
});