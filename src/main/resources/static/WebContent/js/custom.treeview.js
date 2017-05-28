'use strict';
(function(){
  
  //angular module
  var myApp = angular.module('myApp', ['angularTreeview',
                                       'shared',
                                       'viewnode']);
  
  /* Constant to be removed later */
  myApp.constant('DATA',{
	  BOOKMARKLIST:[{"name":"Google Page","url":"www.google.com","folder":"1"},{"name":"Yahoo Page","url":"www.yahoo.com","folder":"1"}],
	  FOLDER:[{"id":1,"name":"common"},{"id":2,"name":"Entertainment"}]
  });
  
  /***************** Newly Added Start******************************/
  myApp.config([
              '$stateProvider',
              '$urlRouterProvider',
              '$httpProvider',
               function($stateProvider, $urlRouterProvider,$httpProvider){
            	   $urlRouterProvider.otherwise("/viewnode");	 
            	   
            	   /*
                   * Enable cross domain calls
                   */
            	   $httpProvider.defaults.useXDomain = true;
               }
            ]);
  
  /***************** Newly Added End******************************/
  //test controller
  myApp.controller('myController', function($scope, $http){
  
   $scope.$watch('search', function() {
      fetch();
    });    
      
    //temporary node
    $scope.temporaryNode = {
        children: []
    };

  	//test tree model
    /*$scope.roleList = [
        { label : "User", id : "role1", children : [
          { label : "subUser1", id : "role11", children : [] },
          { label : "subUser2", id : "role12", children : [
            { label : "subUser2-1", id : "role121", children : [
              { label : "subUser2-1-1", id : "role1211", children : [] },
              { label : "subUser2-1-2", id : "role1212", children : [] }
            ]}
          ]}
        ]},

        { label : "Admin", id : "role2", children : [] },

        { label : "Guest", id : "role3", children : [] }
      ];*/
      
      
    function fetch() {        
      $http.get("http://localhost:8080/VanillaTest/api/verify")
        .then(function(response) {
          $scope.roleList = response.data;
        });
    }
      
      
      $scope.done = function () {
          /* reset */
          $scope.mytree.currentNode.selected = undefined;
          $scope.mytree.currentNode = undefined;
          alert($scope.roleList);
          $scope.mode = undefined;
      };
      
      $scope.addChildDone = function () {
          /* add child */
          if( $scope.temporaryNode.id && $scope.temporaryNode.label ) {
              $scope.mytree.currentNode.children.push( angular.copy($scope.temporaryNode) );
          }
          
          /* reset */
          $scope.temporaryNode.id = "";
          $scope.temporaryNode.label = "";
          
          $scope.done();
      };
  
  });
  
})();