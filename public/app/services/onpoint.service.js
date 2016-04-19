angular
    .module('onpoint')
    .factory('onPointService', function($http, $q) {
        var login = function(loginUser) {
            return $http.post('/login', loginUser);
        }
        var create = function(createVolunteer) {
            return $http.post('/volunteer-profile', createVolunteer);
        };

        var createComment = function(newComment) {
          console.log("NEW COMMENT", newComment)
            return $http.post('/comment', newComment);
        };
        var editComment = function(editComm){
          return $http.put('/comment',  editComm)
        };
        var deleteComment = function(comms){
          return $http.delete('/comment/' + comms.id)
        };

        var editVol = function(editVol) {
            return $http.put('/volunteer-profile/', editVol);
        };

        function getVolunteer() {
            var defer = $q.defer();
            $http.get('/volunteer-profile')
                .then(function(vol) {
                    defer.resolve(vol)
                })
            return defer.promise;
        }

        function createAccount(userData) {
            return $http.post('/register', userData)

        }
      function deleteVol(vol) {
        return $http.delete('/volunteer-profile/' + vol.id);
      }

      function logout(){
        return $http.post('/logout');
      }
      function submit(){
        return $http.put('volunteer-profile', submit);
      }

      function getComments(){
        return $http.get('/comment');
      }
      // function editComments(){
      //   return $http.put('/comment');
      // }
      //
      // function deleteComments(){
      //   return $http.delete('/comment');
      // }


        return {
            login: login,
            create: create,
            getVolunteer: getVolunteer,
            createAccount: createAccount,
            createComment: createComment,
            editVol:editVol,
            deleteVol:deleteVol,
            logout:logout,
            submit:submit,
            getComments:getComments,
            editComment:editComment,
            deleteComment:deleteComment,
        };

    });
