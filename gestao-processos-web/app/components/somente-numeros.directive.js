'use strict';

angular.module('app').directive('somenteNumeros', SomenteNumerosDirective);

SomenteNumerosDirective.$inject = [];
/* @ngInject */
function SomenteNumerosDirective() {

    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (value) {
                var transformedInput = value.replace(/[^\d]/g, '');

                if (transformedInput != value) {
                    ngModel.$setViewValue(transformedInput);
                    ngModel.$render();
                }

                return transformedInput;
            });
        }
    };
}