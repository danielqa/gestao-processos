'use strict';

angular.module('app.responsavel').config(ResponsavelRoute);

ResponsavelRoute.$inject = ['$routeProvider'];
/* @ngInject */
function ResponsavelRoute($routeProvider) {

    $routeProvider.when('/responsavel', {
        controller: 'ResponsavelListCtrl',
        templateUrl: 'responsavel/responsavel.list.html',
        title: 'Consultar responsável'
    });
    $routeProvider.when('/responsavel/novo', {
        controller: 'ResponsavelFormCtrl',
        templateUrl: 'responsavel/responsavel.form.html',
        title: 'Cadastrar responsável'
    });
    $routeProvider.when('/responsavel/:id', {
        controller: 'ResponsavelFormCtrl',
        templateUrl: 'responsavel/responsavel.form.html',
        title: 'Editar responsável'
    });
}
