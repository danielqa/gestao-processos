'use strict';

angular.module('app').directive('datePicker', DatePickerDirective);

DatePickerDirective.$inject = ['$filter'];
/* @ngInject */
function DatePickerDirective($filter) {

    var DATEPICKER_OPTS = {
        format: 'dd/mm/yyyy',
        language: 'pt-BR',
        weekStart: 0,
        todayHighlight: true,
        autoclose: true
    };

    return {
        require: 'ngModel',
        scope: {
            dataInicial: '=',
            dataFinal: '='
        },
        link: function (scope, element, attrs, ngModelCtrl) {
            var $elm = $(element);

            scope.$watch('dataInicial', function () {
                $elm.datepicker('setStartDate', scope.dataInicial);
            });

            scope.$watch('dataFinal', function () {
                $elm.datepicker('setEndDate', scope.dataFinal);
            });

            var opts = angular.extend({}, DATEPICKER_OPTS, {
                startDate: attrs['dataInicial'] || '01/01/1900',
                endDate: attrs['dataFinal'] || '31/12/2099'
            });

            function triggerHandler() {
                angular.element(element).triggerHandler('input');
            }

            $elm
                .on('changeDate', triggerHandler)
                .attr('maxlength', 10)
                .mask('00/00/0000')
                .datepicker(opts);

            ngModelCtrl.$formatters.unshift(function (value) {
                return $filter('date')(value, 'dd/MM/yyyy');
            });

            ngModelCtrl.$parsers.push(function (value) {
                return value ? moment(value, "DD/MM/YYYY").toDate() : '';
            });
        }
    };
}
