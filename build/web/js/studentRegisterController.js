imAPP.controller('studenRegisterController', function($scope, $http, $routeParams, $filter, $rootScope, $location) {
    
   $scope.internship = {"student" : $rootScope.loggedInUser};
   
   $scope.getCompanies = function(){
            var request = $http({
           method: "Get",
           dataType: "json",
           url: '/im/api/companies'
       });  
       
       request.success(function(response, status, headers) {
            console.log(response);
             $scope.companies = response;    
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
        });
   };
   
   $scope.registerSubmit = function(){
       console.log($scope.internship);
       
       var request = $http({
           method: "Post",
           dataType: "json",
           data: $scope.internship,
           url: '/im/api/registerInternship'
       });  
       
        request.success(function(response, status, headers) {
            console.log(response);
             $location.path("studentView");  
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
        });
        
        
   }
    
    $scope.getCompanies();
});
