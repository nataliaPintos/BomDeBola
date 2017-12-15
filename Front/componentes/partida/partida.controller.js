(function () {
    'use strict';

    angular
        .module('app')
        .controller('PartidaController', partidaController);

    function partidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
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
                console.log(response.data);
                var partidaModel = response.data;
                // partidaModel.diaSemana = new Date(partidaModel.diaSemana);
                // partidaModel.horaInicio = new Date(response.data.horaInicio);
                // partidaModel.horaFinal = new Date(response.data.horaFinal);    
                // partidaModel.tempoConfirmacao = new Date(response.data.tempoConfirmacao);    
                // partidaModel.tempoAvaliacao = new Date(response.data.tempoAvaliacao); 
                $scope.partida = partidaModel;
            });
        } 

        function criarPartida(partida) {
            console.log(partida);
            partida.idGrupo = vm.idGrupo;
            PartidaService.criar(partida).then(response => {
                console.log(response.data);
                $location.path('grupo/'+vm.idGrupo+'/feed');
                toastr.success("Partida criar com sucesso");
            });
        }

    }

}());