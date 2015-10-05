var module = angular.module('ServerRequests');

  module.service('UserElementService', ["$http", '$rootScope', '$sce', function($http, $rootScope, $sce) {

var service=this;  

this.getCurrentElement = function() {

return service.currentElement;

};

this.setCurrentElement = function(element) {

if (element!==null) {

//service.currentElement.elementName = element.name;

service.currentElement.element = element;

service.currentElement.prettyname = $sce.trustAsHtml(service.prettyname(element.name));

service.currentElement.prettyTypeName = $sce.trustAsHtml(service.prettyname("[["+element.typeName+"]]"));

service.currentElement.jsonDescription=JSON.stringify(element, null, 2);

service.currentElement.prettyJsonDescription=$sce.trustAsHtml(service.prettyname(service.currentElement.jsonDescription));

if (element.description!==null) {

service.currentElement.description = $sce.trustAsHtml(service.prettyname(element.description));

}

service.currentElement.prettyname = $sce.trustAsHtml(service.prettyname(element.name));

    service.rootScope.$broadcast('CURRENT_ELEMENT_CHANGED');

}

};

// name assumed correct - otherwise error

this.refreshElement = function(name) {

service.setCurrentElementName(name);  

$http({method: 'JSONP', url:'/edit/user/getjson?callback=JSON_CALLBACK&name='+name})

.success(function(data, status) {

    if (data !== null) {

    service.setCurrentElement(data);

        }

    if (DEBUG) console.log("SUCCESS for "+service.currentElement.elementName+" is "+JSON.stringify(data) || "Request succeeded")

    }).error(function(data, status) {

    if (DEBUG) console.log(data || "Request failed");

        }); 

    };

  // element assumed correct - otherwise error

  this.saveElement = function(element,selectElementName) {

  if (DEBUG) {

//console.log("Element:"+JSON.stringify(element));

//console.log("SelectElementName:"+selectElementName);

  }

  data={};

  if (typeof(element)==="string") {

  data.element=element;

  } else {

  data.element=JSON.stringify(element); 

  }

  data.selectElementName = selectElementName;

  return $http.post('/edit/user/savejson',

      data,

      {responseType:"application/json"})

      .success(function(data, status) {

      if (data !== null) {

      service.setCurrentElement(data.selectedElement);

      if (DEBUG) console.log("SUCCESS"+JSON.stringify(data)  || "Request succeeded");

          } else {

          if (DEBUG) console.log("ERROR: Request succeeded but with empty data.");

          }

      }).error(function(data, status) {

      if (DEBUG) console.log(data || "Request failed");

          });

  };

     

    // element assumed correct - otherwise error

  this.removeElement = function(removedElementName,selectElementName) {

  if (DEBUG) {

  console.log("--- REMOVE ELEMENT ---");

console.log("Remove element with name:"+removedElementName);

console.log("Select element with name:"+selectElementName);

  }

  data={};

  data.removedElementName=removedElementName; 

  data.selectElementName = selectElementName;

  return $http.post('/edit/user/removejson',

      data,

      {responseType:"application/json"})

      .success(function(data, status) {

      if (data !== null) {

      service.setCurrentElement(data.selectedElement);

      if (DEBUG) console.log("SUCCESS"+JSON.stringify(data)  || "Request succeeded");

          } else {

          if (DEBUG) console.log("ERROR: Request succeeded but with empty data.");

          }

      }).error(function(data, status) {

      if (DEBUG) console.log(data || "Request failed");

          });

      };   

     

 

  }]);
