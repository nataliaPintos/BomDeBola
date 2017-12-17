(function () {
    'use strict';

    angular
        .module('app')
        .controller('NovaPartidaController', novaPartidaController);

    function novaPartidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
        var vm = this;
        vm.criarPartida = criarPartida;
        vm.isformAtivo = true;
        vm.idGrupo = $routeParams.id;
        
        if(vm.idGrupo) {
            carregarNovaPartida();
        } else {
            $location.path('/dashboard');
        }
        

        function carregarNovaPartida() {
            PartidaService.carregar(vm.idGrupo).then(response => {
                var partidaModel = response.data;
                partidaModel.diaSemana = new Date(response.data.diaSemana);
                partidaModel.horaInicio = new Date(response.data.horaInicio);
                partidaModel.horaFinal = new Date(response.data.horaFinal);    
                partidaModel.tempoConfirmacao = new Date(response.data.tempoConfirmacao);    
                partidaModel.tempoAvaliacao = new Date(response.data.tempoAvaliacao); 
                $scope.partida = partidaModel;
            });
        } 

        function criarPartida(partida) {
            partida.idGrupo = vm.idGrupo;
            PartidaService.criar(partida).then(response => {
                $location.path('grupo/'+vm.idGrupo+'/feed');
                toastr.success("Partida criada com sucesso");
            });
        }

    }

}());