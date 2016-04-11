var angular = require('angular');
require('angular-route');


angular.module('onpoint',[
  'ngRoute'
])
.config(['$routeProvider', function($routeProvider){
  $routeProvider
    .when('/home', {
      controller: 'MainCtrl',
      templateUrl:'/app/templates/home.html'
    })
    .when('/', {
      controller: 'MainCtrl',
      templateUrl:'/app/templates/home.html'
    })
    .when('/serviceorg',{
      templateUrl:'/app/templates/serviceorg.html',
      controller: 'MainCtrl'

    })
    .when('/404', {
      template: '<h1>Page not found!</h1>'
    })
    .otherwise({ redirecTo: '/404' });


}]);
require('./controllers/controller');
require('./services/onpoint.service');
require('./directives/directive')
