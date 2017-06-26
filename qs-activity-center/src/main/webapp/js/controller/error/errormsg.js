define(['app',"bootstrap","jquery"],function(app){
	
    app.controller('ctrl.errormsg',function($scope,$http,$stateParams){
    	$scope.data=window.datas;
    });
});