angular.module('app').directive('partida', function(){
  
    return {
        scope: {
            partida: '=partidaItem',
            id: '=idGrupo'
        },
        templateUrl: 'componentes/partidalist/partidalist.html'
    };
});
  