'use strict';

angular.module('app.responsavel').factory('Responsavel', Responsavel);

Responsavel.$inject = ['$resource', 'CONTEXT'];
/* @ngInject */
function Responsavel($resource, CONTEXT) {

    return $resource(CONTEXT + '/responsavel/:id');
}

angular.module('app.responsavel').factory('ResponsavelService', ResponsavelService);

ResponsavelService.$inject = ['$http', 'CONTEXT'];
/* @ngInject */
function ResponsavelService($http, CONTEXT) {

    return {
        getProcessos: function (id) {
            return $http({method: 'GET', url: CONTEXT + '/responsavel/processos/' + id});
        }
    };
}
