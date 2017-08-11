'use strict';  
require.config({
    paths: {
    	'jquery': '../lib/jquery/jquery-2.1.4.min',
    	'bootstrap': '../lib/bootstrap/bootstrap.min',
        'angular': '../lib/angular-1.3.0.14/angular.min',
        'uiRoute' : '../lib/angular-1.3.0.14/angular-ui-router',
        'layer' : '../lib/layer-v2.3/layer',
        'routes' : 'routes',
        'md5' : '../lib/md5/jquery.md5'
    },
    waitSeconds:0,
    shim: {
    	'jquery' : {'exports' : 'jquery'},
    	'bootstrap' : {deps : [ 'jquery' ],  'exports' : 'bootstrap'},
        'angular' : {'exports' : 'angular'},
        'uiRoute' : {
          deps : ['angular']
        },
        'md5' : {deps : [ 'jquery' ],'exports' : 'md5'},
        'layer' : {'exports' : 'layer'}
    },
    priority: [
        "angular"
    ],
    urlArgs: "bust=" + (new Date()).getTime()
});

require([
         'angular',
         'app',
         'service',
         'routes',
         'uiRoute',
         'jquery'
], function (angular) {
    angular.bootstrap(document, ['app']);
});
