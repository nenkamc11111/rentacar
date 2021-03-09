	var app = angular.module('demo', []);

	app.controller('Hello', function ($scope, $http) {
		$scope.abholdatum=null;
		$scope.ruckgabedatum=null;
		$scope.idauto=null;
		$scope.idkunde=null;

		$scope.charger = function (idauto, idkunde, ruckgabedatum, abholdatum) {

		var data = {
			abholdatum: $scope.abholdatum,
			ruckgabedatum: $scope.ruckgabedatum,
			idauto: $scope.idauto,
			idkunde: $scope.idkunde
		};

		//Call the services

		$http.post('http://127.0.0.1:8083/api/mieten/addmieten', JSON.stringify(data)).then(function (response) {

		if(response.data)
			$scope.msg = "Post Data Submitted Successfully!";
			alert($scope.msg)
		}, function (response) {

		$scope.msg = "Service not Exists";
		$scope.statusval = response.status;
		$scope.statustext = response.statusText;
		console.error('Post error', response.status, response.data);

		});
	};
	
	$scope.handleLogin = function(){
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
	
	$scope.chargerLogin = function (email, password) {

		var data = {
			email: $scope.email,
			password: $scope.password,
		};
	
		  $http.get('http://127.0.0.1:8081/login').
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