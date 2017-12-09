(function(){
    'use strict';

    //Inicia o módulo e adiciona as dependências necessárias
    angular
        .module('app', [
            'ngRoute', 'auth', 
            'angularModalService', 'ngAnimate'
        ]);

}());