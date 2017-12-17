(function () {
    'use strict';

    angular
        .module('app')
        .config(rotasConfig);


    //Configuração das rotas
    function rotasConfig($routeProvider) {

        $routeProvider
        
        .when('/home', {
            templateUrl: 'componentes/login/login.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
        }) 

        .when('/grupo', {
            templateUrl: 'componentes/grupo/cadastroGrupo.html',
            controller: 'GrupoController',
            controllerAs: 'grupoCtrl'
        }) 

        .when('/grupo/:id?/altera', {
            templateUrl: 'componentes/grupo/cadastroGrupo.html',
            controller: 'GrupoController',
            controllerAs: 'grupoCtrl'
        })
        
        .when('/grupo/:id?/feed', {
            templateUrl: 'componentes/grupo/feedGrupo.html',
            controller: 'GrupoController',
            controllerAs: 'grupoCtrl'
        }) 

        .when('/perfil', {
            templateUrl: 'componentes/usuario/perfil.html',
            controller: 'PerfilController',
            controllerAs: 'perfilCtrl'
        }) 
        
        .when('/grupo/:id?/nova-partida', {
            templateUrl: 'componentes/partida/novaPartida.html',
            controller: 'NovaPartidaController',
            controllerAs: 'novaPartidaCtrl'
        })

        .when('/grupo/:id?/partida/feed', {
            templateUrl: 'componentes/partida/feedPartida.html',
            controller: 'FeedPartidaController',
            controllerAs: 'feedPartidaCtrl'
        })

        .when('/grupo/:id?/partida/:partidaId?/times', {
            templateUrl: 'componentes/partida/times.html',
            controller: 'PartidaController',
            controllerAs: 'partidaCtrl'
        })
        
        .when('/grupo/:id?/partida/:partidaId?/times/avaliacao', {
            templateUrl: 'componentes/avaliacao/avaliacao.html',
            controller: 'PartidaController',
            controllerAs: 'partidaCtrl'
        })
                
        
        .when('/dashboard', {
            templateUrl: 'componentes/dashboard/dashBoard.html',
            controller: 'PerfilController',
            controllerAs: 'perfilCtrl'
        }) 

        .otherwise('/dashboard');

    }

}());