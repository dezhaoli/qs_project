require.config({
    paths: {
    	'jquery': '../lib/jquery/jquery-2.1.4.min',
    	'bootstrap': '../lib/bootstrap/bootstrap.min',
        'angular': '../lib/angular-1.3.0.14/angular.min',
        'uiRoute' : '../lib/angular-1.3.0.14/angular-ui-router',
        'routes' : 'routes'
    },
    waitSeconds:0,
    shim: {
    	'jquery' : {'exports' : 'jquery'},
    	'bootstrap' : {deps : [ 'jquery' ],  'exports' : 'bootstrap'},
        'angular' : {'exports' : 'angular'},
        'uiRoute' : {
          deps : ['angular'],
          deps:['bootstrap']
        }
    },
    priority: [
        "angular"
    ],
    urlArgs: "bust=" + (new Date()).getTime()
});

require([
         'angular',
         'app',
         'routes',
         'uiRoute'
], function (angular) {
    angular.bootstrap(document, ['app']);
});
