'use strict';

angular.module('app').run(AppRun);

AppRun.$inject = ['$rootScope', 'Flash', 'HttpService'];
/* @ngInject */
function AppRun($rootScope, Flash, HttpService) {

    $rootScope.httpService = HttpService;

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        // Obtém o atributo "title" da rota atual
        if (current.hasOwnProperty('$$route')) {
            $rootScope.title = current.$$route.title;
        }

        // Remove da tela a <div> de mensagens
        Flash.dismiss();
    });
}
