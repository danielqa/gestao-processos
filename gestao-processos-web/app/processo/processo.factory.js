'use strict';

angular.module('app.processo').factory('Processo', Processo);

Processo.$inject = ['$resource', 'CONTEXT'];
/* @ngInject */
function Processo($resource, CONTEXT) {

    return $resource(CONTEXT + '/processo/:id');
}

angular.module('app.processo').factory('ProcessoService', ProcessoService);

ProcessoService.$inject = ['$http', 'CONTEXT'];
/* @ngInject */
function ProcessoService($http, CONTEXT) {

    return {
        findProcessosVinculaveis: function (id) {
            return $http({method: 'GET', url: CONTEXT + '/processo/vinculaveis', params: {id: id}});
        }
    };
}
