angular
  .module('onpoint')
  .controller("MainCtrl", function($scope, $location, $window,
    onPointService) {

      $scope.volunteers = [];
      $scope.testing = 'hello';
      var getToken = JSON.parse($window.sessionStorage.getItem('token'));
      if(getToken) {
        console.log(getToken)
        $scope.userAuthenticated = true;
        $scope.userData = getToken.data;
      } else {
        //do some logic
      }
      $scope.loginUser = function(user) {
          onPointService.login(user)
          .then(function(data) {
            var stringifyResponse = JSON.stringify(data);
            $window.sessionStorage.setItem('token', stringifyResponse);
            $scope.userAuthenticated = true;
            var getToken = JSON.parse($window.sessionStorage.getItem('token'));
            $scope.userData = getToken.data;
            $location.path('/');
          }).catch(function(e) {
            console.error('An error occured logging in:', e);
          });
      };

      $scope.logout = function() {
        $window.sessionStorage.removeItem('token');
        $scope.userAuthenticated = false;
        $scope.userData = null;
        $location.path('/');
      }

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
