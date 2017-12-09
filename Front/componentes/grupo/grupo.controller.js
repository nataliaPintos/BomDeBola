(function () {
    'use strict';

    angular
        .module('app')
        .controller('GrupoController', GrupoController);

    function GrupoController(authService, GrupoService, ModalService, $routeParams, $scope, $location) {
        var gr = this;
        gr.alterarGrupo = alterarGrupo;
        gr.excluirGrupo = excluirGrupo;
        gr.gruposUsuario;
        gr.grupo;
        gr.isAlterar = !!$routeParams.id;
        gr.openModal = openModal;

        GrupoService.listarGrupos().then(response =>{
            gr.gruposUsuario = response.data;
        });
    
        if(gr.isAlterar){
            GrupoService.buscarPorId($routeParams.id)
                .then(response =>{
                    gr.grupo = response.data;
            });
        }
    
        function alterarGrupo (grupo) {
            if($scope.formGrupo.$invalid) return;
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
            alert(mensagem);
            promise.then(promise => $location.path('/grupo'));
        }


        function openModal() {
            console.log('entrou aqui');
          ModalService.showModal({
            templateUrl: 'componentes/grupo/modal/modal.html',
            controller: 'ModalController',
            controllerAs: 'modalCtrl',
            bodyClass: 'modal.is-active'
           
          });
        }



    }

}());