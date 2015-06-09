
var imAPP = angular.module('imAPP', ['ngRoute']);
imAPP.config(function($routeProvider) {
    $routeProvider
            .when('/',
                    {
                        templateUrl: '/im/partials/login.html',
                        controller:  'loginController'
                    })
                    .when('/studentRegister',
                    {
                        templateUrl: '/im/partials/register-internship.html',
                        controller: 'studenRegisterController'
                    })
                    .when('/studentHome',
                    {
                         templateUrl: '/im/partials/login.html',
                        controller: 'studentHomeController'
                        
                    })
                    .when('/coordinatorInternships',
                    {
                        templateUrl: '/im/partials/internships.html',
                        controller: 'internshipsController'
                    })
                    .when('/coordinatorConfirm',
                    {
                        templateUrl: '/im/partials/confirm-internship.html',
                        controller: 'confirmInternshipController'
                    })
                    .when('/coordinatorAssign',
                    {
                        templateUrl: '/im/partials/assign-examiner.html',
                        controller: 'assignExaminerController'
                    })       
                    .when('/examinerGrade',
                    {
                        templateUrl: '/im/partials/grading.html',
                        controller: 'gradingController'
                    })
                    .when('/studentGrade',
                    {
                        templateUrl: '/im/partials/view-grade.html',
                        controller: 'viewGradeController'
                    })                       
                    .when('/studentView',
                    {
                        templateUrl: '/im/partials/view-internship.html',
                        controller: 'viewInternshipController'
                    })   
            .otherwise({redirectTo: '/'});
});

imAPP.run(function($rootScope, $location) {
    $rootScope.isLoggedIn = function() {
        return !angular.isUndefined($rootScope.loggedInUser);
    };
    
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
      if (!$rootScope.isLoggedIn()) {
          $location.path("/");
      }
    });
 });
  


