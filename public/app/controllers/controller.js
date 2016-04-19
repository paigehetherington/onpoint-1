angular
  .module('onpoint')
  .controller("NavBarCtrl", function ($scope, $window, $location,onPointService) {
    var loadUserData = function () {
      var getToken = JSON.parse($window.sessionStorage.getItem('token'));
      if (getToken) {
        console.log('this is triggered bc login', getToken)
        $scope.userAuthenticated = true;
        $scope.userName = getToken.data.username;
        $scope.userData = getToken.data;
          console.log(getToken.data);
      } else {


      }
    }
    $scope.$on('user:loggedin', loadUserData);
    loadUserData();

    $scope.logout = function() {
      console.log("IS THIS HAPPENING!?");
      onPointService.logout()
        .then(function(data) {
          $window.sessionStorage.removeItem('token');
          $scope.userAuthenticated = false;
          $scope.userData = null;
          $location.path('/');
        }).catch(function(e) {
        console.error('An error occured logging out:', e);
      });
    }

  })

  .controller("MainCtrl", function($scope, $location, $window,
                                   onPointService, $rootScope) {


    $scope.volunteers = [];
    $scope.testing = 'hello';

    $scope.loginUser = function(user) {
      onPointService.login(user)

        .then(function(data) {
          var stringifyResponse = JSON.stringify(data);
          $window.sessionStorage.setItem('token', stringifyResponse);
          $rootScope.$broadcast('user:loggedin');
          $location.path('/');
        }).catch(function(e) {
        console.error('An error occured logging in:', e);
      });
    };




    $scope.scrollTo = function(image, ind) {
      $scope.listposition = { left: (IMAGE_WIDTH * ind * -1) + "px" };
      $scope.selected = image;
    };

    $scope.createVolunteer = function(volunteer) {
      console.log("IS VOLUNTEER SENDING", volunteer)
      onPointService.create(volunteer)
        .success(function(res) {
          console.log(res);
          $scope.volunteers.push(res);
          $scope.volunteer = "";

        })


    }; //end of createVolunteer


$scope.postComment = function(newComment, volunteer) {

  var thingToSend = {
    text: newComment.text,
    volunteerId: volunteer.id
  }

  console.log("THING TO SEND", thingToSend)

  onPointService.createComment(thingToSend)
  .then(function(res) {
    console.log("SUCCESS", res)
  })
  .catch(function(err) {
    console.log("ERROR", err);
  })

  volunteer.comments.push(newComment);
  volunteer.comments="";

};

$scope.edit = function(volunteer) {
  console.log("EDITING",volunteer)
  onPointService.editVol(volunteer)
  .then(function(editVol) {
    console.log("SUCCCESS EDIT",editVol);
    $scope.thisIndex = false;
  })
};

  $scope.createAccount = function(newUser) {
      console.log("Dude where's my data?", newUser)
      onPointService.createAccount(newUser)
        .then(function(createAcct) {
          $location.path('/login');
        }).catch(function(e) {
          $scope.errorMessage = e.data.message;
        console.error('Error Occured', e);
      })
    };


    $scope.showEdit = function(index) {
      $scope.thisIndex = index;
    }
    $scope.cancelEdit = function(){
      $scope.thisIndex = false;
    }

    $scope.delete = function(vol) {
      onPointService.deleteVol(vol)
      .then(function(data) {
        console.log("DELETED", data);
      })
        $scope.volunteers.pop(vol);
    }
// hello
    onPointService.getVolunteer()
      .then(function(vols) {
        console.log('volunteers', vols.data);
        $scope.volunteers = vols.data;
        $scope.volunteers.forEach(function(el) {
          el.comments = [];

        })
        onPointService.getComments()
        .then(function (data) {
          console.log("Comments", data);
        });
        $scope.isloggedin = window.sessionStorage.token
        console.log(window.sessionStorage.token);

      });

  });
