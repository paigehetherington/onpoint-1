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
    .when('/service-org',{
      templateUrl:'/app/templates/serviceorg.html',
      controller: 'MainCtrl'
    })
    .when('/volunteer-profile',{
      templateUrl:'/app/templates/volunteer.html',
      controller: 'MainCtrl'
    })
    .when('/about',{
      templateUrl:'/app/templates/about.html',
      controller: 'MainCtrl'
    })
    .when('/photo-gallery',{
      templateUrl:'/app/templates/photogallery.html',
      controller: 'MainCtrl'
    })
    .when('/press',{
      templateUrl:'/app/templates/press.html',
      controller: 'MainCtrl'
    })
    .when('/contact',{
      templateUrl:'/app/templates/contact.html',
      controller: 'MainCtrl'
    })
    .when('/logout',{
      templateUrl:'/app/templates/logout.html',
      controller: 'MainCtrl'
    })
    .when('/login',{
      templateUrl:'/app/templates/login.html',
      controller: 'MainCtrl'

    })
    .when('/register',{
      templateUrl:'/app/templates/register.html',
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
