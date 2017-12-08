(function () {
    'use strict';

    angular
        .module('app')
        .service('UsuarioService', usuarioService);

    function usuarioService($http) {

        var usuarioService = {
            salvar: salvar
        }

        return usuarioService;

        function salvar(natalia) {
            return $http.post(url, natalia);
        }
    }

}());