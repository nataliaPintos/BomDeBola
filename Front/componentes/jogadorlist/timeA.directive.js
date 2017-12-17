angular.module('app').directive('timea', function(){
  
      return {
          scope: {
              jogador: '=timeaItem'
          },
          templateUrl: 'componentes/jogadorlist/jogadorlist.html'
      };
  });
  