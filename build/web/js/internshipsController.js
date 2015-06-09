imAPP.controller('internshipsController', function($scope, $http, $routeParams, $filter, $rootScope, $location) {
    
    $scope.currentStatus = "all";
    $scope.stats = ["all", "pending", "confirmed"];
    $location.url($location.path());
    $scope.getInternships = function(){
        
        console.log($scope.currentStatus);
      var request = $http({
            method: "Get",
            dataType: "json",
            url: '/im/api/internships/' + $scope.currentStatus
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
             $scope.internships = response;   
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);

        });
    };
    
    $scope.getInternships();
    
    
});
