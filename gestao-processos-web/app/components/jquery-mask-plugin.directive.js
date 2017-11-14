'use strict';

angular.module('app').directive('mask', MaskDirective);

MaskDirective.$inject = [];
/* @ngInject */
function MaskDirective() {

    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            var $elm = $(element);
            $elm.mask(attrs.mask);

            $elm.on('blur', function() {
                var val = $(this).val();
                if (val.length !== attrs.mask.length) {
                    ngModel.$setViewValue('');
                    ngModel.$render();
                }
            });

            $elm.on('keyup', function() {
                var val = $(this).val();
                ngModel.$setViewValue(val);
                ngModel.$render();
            });
        }
    };
}