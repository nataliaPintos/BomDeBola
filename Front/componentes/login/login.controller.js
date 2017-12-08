(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    // nos parâmetros coloco o que vou precisar utilizar
    function LoginController(authService) {
        var vm = this;
        //Liberando para aceesso no html
        vm.helloWorld = helloWorld;

        function helloWorld() {
            return 'Só um exemplo';
        }

    }

}());