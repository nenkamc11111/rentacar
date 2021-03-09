var app = angular.module('myApp', []);

app.controller('AutoController', function($scope, $http) {
	

		  $http.get('http://127.0.0.1:8082/auto')
		  .then(function(response) {
		    $scope.menus = response.data;
		  })
		  .catch(function(response) {
		    console.error('Gists error', response.status, response.data);
		  })
		  .finally(function() {
		    console.log("finally finished gists");
		  });
		  
 
 
});
  