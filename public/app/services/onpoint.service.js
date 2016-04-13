angular
  .module('onpoint')
  .factory('onPointService', function ($http, $q) {
    var login = function (loginUser) {
      $http.post('/login', loginUser).success(function (res) {
          // alert("I am working");
        console.log(res);
      });
    }
    var create = function (createVolunteer) {
      return $http.post('/volunteer-profile', createVolunteer);
    };

    function getVolunteer(){
      var defer = $q.defer();
      $http.get('/volunteer-profile')
      .then(function(vol){
        defer.resolve(vol)
      })
      return defer.promise;
    }



    return {
      login: login,
      create: create,
      getVolunteer: getVolunteer,
    };

  });
