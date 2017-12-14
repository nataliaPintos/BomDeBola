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
        console.log(vm.usuario.nome);

        GrupoService.listarGrupos(vm.usuario.nome).then(response => {
            vm.grupos = response.data;
            console.log(response.data);
        });

    }

}());