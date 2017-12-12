(function () {
    'use strict';

    angular
        .module('app')
        .factory('MapService', mapService);

    function mapService($http) {

        var mapService = {
            search: search,
        }
        $http.defaults.useXDomain = true;

        var urlBase = 'https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Vict&types=(cities)&language=pt_BR&key=AIzaSyDO99In3SwksPKvou3iGqaiWTVfepqxxSs'; 

        return mapService;

        function search(endereco) {
            return $http.get(`https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Azurra&language=pt_BR&key=AIzaSyDO99In3SwksPKvou3iGqaiWTVfepqxxSs`);
        }

        function search2() {
            return $http({
                async: true,
                crossDomain: false,
                url: "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Azzura&language=pt_BR&key=AIzaSyDO99In3SwksPKvou3iGqaiWTVfepqxxSs",
                method: "GET",
                headers: {
                  cache: "no-cache"
                }
            });
         }
    }

}());