define(['app'], function (app) {
        app.config(['$stateProvider', '$urlRouterProvider', '$controllerProvider','$locationProvider',function($stateProvider, $urlRouterProvider, $controllerProvider,$locationProvider){
            app.controller = $controllerProvider.register;
            app.loadJs = function(js){
                return function($rootScope, $q){
                    var def = $q.defer(),deps=[];
                    angular.isArray(js) ? (deps = js) : deps.push(js);
                    require(deps,function(){
                        $rootScope.$apply(function(){
                            def.resolve();
                        });
                    });
                    return def.promise;
                };
            };
            $urlRouterProvider.otherwise('/index');
            $stateProvider.state('index',{
                url : '/index',
                templateUrl : 'web/award_list.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.index', 
                resolve:{
                    deps:app.loadJs('./controller/award_list')
                }
            }).state('rule',{
                url : '/rule',
                templateUrl : 'web/rule.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.rule',
                resolve:{
                    deps:app.loadJs('./controller/rule')
                }
            }).state('ranking',{
                url : '/ranking',
                templateUrl : 'web/ranking_list.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.ranking',
                resolve:{
                    deps:app.loadJs('./controller/ranking_list')
                }
            }).state('converted',{
                url : '/converted',
                templateUrl : 'web/converted_list.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.converted',
                resolve:{
                    deps:app.loadJs('./controller/converted_list')
                }
            }).state('address',{
                url : '/address',
                templateUrl : 'web/address.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.address',
                resolve:{
                    deps:app.loadJs('./controller/address')
                }
            }).state('errormsg',{
//            	params: {'data': ""},
                url : '/errormsg',
                templateUrl : 'web/error/errormsg.html?t=' + Math.floor(Date.now() / 1000),
                controller : 'ctrl.errormsg',
                resolve:{
                    deps:app.loadJs('./controller/error/errormsg')
                }
            });
            
           // console.log('web/converted_list.html?t=' + Math.floor(Date.now() / 1000));
//            $locationProvider.html5Mode(true);
        }]);
});

