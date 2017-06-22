angular.module('todoListApp.services', [])
.factory('todoService', function ($http) {
    'use strict';
    
    var todoStore = {
        todos: [],

        delete: function (todo) {
            var originalTodos = todoStore.todos.slice(0);
            todoStore.todos.splice(todoStore.todos.indexOf(todo), 1);

            return $http.delete('/demo/rest/todos/' + todo.id + '/delete')
                .then(function success() {
                    return todoStore.todos;
                }, function error() {
                    angular.copy(originalTodos, todoStore.todos);
                    return originalTodos;
                });
        },

        getTodos: function () {
            return $http.get('/demo/rest/todos')
                .then(function (resp) {
                    angular.copy(resp.data, todoStore.todos);
                    return todoStore.todos;
                });
        },
        
        finish: function (id) {
            var originalTodos = todoStore.todos.slice(0);

            return $http.put('/demo/rest/todos/' + id + '/finish')
                .then(function success() {
                    return todoStore.todos;
                }, function error() {
                    angular.copy(originalTodos, todoStore.todos);
                    return originalTodos;
                });
        },

        insert: function (todo) {
            var originalTodos = todoStore.todos.slice(0);

            return $http.post('/demo/rest/todos/create', todo)
                .then(function success(resp) {
                    todo.id = resp.data.id;
                    todoStore.todos.push(todo);
                    return todoStore.todos;
                }, function error() {
                    angular.copy(originalTodos, todoStore.todos);
                    return todoStore.todos;
                });
        },

        edit: function (todo) {
            var originalTodos = todoStore.todos.slice(0);

            return $http.put('/demo/rest/todos/' + todo.id + '/edit', todo)
                .then(function success() {
                    return todoStore.todos;
                }, function error() {
                    angular.copy(originalTodos, todoStore.todos);
                    return originalTodos;
                });
        }
    };

    return todoStore;
});
