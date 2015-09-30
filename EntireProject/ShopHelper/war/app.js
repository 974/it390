(function() {
  var app = angular.module('myApp', []);
  
  app.controller('shopHelper', ['$http', function($http){
    var list = this;
    
    list.food = [];
    list.clothing = [];
    list.data = [];
    
    $http.get('food.json').success(function(data){
      list.food = data;
    });
  
    $http.get('clothing.json').success(function(data){
      list.clothing = data;
    });
  
    $http.get('party.json').success(function(data){
      list.party = data;
    });
  }]);
})();
