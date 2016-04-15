angular
  .module('onpoint')
  .factory('onPointService', function ($http, $q) {
    var login = function (loginUser) {
      return $http.post('/login', loginUser);
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
    // function createAccount(){
    //   var defer =$q.defer();
    //   $http.get('/register')
    //   .then(function(createAcct){
    //   defer.resolve(createAcct)
    // })



    return {
      login: login,
      create: create,
      getVolunteer: getVolunteer,
      // createAccount: createAccount,
    };

  });
