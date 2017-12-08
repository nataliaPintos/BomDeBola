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
        vm.login = login;
        vm.msgErrolLogin = false;

        function helloWorld() {
            return 'Só um exemplo';
        }

        function login(){
            authService.login(usuario)
            .then(
                response => {
                console.log(response);
                
            },
                response => {
                console.log(response);
                vm.msgErroLogin = "Usuário ou senha incorretos";
                }
            );
        }

    }

}());