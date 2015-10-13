(function(){
	'use strict';
	
	angular.module('shApp').controller('shCtrl',['$scope','SHElementService',scopefn]);
	
	function scopefn($scope,SHElementService){
		var editCtrl = this;
	$scope.listInfo = GBElementService.getElement({element: "listInfo"},
			$scope.setBudget="";
			$scope.setType="";
			function(result, responseHeaders){
				return result;
			});
		$scope.getBudget=function(){
			var budget = new SHElementService();
			budget.action = "addBudget";
			budget.text = $scope.setBudget;
			$scope.setBudget="";
			budget.$postElement(
					function(newBudget,responseHeaders){
						$scope.type = newType;
						return type;
					},
		}
		$scope.getType=function(){
			var type = new SHElementService();
			type.action = "addType";
			type.text=$scope.setType;
			$scope.setType="";
			type.$postElement(
			function(newType,responseHeaders){
				$scope.type = newType;
				return type;
			},
		}

	};
})();