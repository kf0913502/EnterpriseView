/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


imAPP.controller('loginController', function($scope, $http, $rootScope, $location) {
    
    $scope.login = function () {
        
        var request = $http({
            method: "Post",
            dataType: "json",
            data: $scope.credentials,
            url: '/im/api/login'
        });
        
        request.success(function(response, status, headers) {
            console.log(response);
            $rootScope.loggedInUser = response;
            if (headers("type") == "class ims.entity.Student")
            {
                
                $location.path("/studentHome");
            }
            else
            {
                if ($rootScope.loggedInUser.isCoordinator == 1)
                    $location.path("/coordinatorInternships");
                else
                    $location.path("/examinerGrade");
            }
            
            
        });
                
        request.error(function(response, status, headers, config) {
            $scope.message = "Invalid Credentials";
            //console.log(response, status, config);
        });

        
    };
});