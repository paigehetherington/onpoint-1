angular
.module('onpoint')
.controller("MainCtrl", function ($scope, onPointService) {
  $scope.loginUser = function (user) {
    onPointService.login(user);
  };
  //$scope.test = function () {
  //  onPointService.runMe();
  //}
$scope.createVolunteer = function (volunteer){
  // alert("I am not working");
  console.log("IS VOLUNTEER SENDING", volunteer)
  onPointService.create(volunteer)
  .success(function(res){
    console.log(res);
  });
}
});
