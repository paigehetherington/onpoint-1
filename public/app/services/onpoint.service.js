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
            return $http.post('/comment', newComment);
        };
        // var editComment = function(editComm){
        //   return $http.post('/comment',  editComm)
        // };
        // var deleteComment = function(deleteComm){
        //   retutn $http.delete('/comment', deleteComm)
        // };

        var editVol = function(editVol) {
            return $http.put('/volunteer-profile', editVol);
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
        return $http.put('volunteer-profile', submit)
      }






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
            // editComment:editComment,
            // deleteComment:deleteComment,
        };

    });
