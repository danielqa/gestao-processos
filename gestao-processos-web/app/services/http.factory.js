'use strict';

angular.module('app').factory('HttpService', HttpService);

HttpService.$inject = ['$timeout'];
/* @ngInject */
function HttpService($timeout) {

    var requests = 0;

    function pushRequest() {
        requests++;
    }

    function popRequest() {
        $timeout(function () {
            requests--;
        }, 500);
    }

    function hasPendingRequests() {
        return requests > 0;
    }

    return {
        hasPendingRequests: hasPendingRequests,
        pushRequest: pushRequest,
        popRequest: popRequest
    };
}
