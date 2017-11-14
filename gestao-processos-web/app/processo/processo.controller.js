'use strict';

angular.module('app.processo').controller('ProcessoListCtrl', ProcessoListCtrl);

ProcessoListCtrl.$inject = ['$scope', '$route', '$location', '$http', '$filter', 'Flash', 'Processo', 'EnumService', 'CONTEXT'];
/* @ngInject */
function ProcessoListCtrl($scope, $route, $location, $http, $filter, Flash, Processo, EnumService, CONTEXT) {

    $scope.delete = function (processo) {
        alertify.confirm('Confirma exclusão do registro?', function () {
            Processo.delete({id: processo.id}, function (response) {
                $scope.currentPage = 1;
                Flash.create('success', response.messages);
                $route.reload();
            });
        });
    };

    $scope.consultar = function (newPageNumber) {
        $location.url($location.path()); // Limpa os filtros da url

        $location.search('page', newPageNumber || 1); // Default primeira página
        if ($scope.filtro.numero) {
            $location.search('numero', $scope.filtro.numero);
        }
        if ($scope.filtro.dataDistribuicaoInicio) {
            $location.search('dataDistribuicaoInicio', $filter('date')($scope.filtro.dataDistribuicaoInicio, "dd/MM/yyyy"));
        }
        if ($scope.filtro.dataDistribuicaoFim) {
            $location.search('dataDistribuicaoFim', $filter('date')($scope.filtro.dataDistribuicaoFim, "dd/MM/yyyy"));
        }
        if ($scope.filtro.situacao) {
            $location.search('situacao', $scope.filtro.situacao);
        }
        if ($scope.filtro.segredoJustica) {
            $location.search('segredoJustica', $scope.filtro.segredoJustica);
        }
        if ($scope.filtro.pastaFisicaCliente) {
            $location.search('pastaFisicaCliente', $scope.filtro.pastaFisicaCliente);
        }
        if ($scope.filtro.nomeResponsavel) {
            $location.search('nomeResponsavel', $scope.filtro.nomeResponsavel);
        }
    };

    $scope.find_processos = function (filtros) {
        return $http({
            method: 'GET',
            url: CONTEXT + '/processo/filtros',
            params: filtros
        }).success(function (response) {
            $scope.processos = response.content;
            $scope.totalItems = response.totalElements;
            $scope.pageSize = response.size;
        });
    };

    angular.element(document).ready(function () {
        var params = $location.search();
        if (Object.keys(params).length > 0) {
            $scope.currentPage = params.page;

            $scope.filtro = {
                numero: params.numero,
                dataDistribuicaoInicio: params.dataDistribuicaoInicio,
                dataDistribuicaoFim: params.dataDistribuicaoFim,
                situacao: parseInt(params.situacao),
                segredoJustica: params.segredoJustica,
                pastaFisicaCliente: params.pastaFisicaCliente,
                nomeResponsavel: params.nomeResponsavel
            };

            $scope.find_processos(params);
        }

        EnumService.query({className: 'br.com.softplan.sajadv.processos.enumeration.Situacao'}, function (response) {
            $scope.situacoes = response;
        });
    });
}

angular.module('app.processo').controller('ProcessoFormCtrl', ProcessoFormCtrl);

ProcessoFormCtrl.$inject = ['$scope', '$route', '$routeParams', 'Flash', 'Processo', 'ProcessoService', 'Responsavel', 'EnumService'];
/* @ngInject */
function ProcessoFormCtrl($scope, $route, $routeParams, Flash, Processo, ProcessoService, Responsavel, EnumService) {

    $scope.save = function () {
        before_save();
        $scope.processo.$save(function (response) {
            Flash.create('success', response.messages);
            $route.reload();
        });
    };

    var before_save = function () {
        if ($scope.processo.processoPai && $scope.processo.processoPai.id) {
            $scope.processo.processoPai = {id: $scope.processo.processoPai.id};
        } else {
            $scope.processo.processoPai = null;
        }
    };

    (function () {
        $scope.responsaveisConfig = {
            persist: false,
            selectOnTab: true,
            maxItems: 3,
            labelField: 'nome',
            valueField: 'id',
            sortField: 'nome',
            searchField: 'nome'
        };

        $scope.responsaveis_options = function () {
            return Responsavel.query();
        };

        $scope.processoPaiConfig = {
            persist: false,
            selectOnTab: true,
            labelField: 'dados',
            valueField: 'id',
            sortField: 'dados',
            searchField: 'dados'
        };

        $scope.processo_pai_options = function () {
            return ProcessoService.findProcessosVinculaveis($routeParams.id);
        };
    })();

    angular.element(document).ready(function () {
        $scope.isEdit = angular.isDefined($routeParams.id);

        if ($scope.isEdit) {
            Processo.get({id: $routeParams.id}, function (processo) {
                $scope.processo = processo;
                $scope.isFinalizado = processo.situacao.finalizado;
                $scope.processo.situacao = processo.situacao.id;
            });
        } else {
            $scope.processo = new Processo();
        }

        EnumService.query({className: 'br.com.softplan.sajadv.processos.enumeration.Situacao'}, function (response) {
            $scope.situacoes = response;
        });
    });
}
