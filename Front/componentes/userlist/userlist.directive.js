angular.module('app').directive('user', function(){
  
      return {
          scope: {
              user: '=userItem'
          },
          templateUrl: 'componentes/userlist/userlist.html'
      };
  });
  
  

// .directive('user', function ($rootScope) {

//   return {

//     restrict: 'E',

//     scope: {user: '=userItem'},
    
//     templateUrl: 'componentes/userlist/userlist.html',
    
//     controller: function ($scope, authService) {

    
//     }
//   }

//});