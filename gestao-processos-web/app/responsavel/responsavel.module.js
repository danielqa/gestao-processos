'use strict';

angular.module('app.responsavel', [
    'ngResource',
    'ngRoute'
]);

require('./responsavel.factory');
require('./responsavel.controller');
require('./responsavel.routes');