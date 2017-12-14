(function () {
    'use strict';

    angular
        .module('app')
        .controller('PartidaController', partidaController);

    function partidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
        var vm = this;
        vm.criarPartida = criarPartida;
        vm.isformAtivo = true;
        vm.id_grupo = $routeParams.id;
        
        if(vm.id_grupo) {
            carregarNovaPartida();
        } else {
            $location.path('/dashboard');
        }
        

        function carregarNovaPartida() {
            PartidaService.carregar(vm.id_grupo).then(response => {
                console.log(response.data);
                var partidaModel = response.data;
                partidaModel.hora_inicio = new Date(partidaModel.hora_inicio);
                $scope.partida = partidaModel;
            });
        } 

        function criarPartida(partida) {
            PartidaService.criar(partida).then(response => {
                console.log(response.data);
                $location.path('grupo/'+vm.id_grupo+'/feed');
                toastr.success("Partida criar com sucesso");
            });
        }

    }

}());