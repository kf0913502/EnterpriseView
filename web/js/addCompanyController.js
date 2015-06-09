imAPP.controller('addCompanyController', function($scope, $http, $routeParams, $filter, $rootScope, $location) {
    
      $scope.addCompanySubmit = function(){
       console.log($scope.company);
       
       var request = $http({
           method: "Post",
           dataType: "json",
           data: $scope.company,
           url: '/im/api/addCompany'
       });  
       
        request.success(function(response, status, headers) {
            console.log(response);
             $location.url("/coordinatorConfirm?internshipId=" + $routeParams.internshipId);  
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
        });
        
        
   }
    
});
