(function () {
    'use strict';

    angular
        .module('app')
        .controller('ImageController', ImageController);

    //LoginController.$inject = ['authservice'];
    // nos parâmetros coloco o que vou precisar utilizar
    function ImageController($scope, $http, Upload) {
        
        $scope.onFileSelect = function(files) {
            
            if (files.length > 0) {
                var filename = files[0].name;
                var type = files[0].type;
                var query = {
                    filename: filename,
                    type: type
                };
                $http.post('http://localhost:9090/upload', query)
                    .success(function(result) {
                        Upload.upload({
                            url: result.url, //s3Url
                            transformRequest: function(data, headersGetter) {
                                var headers = headersGetter();
                                delete headers.Authorization;
                                return data;
                            },
                            fields: result.fields, //credentials
                            method: 'POST',
                            file: files[0]
                        }).progress(function(evt) {
                            console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total));
                        }).success(function(data, status, headers, config) {
                            // file is uploaded successfully
                            console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
                        }).error(function() {
        
                        });
                    })
                    .error(function(data, status, headers, config) {
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                    });
            }
        } 
        
        // Upload.upload({
        //     url: 'upload',
        //     fields: {'username': 'zouroto'}, // additional data to send
        //     file: file
        // }).progress(function (evt) {
        //     var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
        //     console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
        // }).success(function (data, status, headers, config) {
        //     console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
        // });

            
    }

}());