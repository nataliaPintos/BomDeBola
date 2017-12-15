(function () {
    'use strict';

    angular
        .module('app')
        .factory('UsuarioService', usuarioService);

    function usuarioService($http) {

        var usuarioService = {
            salvar: salvar,
            listar: listar,
            getUsuario: getUsuario,
            notificacoes: notificacoes
        }

        var urlBase = 'http://localhost:9090/usuario'; 

        return usuarioService;

        function salvar(usuario) {
            return $http.post(urlBase + '/novo-usuario', usuario);
        }

        function listar() {
            return $http.get(urlBase + '/lista');
        }

        function getUsuario(id) {
            return $http.get(urlBase + "/" + id);
        }

        function notificacoes(id) {
            return $http.get(urlBase + '/notificacoes/' + id);
        }
    }

}());