angular.module('app')
.directive('header', function ($rootScope) {

  return {

    restrict: 'E',

    scope: {},
    
    templateUrl: 'componentes/header/header.html',
    
    controller: function ($scope, authService, PartidaService, UsuarioService, toastr) {

      atualizarUsuario();

      $scope.logout = authService.logout;
      $scope.confirmar = confirmar;

      $rootScope.$on('authLoginSuccess', function () {
        atualizarUsuario();
      });

      $rootScope.$on('authLogoutSuccess', function () {
        atualizarUsuario();
      });        

      function atualizarUsuario() {
        $scope.usuario = authService.getUsuario();
        console.log($scope.usuario.id);
        UsuarioService.notificacoes($scope.usuario.id).then(response => {
          console.log(response.data);
          $scope.notificacoes = response.data;
       
        
        });
        
      }

      function confirmar(id) {
        console.log("confirmacao");
        PartidaService.confirmar(id).then(response => {
          toastr.success("Partida confirmada!");
          atualizarUsuario();
          $scope.$apply();
        })
      }
    }
  }

});