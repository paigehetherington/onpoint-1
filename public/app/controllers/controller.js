angular
  .module('onpoint')
  .controller("MainCtrl", function($scope, $location, onPointService) {

      $scope.volunteers = [];

      $scope.loginUser = function(user) {
          onPointService.login(user)
          .success(function(data) {
            console.log("USER LOGGED IN ", data);
              $location.path('/');
          });
      };

      $scope.scrollTo = function(image,ind) {
        $scope.listposition = {left:(IMAGE_WIDTH * ind * -1) + "px"};
        $scope.selected = image;
      };

      $scope.createVolunteer = function(volunteer) {
              // alert("I am not working");
              console.log("IS VOLUNTEER SENDING", volunteer)
              onPointService.create(volunteer)
                .success(function(res) {
                  console.log(res);
                  $scope.volunteers.push(volunteer);
                  $scope.volunteer = "";
              })


      }; //end of createVolunteer

      // $scope.createAccount = function(newUser){
      //   console.log("Dude where's my data?", newUser)
      //   OnpointService.createAccount(newUser)
      //   .success(function(res){
      //     console.log(res);
      //   })
      // };
// hello
      onPointService.getVolunteer()
      .then(function(vols) {
      console.log('volunteers', vols.data);
          $scope.volunteers = vols.data;

          })
      })
