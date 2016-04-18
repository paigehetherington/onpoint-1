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

        // todo: create endpoint
        var editVol = function(editVol) {
            return $http.post('/volunteer-profile', editVol);
        };
        // var delete = function(deleteVol){
        //   return $http.delete('/volunteer-profile/' + vol.id);
        // };

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
      function editVol(editvol){
        return $http.put('/volunteer-profile', editvol)
      }

      function deleteVol(vol) {
        return $http.delete('/volunteer-profile/' + vol.id);
      }




        return {
            login: login,
            create: create,
            getVolunteer: getVolunteer,
            createAccount: createAccount,
            createComment: createComment,
            editVol:editVol,
            deleteVol:deleteVol,
        };

    });
