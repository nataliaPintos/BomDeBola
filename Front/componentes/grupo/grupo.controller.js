(function () {
    'use strict';

    angular
        .module('app')
        .controller('GrupoController', grupoController);

    function grupoController(authService, GrupoService, $routeParams, $scope, $location, $filter, toastr, MapService, $route) {
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

        if(gr.isAlterar){
            GrupoService.listarUsuarios($routeParams.id).then(response =>{
                $scope.users = response.data;
              
            });
            GrupoService.buscarPorId($routeParams.id)
                .then(response =>{
            
                    $scope.grupo = response.data;
                    $scope.grupo.horaInicio = new Date($scope.grupo.horaInicio);    
                    $scope.grupo.horaFinal = new Date($scope.grupo.horaFinal);    
                    $scope.grupo.horasConfirmacao = new Date($scope.grupo.horasConfirmacao);    
                    $scope.grupo.tempoAvaliacao = new Date($scope.grupo.tempoAvaliacao);    
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
            promise.then(response => {
                $location.path('/grupo/'+response.data.id+'/feed');
            });
        }

        function buscar(endereco) {
            MapService.search(endereco).then(response => {
                $scope.enderecos = response.data.predictions.map(e => e.description);
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
                emailUsuario: email,
                idGrupo: $scope.grupo.id
            }
            GrupoService.convidar(usuarioGrupo).then(response =>{
                gr.isAdicionarJogador = false;
                $route.reload();
            });
        }

        function mostrarAdicionarJogador(){
            gr.isAdicionarJogador = true;
        }

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