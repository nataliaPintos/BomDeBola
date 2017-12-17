(function () {
    'use strict';

    angular
        .module('app')
        .factory('AvaliacaoService', avaliacaoService);

    function avaliacaoService($http) {

        var avalicaoService = {
            criarAvaliacao: criarAvaliacao
        

        }

        var urlBase = 'http://localhost:9090/avaliacao';

        return avalicaoService;

        function criarAvaliacao(avaliacao) {
            return $http.post(urlBase + '/nova-avaliacao', avaliacao);
        }

    }

}());