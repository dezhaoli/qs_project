define(['app'],function(app){
	
    app.controller('ctrl.ranking',function($scope,$http,$myService,$location){
    	
//    	$(".qs_phb_body").width($(window).width()-100);
//    	$(".qs_phb_body").height($(window).width());
       /* var vm = $scope.vm = {};
       $("h1").text("hello");
       var http=$http;
       vm.title = '排行榜';
       var date=$myService.getDate();
       $http({  
           method:'post',   url:'http://127.0.0.1:8080/app/js/controller/data.json',   data:{name:"aaa",id:1,age:20}  
       		}).success(function(data){  
	            console.log(data);  
	            $scope.data= data.ranking;
       }); */ 
    	
    	  var parma={
          		"sesskey":"52159-1497001008469-101-66060edf720a1b44e3aef04c5afcd2bb-0-6",
          		"signCode":$myService.getDate()};
          parma.sign=$myService.sortObjectKeys(parma);
          var hash =location.href;
          var url=hash.substring(0,hash.indexOf("/index.html")) 
          
          //用户信息查询67
          $.post(url+"/api/actiIntegral/rankingList.do", parma,
          		function(data){
        	  setInterval(function(){  
          		$scope.$apply(function(){  
          				$scope.data=JSON.parse(data).data.actiIntegralList;
          		});  
          	},100);
          });
       
    });
});