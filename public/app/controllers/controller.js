angular
  .module('onpoint')

  .controller("MainCtrl", function($scope, $location, $window,
                                   onPointService, $rootScope) {

    $scope.editForm = 'hidden';
    $scope.volunteers = [];
    $scope.testing = 'hello';
    var loadUserData = function (){

    }
    var getToken = JSON.parse($window.sessionStorage.getItem('token'));
    if (getToken) {
      console.log(getToken)
      $scope.userAuthenticated = true;
      $rootScope.$broadcast
      $scope.userData = getToken.data;
    } else {
      //do some logic
    }
    $scope.$on('user:loggedin', loadUserData);
    loadUserData();
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

    $scope.delete = function(index, volunteer) {
      onPointService.deleteVol(volunteer)
      .then(function(data) {
        console.log("DELETED THIS");
      })
    }

    $scope.logout = function() {
      $window.sessionStorage.removeItem('token');
      $scope.userAuthenticated = false;
      $scope.userData = null;
      $location.path('/');
    };

    //     .success(function(data) {
    //       console.log("USER LOGGED IN ", data);
    //         $location.path('/');
    //     });
    // };

    $scope.scrollTo = function(image, ind) {
      $scope.listposition = { left: (IMAGE_WIDTH * ind * -1) + "px" };
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

$scope.showEdit = function($index) {
  $scope.thisIndex = $index;
}

$scope.postComment = function(newComment, volunteer) {
  if (!volunteer.comments) {
    volunteer.comments = [];
  }
  volunteer.comments.push(newComment);

  onPointService.createComment(newComment).then(function(){
    newComment.body = '';
    console.info('comment posted');
  })
};

  $scope.edit = function(volunteer){
    console.log(volunteer)
    onPointService.editVol(volunteer)
    .then(function(editVol){
      console.log("editresponse", editVol);
    });
  };

  $scope.createAccount = function(newUser) {
      console.log("Dude where's my data?", newUser)
      onPointService.createAccount(newUser)
        .then(function(createAcct) {


          console.log(createAcct);
        }).catch(function(e) {
        console.error('Error Occured', e);
      })
    };

    // $scope.postComment = function(newComment) {
    //   console.dir(newComment);
    // }


// hello
    onPointService.getVolunteer()
      .then(function(vols) {
        console.log('volunteers', vols.data);
        $scope.volunteers = vols.data;

      });
  });
