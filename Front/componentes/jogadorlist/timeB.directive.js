angular.module('app').directive('timeb', function(){
  
      return {
          scope: {
            jogador: '=timebItem'
          },
          templateUrl: 'componentes/jogadorlist/jogadorlist.html'
      };
  });
  