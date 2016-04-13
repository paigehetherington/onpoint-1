angular
.module('onpoint')
.directive('onpointdir', function(){
  return{
   templateUrl: 'app/templates/volunteer.html',
    restrict: 'EA',
   scope:{
     generic:'='
    //  name:'@'
    //  organization:'@'
    //  country:'@'
    //  text:'@'
    //  photo:'&'
   }
  //  link: function(scope, element, attributes){
  //    console.log('el', element)
  //    element.bind('onClick', function(event){
  //    }
   //
  //   }
}
})
