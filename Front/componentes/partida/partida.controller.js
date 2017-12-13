(function () {
    'use strict';

    angular
        .module('app')
        .controller('PartidaController', partidaController);

    function partidaController($scope, GrupoService, PartidaService, toastr) {
        var vm = this;
        vm.carregarNovaPartida = carregarNovaPartida;
        vm.criarPartida = criarPartida;
        vm.isformAtivo = true;
        

        carregarNovaPartida();

        function carregarNovaPartida() {
            PartidaService.carregar(1).then(response => {
                console.log(response.data);
                $scope.partida = response.data;
            });
            
    
        } 

        function criarPartida(partida) {
            PartidaService.criar(partida).then(response => {
                console.log(response.data);
                $location.path('/partida/feed');
                toastr.success("Partida criar com sucesso");
            });
        }

    }

}());