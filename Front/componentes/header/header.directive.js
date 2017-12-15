angular.module('app')
.directive('header', function ($rootScope) {

  return {

    restrict: 'E',

    scope: {},
    
    templateUrl: 'componentes/header/header.html',
    
    controller: function ($scope, authService, PartidaService) {

      atualizarUsuario();

      $scope.logout = authService.logout;

      $rootScope.$on('authLoginSuccess', function () {
        atualizarUsuario();
      });

      $rootScope.$on('authLogoutSuccess', function () {
        atualizarUsuario();
      });        

      function atualizarUsuario() {
        $scope.usuario = authService.getUsuario();
      }
    }
  }

});