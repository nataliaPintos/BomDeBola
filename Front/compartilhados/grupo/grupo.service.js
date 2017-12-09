(function () {
    'use strict';

    angular
        .module('app')
        .factory('GrupoService', grupoService);

    function grupoService($http) {

        var grupoService = {
            criar: criar,
            listarGrupos: listarGrupos,
            alterar: alterar,
            excluirGrupo: excluirGrupo,
            buscarPorId: buscarPorId,
            convidar: convidar,
            excluirJogador: excluirJogador
        }

        return grupoService;

        var urlBase = 'http://localhost:9090/grupo'; 

        function criar(usuario) {
            return $http.post('http://localhost:9090/usuario/novo-usuario', usuario);
        }

        function listarGrupos() {
            return $http.get(urlBase + '/lista');
        }

        function buscarPorId(id) {
            return $http.get(urlBase + "/" + id);
        }

        function alterar() {
            return $http.get(urlBase + '/lista');
        }

        function excluirGrupo() {
            return $http.get(urlBase + '/lista');
        }

        function convidar() {
            return $http.get(urlBase + '/lista');
        }

        function excluirJogador() {
            return $http.get(urlBase + '/lista');
        }



    }

}());