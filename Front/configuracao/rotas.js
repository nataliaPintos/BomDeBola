(function () {
    'use strict';

    angular
        .module('app')
        .config(rotasConfig);


    //Configuração das rotas
    function rotasConfig($routeProvider) {

        $routeProvider
        
        .when('/', {
            templateUrl: 'componentes/login/login.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
        })

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

        .when('/grupo/altera/:id?', {
            templateUrl: 'componentes/grupo/cadastroGrupo.html',
            controller: 'GrupoController',
            controllerAs: 'grupoCtrl'
        })
        
        .when('/grupo/feed', {
            templateUrl: 'componentes/grupo/feedGrupo.html',
            controller: 'GrupoController',
            controllerAs: 'grupoCtrl'
        }) 

        .when('/imagem', {
            templateUrl: 'componentes/teste/imagem.html',
            controller: 'ImageController',
            controllerAs: 'imageCtrl'
        }) 

        .otherwise('/grupo/feed');

    }

}());