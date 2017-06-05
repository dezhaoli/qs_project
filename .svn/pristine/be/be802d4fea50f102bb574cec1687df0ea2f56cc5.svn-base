define(['app',],function(app){
	  app.service('$myService', function($http,$q) {
	        this.name = "service";
	        this.myFunc = function (x) {
	            return x.toString(16);//转16进制
	        }
	        this.getData = function(){
	            var d = $q.defer();
	            $http.get("ursl")//读取数据的函数。
	                .success(function(response) {
	                d.resolve(response);
	            })
	                .error(function(){
	                alert(0)
	                d.reject("error");
	            });
	            return d.promise;
	        }
	        
	        //获取当前数据10位数
	        this.getDate=  function timest() {
	     	   var tmp = Date.parse( new Date() ).toString();
	    	   tmp = tmp.substr(0,10);
	    	   return tmp;
	    	 }
	    });
});