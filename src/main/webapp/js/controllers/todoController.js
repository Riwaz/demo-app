angular.module('todoListApp.controllers', []).
        controller('todoController', function ($scope, $location, $route, $routeParams, todoService) {

            'use strict';
            $scope.todoList = todoService.todos;
            $scope.editedTodo = null;
            $scope.editedDate = "";
            $scope.numberTodos = 0;
            

            todoService.getTodos().then(function (response) {
                $scope.todoList = response;
                $scope.numberTodos = $scope.todoList.length;
            });

            if (typeof $routeParams.id !== 'undefined')
            {
                getTodo();
            }

            $scope.finishTodo = function (id) {

                todoService.finish(id)
                        .then(function success() {
                            $route.reload();
                        });
            };

            $scope.addTodo = function () {

                var resolveDate = getDate();
                var newTodo = {
                    name: $scope.nameI,
                    description: $scope.descriptionI,
                    resolveUntil: resolveDate,
                    resolved: false,
                    javaClass: "app.model.Todo"
                };

                todoService.insert(newTodo)
                        .then(function success() {
                            $location.path("/");
                        });
            };

            $scope.editTodo = function () {

                var resolveDate = getDate();
                var newTodo = {
                    id: $scope.editedTodo.id,
                    name: $scope.nameI,
                    description: $scope.descriptionI,
                    resolveUntil: resolveDate,
                    resolved: $scope.editedTodo.resolved,
                    javaClass: "app.model.Todo"
                };

                todoService.edit(newTodo)
                        .then(function success() {
                            $scope.editedTodo = null;
                            $scope.editedDate = "";
                            $location.path("/");
                        });
            };

            $scope.removeTodo = function (todo) {
                todoService.delete(todo)
                        .then(function success() {
                            $route.reload();
                        });
            };
            
            $scope.getBadge = function (isFinished) {
                if(isFinished === true){
                    return "Finished"; 
                }
                return "In progress";
            };
            
            $scope.dataAvabillity = function (){
                if($scope.numberTodos === 0){
                    return false;
                }
                return true;
            };

            function getTodo() {

                todoService.getTodos().then(function (response) {
                    $scope.todoList = response;
                });

                for (var i = 0; i < $scope.todoList.length; i++)
                {
                    if (String($scope.todoList[i].id) === $routeParams.id)
                    {
                        $scope.editedTodo = $scope.todoList[i];
                        var month = editDateMonth($scope.editedTodo.resolveUntil.monthValue);
                        
                        $scope.editedDate = $scope.editedTodo.resolveUntil.dayOfMonth + "/" +
                                month + "/" + $scope.editedTodo.resolveUntil.year;
                        break;
                    }
                }
            }

            function getMonthFromString(mon) {
                return new Date(Date.parse(mon + " 1, 2012")).getMonth() + 1;
            }
            
            function editDateMonth(month){
                var monthStr = "0";
                if (String(month).length === 1){
                    monthStr += String(month);
                } else{
                    monthStr = String(month);
                }
                return monthStr; 
            }

            function getDate() {
                
                if(typeof $scope.dateI === "undefined")
                {
                    var monthEdit = editDateMonth($scope.editedTodo.resolveUntil.monthValue);
                    $scope.dateI = $scope.editedTodo.resolveUntil.dayOfMonth + "/" + 
                            monthEdit + "/" + $scope.editedTodo.resolveUntil.year;
                }
                
                if ($scope.dateI.length !== 10) {
                    var day = $scope.dateI.charAt(8) + $scope.dateI.charAt(9);
                    var year = "";
                    for (var i = 11; i < 15; i++)
                    {
                        year += $scope.dateI.charAt(i);
                    }
                    var month = $scope.dateI.charAt(4) + $scope.dateI.charAt(5) + $scope.dateI.charAt(6);

                    var get = getMonthFromString(month);
                    var monthVal = editDateMonth(get);
                    return day + "/" + monthVal + "/" + year;
                }
                return $scope.dateI; 

            }
        });
        
        

