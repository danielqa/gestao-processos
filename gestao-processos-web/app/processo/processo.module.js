'use strict';

angular.module('app.processo', [
    'ngResource',
    'ngRoute'
]);

require('./processo.factory');
require('./processo.controller');
require('./processo.routes');