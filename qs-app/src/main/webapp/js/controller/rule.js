define(['app',"bootstrap","jquery"],function(app){
	
    app.controller('ctrl.rule',function($scope,$http){
        var vm = $scope.vm = {};
       $("h1").text("hello");
       var http=$http;
       vm.title = '我擦';
    });
});