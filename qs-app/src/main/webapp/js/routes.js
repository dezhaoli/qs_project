define(['app'], function (app) {
        app.config(function($stateProvider, $urlRouterProvider, $controllerProvider){
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
            }
            $urlRouterProvider.otherwise('/index');
            $stateProvider.state('index',{
                url : '/index',
                templateUrl : 'web/award_list.html',
                controller : 'ctrl.index', 
                resolve:{
                    deps:app.loadJs('./controller/award_list')
                }
            }).state('rule',{
                url : '/rule',
                templateUrl : 'web/rule.html',
                controller : 'ctrl.rule',
                resolve:{
                    deps:app.loadJs('./controller/rule')
                }
            }).state('ranking',{
                url : '/ranking',
                templateUrl : 'web/ranking_list.html',
                controller : 'ctrl.ranking',
                resolve:{
                    deps:app.loadJs('./controller/ranking_list')
                }
            }).state('converted',{
                url : '/converted',
                templateUrl : 'web/converted_list.html',
                controller : 'ctrl.converted',
                resolve:{
                    deps:app.loadJs('./controller/converted_list')
                }
            }).state('address',{
                url : '/address',
                templateUrl : 'web/address.html',
                controller : 'ctrl.address',
                resolve:{
                    deps:app.loadJs('./controller/address')
                }
            });
            
//            $locationProvider.html5Mode(true);
        });
});

