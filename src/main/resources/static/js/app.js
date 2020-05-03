'use strict';

// Creating angular Application with module name "GoogleOAuthMicroservice"
var app = angular.module('SocialOAuth2Microservice',[]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);