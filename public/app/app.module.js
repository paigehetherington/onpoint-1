var angular=require('onpoint');
var angularRoute = require('angular-route');


angular.module('onpoint',[ngRoute])
.config(['$routeProvider','$localProvider' function($routeProvider, $localProvider){
  $routeProvider.when('/', {controller: 'MainCtrl', templateUrl:'home.html'})
.when('/serviceorg',{
  templateUrl:'/app/templates/serviceorg.html',
  '/app/templates/volunteer.html',
  '/app/templates/login.html',
  '/app/templates/photogallery.html',
  '/app/templates/press.html',
  '/app/templates/about.html',
  '/app/templates/volunteerprofiles.html',


})
  .otherwise({templateUrl:'/'});
  $localProvider.html5Mode(true);

  redirecTo: '404'
}]);
require('.controllers/controller');
require('.services/onpoint.service');
require('directives/directive')
