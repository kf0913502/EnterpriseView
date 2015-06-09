imAPP.controller('studentHomeController', function($scope, $http, $routeParams, $filter, $rootScope, $location) {
    
   console.log("studentHomeController");
    $scope.navigate = function(){
        var request = $http({
            method: "Get",
            dataType: "json",
            url: '/im/api/studentInternship/' + $rootScope.loggedInUser.id
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
             $location.path("/studentView");      
        });
        
        request.error(function(response, status, headers, config) {
           // console.log(response, status, config);
            $location.path("/studentRegister");    
        });
    };
    
    $scope.navigate();
});
