'use strict';

angular.module('app.processo').config(ProcessoRoute);

ProcessoRoute.$inject = ['$routeProvider'];
/* @ngInject */
function ProcessoRoute($routeProvider) {

    $routeProvider.when('/processo', {
        controller: 'ProcessoListCtrl',
        templateUrl: 'processo/processo.list.html',
        title: 'Consultar processo'
    });
    $routeProvider.when('/processo/novo', {
        controller: 'ProcessoFormCtrl',
        templateUrl: 'processo/processo.form.html',
        title: 'Cadastrar processo'
    });
    $routeProvider.when('/processo/:id', {
        controller: 'ProcessoFormCtrl',
        templateUrl: 'processo/processo.form.html',
        title: 'Editar processo'
    });
}
