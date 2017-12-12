angular.module('app')
.directive('menu', function ($rootScope) {

  return {

    restrict: 'E',

    scope: {},
    
    templateUrl: 'componentes/menu/menu.html',
    
    controller: function ($scope, authService) {

    
    }
  }

});