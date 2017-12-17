angular.module('app').directive('jogador', function(){
  
      return {
          scope: {
              jogador: '=jogadorItem'
          },
          templateUrl: 'componentes/jogadorlist/jogadorlist.html'
      };
  });
  