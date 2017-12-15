(function () {
    'use strict';

    angular
        .module('app')
        .controller('FeedPartidaController', feedPartidaController);

    function feedPartidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
        var vm = this;
        vm.criarPartida = criarPartida;
        vm.isAdmin = true;
        vm.idGrupo = $routeParams.id;
        vm.idPartida = $routeParams.idPartida;
        //vm.listaJogadores = listaJogadores;
        //vm.setupPartida = setupPartida;
        vm.listaPartidas = listaPartidas;
        

        if(vm.idPartida) {
            listaJogadores();
            setupPartida();
        } else {
            listaPartidas();
        }

        function listaPartidas() {
            PartidaService.listar(vm.idGrupo).then(response => {
                console.log(response.data);
                $scope.partidas = response.data;
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