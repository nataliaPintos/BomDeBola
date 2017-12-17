(function () {
    'use strict';

    angular
        .module('app')
        .controller('PerfilController', PerfilController);

    //LoginController.$inject = ['authservice'];
    // nos parâmetros coloco o que vou precisar utilizar
    function PerfilController(authService, UsuarioService, GrupoService, $scope) {
        var vm = this;

        vm.usuario = authService.getUsuario();

        GrupoService.listarGrupos(vm.usuario.id).then(response => {
            vm.grupos = response.data;
           
        });

    }

}());