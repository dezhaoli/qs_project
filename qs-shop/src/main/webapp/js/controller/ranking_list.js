define(['app',"bootstrap","jquery"],function(app){
	
    app.controller('ctrl.ranking',function($scope,$http){
    	
//    	$(".qs_phb_body").width($(window).width()-100);
//    	$(".qs_phb_body").height($(window).width());
        var vm = $scope.vm = {};
       $("h1").text("hello");
       var http=$http;
       vm.title = '我擦';
    });
});