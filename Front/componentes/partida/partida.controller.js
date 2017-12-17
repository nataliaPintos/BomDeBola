(function () {
    'use strict';

    angular
        .module('app')
        .controller('PartidaController', partidaController);

    function partidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
        var vm = this;
        vm.isAdmin = true;
        vm.idGrupo = $routeParams.id;
        vm.idPartida = $routeParams.partidaId;
        vm.sortearTimes = sortearTimes;
        console.log("Partida Id: " + $routeParams.partidaId);

        listaJogadores();

        function listaJogadores() {
            PartidaService.listaJogadores(vm.idPartida).then(response =>{
                $scope.users = response.data;
                console.log($scope.users);
            });
        }  
        
        function sortearTimes() {
            PartidaService.sortear(vm.idPartida).then(response =>{
                $scope.users = response.data;
                console.log(response.data);
            });
        }  


    }

}());