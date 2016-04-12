
var onPoint ={

 urls:{
   about:      '/about',
   home:       '/home',
   login:      '/login',
   logout:     'logout',
   photogallert: '/photogallery',
   press:        '/press',
   serviceorg:   '/service-org',
   volunteer:    '/volunteer-profile'
 }
};

function getImg(){
 $.ajax({
   method:'GET',
   url: onPoint.urls.volunteer + id,
   success: function(){
     console.log('volunteer!');
   },
   function postImg(){
    $.ajax({
      method:'POST',
      url: onPoint.urls.volunteer + id,
      success: function(){
        console.log('volunteer!');
 });
