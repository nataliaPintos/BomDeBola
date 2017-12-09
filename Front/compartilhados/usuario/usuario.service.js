(function () {
    'use strict';

    angular
        .module('app')
        .factory('UsuarioService', usuarioService);

    function usuarioService($http) {

        var usuarioService = {
            salvar: salvar,
            listar: listar,
            getUsuario: getUsuario
        }

        return usuarioService;

        var urlBase = 'http://localhost:9090/usuario'; 

        function salvar(usuario) {
            return $http.post('http://localhost:9090/usuario/novo-usuario', usuario);
        }

        function listar() {
            return $http.get(urlBase + '/lista');
        }

        function getUsuario(id) {
            return $http.get(urlBase + "/" + id);
        }


    }

}());