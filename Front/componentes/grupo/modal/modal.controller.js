(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModalController', modalController);

    function modalController(close, grupo, $scope, GrupoService, PartidaService) {
        var vm = this;
        vm.isModal = true;
        vm.closeModal = closeModal;
        vm.salvar = salvar;
        vm.convidar = convidar;
        vm.criarPartida = criarPartida;
        
        grupo.hora_inicio = new Date(grupo.hora_inicio);    
        grupo.hora_final = new Date(grupo.hora_final);    
        grupo.horas_confirmacao = new Date(grupo.horas_confirmacao);    
        grupo.tempo_avaliacao = new Date(grupo.tempo_avaliacao);    
        $scope.grupo = grupo;
        $scope.partida = carregarNovaPartida();

        console.log (grupo);

        function salvar(grupo) {
            GrupoService.alterar(grupo).then(response => {
                console.log(response.data)
                closeModal();
            });
        } 

        function convidar(email) {
            var usuarioGrupo = {
                email_usuario: email,
                id_grupo: grupo.id
            }
            GrupoService.convidar(usuarioGrupo).then(response =>{
                console.log(response.data)
                closeModal();
            });
        }

        function carregarNovaPartida(grupo) {
            var novaPartida = {
                dia_semana: $scope.grupo.dia_semana,
                dias_confirmacao: $scope.grupo.dias_confirmacao,
                hora_final: $scope.grupo.hora_final,
                hora_inicio: $scope.grupo.hora_inicio,
                horas_confirmacao: $scope.grupo.horas_confirmacao,
                id_grupo: $scope.grupo.id,
                latitude: $scope.grupo.latitude,
                longitude: $scope.grupo.longitude,
                tempo_avaliacao: $scope.grupo.tempo_avaliacao,
                time_max: $scope.grupo.time_max,
                time_min: $scope.grupo.time_min
            };
            console.log(novaPartida);
            return novaPartida;
    
        } 

        function criarPartida(partida) {
            PartidaService.criar(partida).then(response => {
                console.log(response.data);
                closeModal();
            });
        }

        function closeModal() {
            vm.isModal = false;
            close();
        }
    }

}());