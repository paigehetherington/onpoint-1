angular
  .module('onpoint')
  .controller("MainCtrl", function($scope, $location,
    onPointService) {
      $scope.loginUser = function(user) {
          onPointService.login(user);
      };
      $scope.login = function(){
        $location.path('/');
      }
      //$scope.test = function () {
      //  onPointService.runMe();
      //}
      $scope.scrollTo = function(image,ind) {
    $scope.listposition = {left:(IMAGE_WIDTH * ind * -1) + "px"};
    $scope.selected = image;
}
      $scope.formData = [];
      $scope.createVolunteer = function(volunteer) {
              // alert("I am not working");
              console.log("IS VOLUNTEER SENDING", volunteer)
              onPointService.create(volunteer)
                .success(function(res) {
                  console.log(res);
              })
              //$scope.formData.push(volunteer);
              //$scope.volunteer = "";
          } //end of createVolunteer

      onPointService.getVolunteer()
        .then(function(vols) {
          console.log('volunteers', vols.data);
              $scope.volunteers = vols.data;

          })
      })
