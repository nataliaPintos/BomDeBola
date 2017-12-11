(function () {
    'use strict';

    angular
        .module('app')
        .controller('GrupoController', GrupoController);

    function GrupoController(authService, GrupoService, ModalService, $routeParams, $scope, $location, $filter, toastr) {
        var gr = this;
        gr.alterarGrupo = alterarGrupo;
        gr.excluirGrupo = excluirGrupo;
        gr.gruposUsuario;
        gr.isAlterar = !!$routeParams.id;
        gr.openModal = openModal;
        gr.openModalEdicao = openModalEdicao;
        gr.openModalNovaPartida = openModalNovaPartida;

        // GrupoService.listarGrupos().then(response =>{
        //     gr.gruposUsuario = response.data;
        // });
    
        if(gr.isAlterar){
            GrupoService.buscarPorId($routeParams.id)
                .then(response =>{
                    console.log(response.data);
                    console.log($routeParams.id);
                    $scope.grupo = response.data;
                    $scope.grupo.hora_inicio = new Date($scope.grupo.hora_inicio);    
                    $scope.grupo.hora_final = new Date($scope.grupo.hora_final);    
                    $scope.grupo.horas_confirmacao = new Date($scope.grupo.horas_confirmacao);    
                    $scope.grupo.tempo_avaliacao = new Date($scope.grupo.tempo_avaliacao);    
            });
        }
    
        function alterarGrupo (grupo) {
            //if($scope.formGrupo.$invalid) return;
            if(!gr.isAlterar) {
                criarGrupo(grupo);
                return;
            }
            let promise = GrupoService.alterar(grupo);
            let mensagem = "Grupo alterado com sucesso!";
            redirecionar(promise, mensagem);
        }
    
        function criarGrupo (grupo) {
            let promise = GrupoService.criar(grupo);
            let mensagem = "Grupo criado com sucesso!";
            redirecionar(promise, mensagem);   
        }
    
        function excluirGrupo (grupo) {
            let promise = GrupoService.excluirGrupo(grupo.id);
            let mensagem = "Grupo excluído.";
            redirecionar(promise, mensagem);
        }
    
        function redirecionar(promise, mensagem){
            //futuro toaster
            toastr.success(mensagem);
            promise.then(promise => $location.path('/grupo'));
        }


        function openModal() {
            console.log("entrou aqui");
            GrupoService.buscarPorId(25)
                .then(response =>{
                ModalService.showModal({
                    templateUrl: 'componentes/grupo/modal/modal.html',
                    controller: 'ModalController',
                    controllerAs: 'modalCtrl',
                    bodyClass: 'modal.is-active',
                    inputs: {
                        grupo: response.data
                    }
                });
            });    
        }

        function openModalEdicao() {
            console.log("entrou aqui");
            GrupoService.buscarPorId(25)
                .then(response =>{
                ModalService.showModal({
                    templateUrl: 'componentes/grupo/modal/modalEditarGrupo.html',
                    controller: 'ModalController',
                    controllerAs: 'modalCtrl',
                    bodyClass: 'modal.is-active',
                    inputs: {
                        grupo: response.data
                    }
                });
            });    
        }

        function openModalNovaPartida() {
            console.log("entrou aqui");
            GrupoService.buscarPorId(25)
                .then(response =>{
                ModalService.showModal({
                    templateUrl: 'componentes/grupo/modal/modalNovaPartida.html',
                    controller: 'ModalController',
                    controllerAs: 'modalCtrl',
                    bodyClass: 'modal.is-active',
                    inputs: {
                        grupo: response.data
                    }
                });
            });    
        }


    }

}());