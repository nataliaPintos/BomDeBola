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
                console.log(response);
                toastr.success("Dallleeeeeee");
                
            },
                response => {
                console.log(response);
                vm.msgErroLogin = "Usuário ou senha incorretos";
                }
            );
        }

        function cadastrar(usuario) {
            usuario.imagem = 'http://www.pequenaeva.com/uploads/userfiles/images/xbola-de-futebol-2_pngBola-redonda-entenda-por-que-ela-tem-este-formato.png.pagespeed.ic.gWQmlCZ4Si.png';
            UsuarioService.salvar(usuario).then(
                response => {
                console.log(response);               
            });
        }

    }

}());