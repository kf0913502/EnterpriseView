imAPP.controller('assignExaminerController', function($scope, $http, $routeParams, $filter, $rootScope,$location) {
    
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
    
       $scope.getFaculty = function(){
      var request = $http({
            method: "Get",
            dataType: "json",
            url: '/im/api/faculty/'
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
            
            $scope.faculty = response;
            for (var i=0; i<$scope.faculty.length; i++)
                if ($scope.faculty[i].id == $scope.internship.examiner.id)
                    $scope.internship.examiner = $scope.faculty[i];
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
             
        });
    };
    
    
    
     $scope.assignSubmit = function(){
       console.log($scope.internship);
       
       var request = $http({
           method: "Put",
           dataType: "json",
           data: $scope.internship,
           url: '/im/api/assignExaminer'
       });  
       
        request.success(function(response, status, headers) {
            console.log(response);
             $location.url("/coordinatorInternships");
        });
        
        request.error(function(response, status, headers, config) {
            console.log(response, status, config);
        });
        
        
   }
    
    $scope.getInternship();
    $scope.getFaculty();
    
});
