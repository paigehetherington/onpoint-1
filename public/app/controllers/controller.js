 angular
  .module('onpoint')
  .controller("NavBarCtrl", function ($scope, $window, $location,onPointService, $rootScope) {
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
    console.log('------');
    console.log($scope.userName);
    $scope.logout = function() {
      console.log("IS THIS HAPPENING!?");
      onPointService.logout()
        .then(function(data) {
          $window.sessionStorage.removeItem('token');
          $scope.userAuthenticated = false;
          $scope.userData = null;
          $rootScope.user = null;
          $location.path('/logout');
        }).catch(function(e) {
        console.error('An error occured logging out:', e);
      });
    }

  })

  .controller("MainCtrl", function($scope, $location, $window,
                                   onPointService, $rootScope) {

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
     loadUserData();
    $scope.volunteers = [];
    $scope.testing = 'hello';
    $scope.formSent = false;
    if ($scope.user) {
      $scope.showTheForm = true;
    } else {
      $scope.showTheForm = false;
    }

    $scope.loginUser = function(user) {
      onPointService.login(user)

        .then(function(data) {
          var stringifyResponse = JSON.stringify(data);
          $rootScope.user = data;

          $window.sessionStorage.setItem('token', stringifyResponse);
          $rootScope.$broadcast('user:loggedin');
          $location.path('/');
        }).catch(function(e) {
          $scope.passwordIncorrect = true
      });
    };




    $scope.scrollTo = function(image, ind) {
      $scope.listposition = { left: (IMAGE_WIDTH * ind * -1) + "px" };
      $scope.selected = image;
    };

    $scope.showFormAgain = function () {
      //$scope.volunteer =

      $scope.formSent = false;
      $scope.showTheForm = true;
    }

    $scope.createVolunteer = function(volunteer) {
      $scope.showTheForm = false;
      console.log("IS VOLUNTEER SENDING", volunteer)
      onPointService.create(volunteer)
        .success(function(res) {
          console.log(res);
          res.comments = [];
          $scope.volunteers.push(res);
          $scope.volunteer = "";

        })
        $scope.formSent = true;

    }; //end of createVolunteer


$scope.postComment = function(newComment, volunteer) {

  var thingToSend = {
    text: newComment.text,
    volunteerId: volunteer.id
  }


  onPointService.createComment(thingToSend)
  .then(function(res) {
    console.log("WE CREATED A COMMENT", res);
    volunteer.comments.push(res.data);
    var commentBoxes = [].slice.call(document.getElementsByClassName('commentsBox'))
    commentBoxes.forEach(function(box) {
      box.value = ''
    });
  })
  .catch(function(err) {
  })


};

$scope.edit = function(volunteer) {
  onPointService.editVol(volunteer)
  .then(function(editVol) {
    $scope.thisIndex = false;
  })
};

  $scope.createAccount = function(newUser) {
      onPointService.createAccount(newUser)
        .then(function(createAcct) {
          $location.path('/login');
        }).catch(function(e) {
          $scope.errorMessage = e.data.message;
      })
    };


    $scope.showEdit = function(index) {
      $scope.thisIndex = index;
    }
    $scope.processForm = function(){
    }
    $scope.cancelEdit = function(){
      $scope.thisIndex = false;
    }
    $scope.showEditVol = function(){
      $scope.thisIndex = false;
    }
    $scope.showEditComments = function(commentToEdit){
      console.log("SHOW STUFF", commentToEdit)
      $scope.thisCommentIndex = commentToEdit;
    }
    $scope.deleteComms = function(comms){
      console.log("STUFF", comms);
      onPointService.deleteComment(comms)
      .then (function (data){
        var volIdx = $scope.volunteers.findIndex(function(el) {
          return el.id === comms.volunteerProf.id;
        })
        var commIdx = $scope.volunteers[volIdx].comments.findIndex(function(comment) {
          return comment.id === comms.id;
        })
        $scope.volunteers[volIdx].comments.splice(commIdx,1);
      })

    }
    $scope.updateComment = function(comment){
      console.log("THIS IS COMMENT", comment);
      var thingToSent = {
        text: comment.text,
        volunteerId: comment.volunteerProf.id,
        volunteerProf: comment.volunteerProf,
        id: comment.id,
        user: comment.user
      }
      onPointService.editComment(thingToSent)
      .then(function(data){
        console.log("Yay", data);
        $scope.thisCommentIndex = -1;
      })

    }

    $scope.delete = function(vol,$index) {
      onPointService.deleteVol(vol)
      .then(function(data) {
        $scope.volunteers.splice($index, 1)
      })
        // $scope.volunteers.pop(vol);
    }
// hello
    onPointService.getVolunteer()
      .then(function(vols) {
        $scope.volunteers = vols.data;
        window.stuff = vols.data;
        $scope.volunteers.forEach(function(el) {
          el.comments = [];
        })
        onPointService.getComments()
        .then(function (data) {
          console.log("Comments", data);
          var commentList = data.data.map(function(comment) {
            return {
              id: comment.id,
              text: comment.text,
              volunteerProf: comment.volunteerProf,
              user: comment.user
            }
          }).forEach(function(comment) {
            var volIdx = $scope.volunteers.findIndex(function(el) {
              return el.id === comment.volunteerProf.id
            })
            $scope.volunteers[volIdx].comments.push(comment);
          });



        });



      });

       $scope.isloggedin = window.sessionStorage.token
              if (window.sessionStorage.token) {
                $scope.user = JSON.parse(window.sessionStorage.token)
              }

              console.log("USER", $scope.user);


  });
