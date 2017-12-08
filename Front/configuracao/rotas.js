(function () {
    'use strict';

    angular
        .module('app')
        .config(rotasConfig);


    //Configuração das rotas
    function rotasConfig($routeProvider) {

        $routeProvider.when('/', {
            templateUrl: 'componentes/login/login.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
        })

        $routeProvider.when('/home', {
            templateUrl: 'componentes/login/login.html',
            controller: 'LoginController',
            controllerAs: 'loginCtrl'
        })

        .otherwise('/home');

    }

}());