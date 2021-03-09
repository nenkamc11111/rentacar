var app = angular.module('myApp2', []);

app.controller('MietenController', function($scope, $http) {
	
	

	$scope.charger = function(){
		  $http.get('http://127.0.0.1:8083/api/mieten/all')
		  .then(function(response) {
		    $scope.menus = response.data;
		    alert($scope.menus);
		  })
		  .catch(function(response) {
			  alert('Problem de connexion avec e serveur');
		    console.error('Gists error', response.status, response.data);
		  })
		  .finally(function() {
			 alert('Problem de connexion avec e serveur');
		    console.log("finally finished gists");
		  });
	};
 
  
});


angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    $http.get('http://127.0.0.1:8083/api/mieten/all').
        then(function(response) {
            $scope.menus = response.data;
            })
        .catch(function(response) {
			  alert('Problem de connexion avec e serveur');
		    console.error('Gists error', response.status, response.data);
		  })
		  .finally(function() {
		    console.log("finally finished gists");
		  });
});

angular.module('App', [])
  .controller('Ctrl', function($scope, $http){
    $http.get('http://127.0.0.1:8083/api/mieten/all').then(function(articlesResponse) {
      $scope.menus = articlesResponse.data;
    });
  });
  
  
  
  
  var app = angular.module('demo', []);

app.controller('Hello', function($scope, $http) {
	
	
	$scope.charger = function(){
		
	        var $dataModel ={
	            abholdatum: $scope.abholdatum,
	            ruckgabedatum: $scope.ruckgabedatum,
	            idauto: $scope.idauto,
	            idkunde: $scope.idkunde
	        };
		
		  $http.post('http://127.0.0.1:8083/api/mieten/addmieten',JSON.stringify($dataModel))
		  .then(function(response) {
			  console.error('Post error', response.status, response.data);

		  })
		  .catch(function(response) {
		  		  $http.post('http://127.0.0.1:8083/api/mieten/addmieten',JSON.stringify($dataModel))
		  
			 alert('Problem de connexion avec e 11serveur'+$scope.abholdatum);
		    console.error('Gists error',response.headers, response.status, response.data);
		  })
		  .finally(function() {
			alert('Problem de connexion avec e serveur');
		    console.log("finally finished gists");
		  });
	};
	
	
	$scope.chargerListe = function(){
		  $http.get('http://127.0.0.1:8083/api/mieten/all').
        then(function(response) {
            $scope.menus = response.data;
            })
        .catch(function(response) {
			  alert('Problem de connexion avec e serveur');
		    console.error('Gists error', response.status, response.data);
		  })
		  .finally(function() {
		    console.log("finally finished gists");
		  });
	};
 
});
  