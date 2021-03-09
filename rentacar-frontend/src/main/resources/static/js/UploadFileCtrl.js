// CONTROLLER UPLOAD FILE
mainApp.controller('UploadFileController', function($scope, $http) {
 
    $scope.uploadResult ="";
     
    $scope.myForm = {
        description: "",
        files: []
    }
 
    $scope.doUploadFile = function() {  
 
        var url = "/rest/uploadMultiFiles";
        var data = new FormData();
 
        data.append("bild", $scope.myForm.description);
        data.append("mark", $scope.mark);
        data.append("filiale",$scope.mark );
        data.append("kmstand", $scope.kmstand);
        data.append("zustand", $scope.zustand);
        data.append("preis", $scope.preis);
        data.append("beschreibung", $scope.preis);
        
        data.append("description", $scope.myForm.description);
        for (i = 0; i < $scope.myForm.files.length; i++) {
            data.append("files", $scope.myForm.files[i]);
        }
 
        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }
        
        var dataJSON = {
			bild:$scope.myForm.files[0].name,
			mark: $scope.mark,
			kmstand: $scope.kmstand,
			zustand: $scope.zustand,
			preis:$scope.preis,
			filiale:$scope.filiale,
			beschreibung:$scope.myForm.description
		};
 
        $http.post(url, data, config).then(
            // Success
            function(response) {
                $scope.uploadResult =  response.data;
                $http.post('http://127.0.0.1:8082/auto', JSON.stringify(dataJSON)).then(function (response) {

				if(response.data)
					$scope.msg = "Post Data Submitted Successfully!";
					alert($scope.msg)
				}, function (response) {
		
				$scope.msg = "Service not Exists";
				$scope.statusval = response.status;
				$scope.statustext = response.statusText;
				console.error('Post error', response.status, response.data);
		
				});
            },
            // Error
            function(response) {
                $scope.uploadResult = response.data;
            });
    };
 
});
