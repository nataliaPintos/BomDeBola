//let app = angular.module('app', ['ngRoute', 'auth']);

// Configurações utilizadas pelo módulo de autenticação (authService)
angular.module('app').constant('authConfig', {

    // Obrigatória - URL da API que retorna o usuário
    //urlUsuario: 'http://10.99.0.12:3296/api/acessos/usuarioLogado',
    urlUsuario: 'http://localhost:9090/logged-user',

    // Obrigatória - URL da aplicação que possui o formulário de login
    urlLogin: '/home',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGIN com sucesso
    urlPrivado: '/grupo',

    // Opcional - URL da aplicação para onde será redirecionado (se for informado) após o LOGOUT
    urlLogout: '/home'
});
