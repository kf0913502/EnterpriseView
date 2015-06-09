imAPP.controller('confirmInternshipController', function($scope, $http, $routeParams, $filter, $rootScope, $location) {
    $scope.getInternship = function(){
      var request = $http({
            method: "Get",
            dataType: "json",
            url: '/im/api/internship/' + $routeParams.internshipId
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
            $scope.internship = response;
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
             
        });
    };
    
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
   
   
     $scope.confirmSubmit = function(){
       console.log($scope.internship);
       
       var request = $http({
           method: "Put",
           dataType: "json",
           data: $scope.internship,
           url: '/im/api/confirmInternship'
       });  
       
        request.success(function(response, status, headers) {
            console.log(response);
             $location.url("/coordinatorInternships");
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
        });
        
        
   }
   
   
   $scope.getCompanies();
    $scope.getInternship();
});
