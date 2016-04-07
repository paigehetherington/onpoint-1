angular.module('ongoing',[ngRoute])
.config(['$routeProvider','$localProvider' function($routeProvider, $localProvider){
  $routeProvider.when('/', {controller: 'MainCtrl', templateUrl:'home.html'})
.when('/content',{templateUrl:'/app/templates/content.html'})
  .otherwise({templateUrl:'/'});
  $localProvider.html5Mode(true);
}]);
