'use strict';

angular.module('app').factory('EnumService', EnumService);

EnumService.$inject = ['$resource', 'CONTEXT'];
/* @ngInject */
function EnumService($resource, CONTEXT) {

    return $resource(CONTEXT + '/enum/:className');
}
