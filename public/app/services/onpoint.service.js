angular
  .module('onpoint')
  .factory('onPointService', function ($http) {
    var login = function (loginUser) {
      $http.post('/login', loginUser).success(function (res) {
          // alert("I am working");
        console.log(res);
      });
    }
    var create = function (createVolunteer) {
      return $http.post('/volunteer-profile', createVolunteer);
    };
    



    return {
      login: login,
      create: create,
    };

  });
