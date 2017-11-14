// npm
$ = jQuery = require('jquery');
require('bootstrap');
require('bootstrap-datepicker');
require('../node_modules/bootstrap-datepicker/dist/locales/bootstrap-datepicker.pt-BR.min');
require('selectize');
require('jquery-mask-plugin');
moment = require('moment');
require('../node_modules/moment/locale/pt-br');
require('angular');
require('angular-animate');
require('angular-resource');
require('angular-route');
require('angular-selectizejs');
require('angular-utils-pagination');
require('angular-flash-alert');
alertify = require('alertifyjs');

// angular
require('./responsavel/responsavel.module');
require('./processo/processo.module');
require('./app.module');