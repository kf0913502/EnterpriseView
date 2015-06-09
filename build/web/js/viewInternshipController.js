imAPP.controller('viewInternshipController', function($scope, $http, $routeParams, $filter, $rootScope) {
    
   var request = $http({
            method: "Get",
            dataType: "json",
            url: '/im/api/studentInternship/' + $rootScope.loggedInUser.id
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
            $scope.internship = response;   
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
              
        });
    
});
