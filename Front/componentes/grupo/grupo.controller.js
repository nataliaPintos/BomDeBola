(function () {
    'use strict';

    angular
        .module('app')
        .controller('GrupoController', grupoController);

    function grupoController(authService, GrupoService, $routeParams, $scope, $location, $filter, toastr, MapService) {
        var gr = this;
        gr.alterarGrupo = alterarGrupo;
        gr.excluirGrupo = excluirGrupo;
        gr.gruposUsuario;
        gr.convidar = convidar;
        gr.isAlterar = !!$routeParams.id;
        gr.buscar = buscar;
        gr.isAdicionarJogador = false;
        gr.mostrarAdicionarJogador = mostrarAdicionarJogador;

        gr.closeSideNav =  closeSideNav;
        $scope.local = {
            latitude: 0,
            longitude: 0
        }
        
        autoComplete();
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
            $scope.grupo = grupo;
            $scope.grupo.latitude =  $scope.local.latitude;
            $scope.grupo.longitude = $scope.local.longitude;
            if(!gr.isAlterar) {
                criarGrupo($scope.grupo);
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

        function buscar(endereco) {
            MapService.search(endereco).then(response => {
                $scope.enderecos = response.data.predictions.map(e => e.description);
                console.log($scope.enderecos);
            });
        }

        function autoComplete() {
            $scope.autocomplete = new google.maps.places.Autocomplete(
                (document.getElementById('autocomplete'))
            );
            $scope.autocomplete.addListener('place_changed', obterCoordenadas);
        }
    
        function obterCoordenadas() {
            var place = $scope.autocomplete.getPlace();
            $scope.local.latitude = place.geometry.location.lat();
            $scope.local.longitude = place.geometry.location.lng();
            return;
        }

        function convidar(email) {
            var usuarioGrupo = {
                email_usuario: email,
                id_grupo: 1 //grupo.id
            }
            GrupoService.convidar(usuarioGrupo).then(response =>{
                console.log(response.data)
                gr.isAdicionarJogador = false;
            });
        }

        function mostrarAdicionarJogador(){
            gr.isAdicionarJogador = true;
        }


        // function openModal() {
        //     console.log("entrou aqui");
        //     GrupoService.buscarPorId(1)
        //         .then(response =>{
        //         ModalService.showModal({
        //             templateUrl: 'componentes/grupo/modal/modal.html',
        //             controller: 'ModalController',
        //             controllerAs: 'modalCtrl',
        //             bodyClass: 'modal.is-active',
        //             inputs: {
        //                 grupo: response.data
        //             }
        //         });
        //     });    
        // }

        // function openModalEdicao() {
        //     console.log("entrou aqui");
        //     GrupoService.buscarPorId(1)
        //         .then(response =>{
        //         ModalService.showModal({
        //             templateUrl: 'componentes/grupo/modal/modalEditarGrupo.html',
        //             controller: 'ModalController',
        //             controllerAs: 'modalCtrl',
        //             bodyClass: 'modal.is-active',
        //             inputs: {
        //                 grupo: response.data
        //             }
        //         });
        //     });    
        // }

        // function openModalNovaPartida() {
        //     console.log("entrou aqui");
        //     GrupoService.buscarPorId(1)
        //         .then(response =>{
        //         ModalService.showModal({
        //             templateUrl: 'componentes/grupo/modal/modalNovaPartida.html',
        //             controller: 'ModalController',
        //             controllerAs: 'modalCtrl',
        //             bodyClass: 'modal.is-active',
        //             inputs: {
        //                 grupo: response.data
        //             }
        //         });
        //     });    
        // }


       function closeSideNav(e) {
            e.preventDefault();
            $("body").toggleClass("sidenav-toggled");
            $(".navbar-sidenav .nav-link-collapse").addClass("collapsed");
            $(".navbar-sidenav .sidenav-second-level, .navbar-sidenav .sidenav-third-level").removeClass("show");
          };
          // Force the toggled class to be removed when a collapsible nav link is clicked
          $(".navbar-sidenav .nav-link-collapse").click(function(e) {
            e.preventDefault();
            $("body").removeClass("sidenav-toggled");
          });


    }

}());