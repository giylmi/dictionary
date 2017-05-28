(function() {
    'use strict';

    angular
        .module('dictionaryApp')
        .controller('WordDetailController', WordDetailController);

    WordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Word', 'Category'];

    function WordDetailController($scope, $rootScope, $stateParams, previousState, entity, Word, Category) {
        var vm = this;

        vm.word = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dictionaryApp:wordUpdate', function(event, result) {
            vm.word = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
