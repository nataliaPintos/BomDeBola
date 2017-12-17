angular.module('app').directive('jogador', function () {

    return {
        scope: {
            jogador: '=jogadorItem',
            isAvaliacao: '=?'

        },

        controller: function (AvaliacaoService, $scope, authService, toastr) {

            $scope.avaliarJogador = avaliarJogador;


            function avaliarJogador(nota) {
                var idAvaliador = authService.getUsuario().id;
                var avaliacao = {
                    nota: nota,
                    idAvaliador: idAvaliador,
                    idAvaliado: $scope.jogador.usuarioGrupo.usuario.id
                }
                AvaliacaoService.criarAvaliacao(avaliacao).then(response => {
                    toastr.success("Avaliado!");
                });

            }

        },

        templateUrl: 'componentes/jogadorlist/jogadorlist.html'
    };
});