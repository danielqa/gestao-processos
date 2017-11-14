'use strict';

angular.module('app', [
    'ngAnimate',
    'flash',
    'selectize',
    'angularUtils.directives.dirPagination',
    'app.responsavel',
    'app.processo'
]);

angular.module('app').constant('CONTEXT', 'api');

require('./app.config');
require('./app.run');
require('./alertify.config');
require('./services/enum.factory');
require('./services/http.factory');
require('./components/bootstrap-datepicker.directive');
require('./components/jquery-mask-plugin.directive');
require('./components/somente-numeros.directive');

setTimeout(function () {
    angular.element(document).ready(function () {
        angular.bootstrap(document, ['app']);
    });
}, 500);
