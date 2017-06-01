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
                templateUrl : 'tpls/award_list.html',
                controller : 'ctrl.index', 
                resolve:{
                    deps:app.loadJs('./controller/award_list')
                }
            }).state('page',{
                url : '/page',
                templateUrl : 'tpls/page2.html',
                controller : 'ctrl.page',
                resolve:{
                    deps:app.loadJs('./controller/page')
                }
            }).state('converted',{
                url : '/converted',
                templateUrl : 'tpls/converted_list.html',
                controller : 'ctrl.converted',
                resolve:{
                    deps:app.loadJs('./controller/converted_list')
                }
            });;
            
            // $locationProvider.html5Mode(true);
        });
});

