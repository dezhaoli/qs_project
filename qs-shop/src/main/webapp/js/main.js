'use strict';  
require.config({
    paths: {
    	'jquery': '../lib/jquery/jquery-2.1.4.min',
    	'bootstrap': '../lib/bootstrap/bootstrap.min',
        'angular': '../lib/angular-1.3.0.14/angular.min',
        'uiRoute' : '../lib/angular-1.3.0.14/angular-ui-router',
        'routes' : 'routes',
        'md5' : '../lib/md5/jquery.md5'
    },
    waitSeconds:0,
    shim: {
    	'jquery' : {'exports' : 'jquery'},
    	'bootstrap' : {deps : [ 'jquery' ],  'exports' : 'bootstrap'},
        'angular' : {'exports' : 'angular'},
        'uiRoute' : {
          deps : ['angular'],
          deps:['bootstrap']
        },
        'md5' : {'exports' : 'md5'},
    },
    priority: [
        "angular"
    ],
    //urlArgs: "bust=" + (new Date()).getTime()
});

require([
         'angular',
         'app',
         "service",
         'routes',
         'uiRoute'
], function (angular) {
    angular.bootstrap(document, ['app']);
});
