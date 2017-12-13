angular.module('app')
.directive('user', function ($rootScope) {

  return {

    restrict: 'E',

    scope: {},
    
    templateUrl: 'componentes/userlist/userlist.html',
    
    controller: function ($scope, authService) {

    
    }
  }

});