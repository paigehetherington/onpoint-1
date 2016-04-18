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
        return $http/post('/edit', editvol)
      }




        return {
            login: login,
            create: create,
            getVolunteer: getVolunteer,
            createAccount: createAccount,
            createComment: createComment,
            editVol:editVol,
        };

    });
