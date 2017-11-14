'use strict';

angular.module('app.responsavel').controller('ResponsavelListCtrl', ResponsavelListCtrl);

ResponsavelListCtrl.$inject = ['$scope', '$route', '$location', '$http', 'Flash', 'Responsavel', 'CONTEXT'];
/* @ngInject */
function ResponsavelListCtrl($scope, $route, $location, $http, Flash, Responsavel, CONTEXT) {

    $scope.delete = function (responsavel) {
        alertify.confirm('Confirma exclusão do registro?', function () {
            Responsavel.delete({id: responsavel.id}, function (response) {
                $scope.currentPage = 1;
                Flash.create('success', response.messages);
                $route.reload();
            });
        });
    };

    $scope.consultar = function (newPageNumber) {
        $location.url($location.path()); // Limpa os filtros da url

        $location.search('page', newPageNumber || 1); // Default primeira página
        if ($scope.filtro.nome) {
            $location.search('nome', $scope.filtro.nome);
        }
        if ($scope.filtro.cpf) {
            $location.search('cpf', $scope.filtro.cpf);
        }
        if ($scope.filtro.numeroProcesso) {
            $location.search('numeroProcesso', $scope.filtro.numeroProcesso);
        }
    };

    $scope.find_responsaveis = function (filtros) {
        return $http({
            method: 'GET',
            url: CONTEXT + '/responsavel/filtros',
            params: filtros
        }).success(function (response) {
            $scope.responsaveis = response.content;
            $scope.totalItems = response.totalElements;
            $scope.pageSize = response.size;
        });
    };

    angular.element(document).ready(function () {
        var params = $location.search();
        if (Object.keys(params).length > 0) {
            $scope.currentPage = params.page;

            $scope.filtro = {
                nome: params.nome,
                cpf: params.cpf,
                numeroProcesso: params.numeroProcesso
            };

            $scope.find_responsaveis(params);
        } else {
            $scope.filtro = {};
        }
    });
}

angular.module('app.responsavel').controller('ResponsavelFormCtrl', ResponsavelFormCtrl);

ResponsavelFormCtrl.$inject = ['$scope', '$route', '$routeParams', 'Flash', 'Responsavel', 'ResponsavelService'];
/* @ngInject */
function ResponsavelFormCtrl($scope, $route, $routeParams, Flash, Responsavel, ResponsavelService) {

    $scope.save = function () {
        $scope.responsavel.$save(function (response) {
            Flash.create('success', response.messages);
            $route.reload();
        });
    };

    angular.element(document).ready(function () {
        $scope.isEdit = angular.isDefined($routeParams.id);

        if ($scope.isEdit) {
            Responsavel.get({id: $routeParams.id}, function (responsavel) {
                $scope.responsavel = responsavel;
            });

            ResponsavelService.getProcessos($routeParams.id).success(function (response) {
                $scope.processos = response;
            });
        } else {
            $scope.responsavel = new Responsavel();
        }
    });
}
