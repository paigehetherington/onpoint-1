var angular=require('onpoint');
var angularRoute = require('angular-route');


angular.module('onpoint',[ngRoute])
.config(['$routeProvider','$localProvider' function($routeProvider, $localProvider){
  $routeProvider.when('/', {controller: 'MainCtrl', templateUrl:'home.html'})
.when('/content',{
  templateUrl:'/app/templates/content.html',
  '/app/templates/volunteer.html',
    '/app/templates/contact.html',

})
  .otherwise({templateUrl:'/'});
  $localProvider.html5Mode(true);

  redirecTo: '404'
}]);
require('.controllers/controller');
require('.services/onpoint.service');
require('directives/directive')
