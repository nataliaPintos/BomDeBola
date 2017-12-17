(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    //LoginController.$inject = ['authservice'];
    // nos parâmetros coloco o que vou precisar utilizar
    function LoginController(authService, UsuarioService, toastr) {
        var vm = this;
        //Liberando para aceesso no html
        vm.helloWorld = helloWorld;
        vm.login = login;
        vm.msgErrolLogin = false;
        vm.cadastrar = cadastrar;

        function helloWorld() {
            return 'Só um exemplo';
        }

        function login(usuarioRegistrado){
            authService.login(usuarioRegistrado)
            .then(
                response => {
                toastr.success("Login com Sucesso!");
                
            },
                response => {

                vm.msgErroLogin = "Usuário ou senha incorretos";
                toastr.error("Usuário ou senha incorretos");
                }
            );
        }

        function cadastrar(usuario) {
            usuario.imagemPerfil = 'ddadasdada';
            UsuarioService.salvar(usuario).then(
                response => {
                toastr.success("Cadastro com Sucesso!");    
            });
        }

    }

}());