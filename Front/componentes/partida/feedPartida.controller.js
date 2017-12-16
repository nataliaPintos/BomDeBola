(function () {
    'use strict';

    angular
        .module('app')
        .controller('FeedPartidaController', feedPartidaController);

    function feedPartidaController($scope, GrupoService, PartidaService, toastr, $routeParams, $location) {
        var vm = this;
        vm.isAdmin = true;
        vm.idGrupo = $routeParams.id;

        listaPartidas();
      
        function listaPartidas() {
            PartidaService.listar(vm.idGrupo).then(response => {
                console.log(response.data);
                $scope.partidas = response.data;
            });
        }
        
    }

}());