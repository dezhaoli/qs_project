define(['app',],function(app){
    app.controller('ctrl.converted',function($scope,$http,$myService,$timeout){
    	
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
        		$timeout(function(){  
        				$scope.data=JSON.parse(data).data;
        		},100);
        	}
        });
        
    });
});