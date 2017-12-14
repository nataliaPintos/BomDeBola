(function () {
    'use strict';

    angular
        .module('app')
        .factory('GrupoService', grupoService);

    function grupoService($http) {

        var grupoService = {
            criar: criar,
            listarGrupos: listarGrupos,
            listarUsuarios: listarUsuarios,
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

        function listarGrupos(id) {
            return $http.get(urlBase + '/lista-grupos/' + id);
        }

        function listarUsuarios(id) {
            return $http.get(urlBase + '/lista-usuarios/' + id);
        }

        function buscarPorId(id) {
            return $http.get(urlBase + "/" + id);
        }

        function alterar(grupo) {
            return $http.put(urlBase + '/altera/' + grupo.id, grupo);
        }

        function excluirGrupo(id) {
            return $http.get(urlBase + '/lista');
        }

        function convidar(usuarioGrupo) {
            return $http.post(urlBase + '/inclui-usuario', usuarioGrupo);
        }

        function excluirJogador(id) {
            return $http.get(urlBase + '/lista');
        }

    }

}());