(function () {
    'use strict';

    angular
        .module('app')
        .factory('PartidaService', partidaService);

    function partidaService($http) {

        var partidaService = {
            criar: criar,
            carregar: carregar,
            listarTimes: listarTimes,
            alterar: alterar,
            cancelarPartida: cancelar,
            buscarPorId: buscarPorId,
            excluirPartida: excluir
        }

        var urlBase = 'http://localhost:9090/partida';

        return partidaService;

        function criar(partida) {
            return $http.post(urlBase + '/nova-partida', partida);
        }

        function carregar(id) {
            return $http.get(urlBase + '/nova-partida' + id);
        }

        function listarTimes() {
            return $http.get(urlBase + '/lista');
        }

        function buscarPorId(id) {
            return $http.get(urlBase + "/" + id);
        }

        function alterar(grupo) {
            return $http.put(urlBase + '/alteracao/' + grupo.id, grupo);
        }

        function cancelar(id) {
            return $http.get(urlBase + '/lista');
        }

        function excluir(id) {
            return $http.get(urlBase + '/lista');
        }


    }

}());