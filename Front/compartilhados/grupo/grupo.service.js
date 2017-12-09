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

        var urlBase = 'http://localhost:9090/grupo';

        return grupoService;

        function criar(grupo) {
            return $http.post(urlBase + '/novo-grupo', grupo);
        }

        function listarGrupos() {
            return $http.get(urlBase + '/lista');
        }

        function buscarPorId(id) {
            return $http.get(urlBase + "/" + id);
        }

        function alterar(grupo) {
            return $http.put(urlBase + '/atualizar-grupo/' + grupo.id, grupo);
        }

        function excluirGrupo(id) {
            return $http.get(urlBase + '/lista');
        }

        function convidar(email) {
            return $http.get(urlBase + '/lista');
        }

        function excluirJogador(id) {
            return $http.get(urlBase + '/lista');
        }



    }

}());