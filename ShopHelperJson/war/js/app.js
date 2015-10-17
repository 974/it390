(function() {
  var app = angular.module('myApp', ['serverRequests']);
  
  app.controller('shopHelper', ['$http', function($http){
    var list = this;
    
    list.continental breakfast= [];
    list.thanksgiving dinner = [];
    list.back to school clothes = [];
    list.halloween party = [];
    
    $http.get('continental_breakfast.json').success(function(data){
      list.continental_breakfast = data;
    });
  
    $http.get('thanksgiving_dinner.json').success(function(data){
      list.thanksgiving_dinner = data;
    });
  
    $http.get('back_to_school_clothes.json').success(function(data){
      list.back_to_school_clothes = data;
    });
    
    $http.get('halloween_party.json').success(function(data){
        list.halloween_party = data;
      });
  }]);
})();