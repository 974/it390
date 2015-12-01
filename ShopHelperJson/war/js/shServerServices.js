(function(){
	'use strict';
	
	var shServerServices=angular.module('shServerServices',['ngResource']);
	
	shServerServices.factory('SHElementService',['$resource',
	  function($resource){
		return $resource('/ShopHelperServletJSON/',{},{
			getElement:{method:'GET',isArray:false,responseType:"json"},
			postElement: {method:'POST',isArray:false, responseType:"json"}
	});
		
	}]);

})();
	