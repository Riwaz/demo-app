
package app.rest;

import app.model.Storage;
import app.model.Todo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TodoController {
    
    @Autowired
    private Storage storage;
    
    @RequestMapping(method = RequestMethod.POST, value = "create")
    public HttpStatus createTodo(@RequestBody Todo todo) {
        
        todo.setId(this.storage.getIndex());
        todo.setCreatedOn(LocalDateTime.now());
        this.storage.addTodo(todo);
        
        return HttpStatus.CREATED;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getTodos() {
        return storage.getTodos(); 
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "edit")
    public void updateTodo(@RequestBody Todo todo)
    {
        this.storage.updateTodo(todo);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void deleteTodo(@RequestBody Todo todo)
    {
        this.storage.removeTodo(todo);
    }
    

    
    
    
}
