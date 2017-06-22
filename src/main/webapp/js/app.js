var app = angular.module('todoListApp', [
  'todoListApp.controllers',
  'todoListApp.services',
  '720kb.datepicker',
  'ngRoute'
]);
app.config(function($routeProvider) {
  $routeProvider.
	when("/todos", {templateUrl: "view/html/todoList.html", controller: "todoController"}).
	when("/todos/:id/edit", {templateUrl: "view/html/editForm.html", controller: "todoController"}).
	when("/todos/create" , {templateUrl: "view/html/createForm.html", controller: "todoController"}).
        otherwise({redirectTo: '/todos'});
});

app.filter('dateFilter', function() {
    return function(input, date) {
        
        if (typeof date === 'undefined' || date === "")
        {
            return input;
        }
        var result = [];
        var day = date.charAt(8) + date.charAt(9);
        var year = "";
        for (var i = 11; i < 15; i++)
        {
            year += date.charAt(i);
        }
        var monthStr = date.charAt(4) + date.charAt(5) + date.charAt(6);
        var month = String(getMonthFromString(monthStr));
        
        for(var j = 0; j < input.length; j++)
        {
            var dayStr = String(input[j].resolveUntil.dayOfMonth);
            var monthStr = String(input[j].resolveUntil.monthValue);
            var yearStr = String(input[j].resolveUntil.year);
            if(day === dayStr && month === monthStr && year === yearStr)
            {
                result.push(input[j]); 
            }
        }
        return result;
       
        
    };
    
    function getMonthFromString(mon) {
                return new Date(Date.parse(mon + " 1, 2012")).getMonth() + 1;
            }
});

