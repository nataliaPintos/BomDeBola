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
        console.log("Partida Id: " + $routeParams.partidaId);

        listaJogadores();

        function listaJogadores() {
            GrupoService.listarUsuarios(vm.idGrupo).then(response =>{
                $scope.users = response.data;
                console.log(response.data);
            });
        }    


    }

}());