(function () {
    'use strict';

    angular
        .module('app')
        .controller('PerfilController', PerfilController);

    //LoginController.$inject = ['authservice'];
    // nos parâmetros coloco o que vou precisar utilizar
    function PerfilController(authService, UsuarioService, toastr) {
        var vm = this;
        //Liberando para aceesso no html
        vm.msgErrolLogin = false;
        

    }

}());