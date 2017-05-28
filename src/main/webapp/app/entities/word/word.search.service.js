(function() {
    'use strict';

    angular
        .module('dictionaryApp')
        .factory('WordSearch', WordSearch);

    WordSearch.$inject = ['$resource'];

    function WordSearch($resource) {
        var resourceUrl =  'api/_search/words/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
