package app.rest;

import app.model.Storage;
import app.model.Todo;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    private Storage storage;
    
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public HttpStatus createTodo(@RequestBody Todo todo) {
        
        this.storage.addTodo(todo);
        return HttpStatus.CREATED;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getTodos() {
        return storage.getTodos(); 
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "{id}/finish")
    public void finishTodo(@PathVariable("id") int id)
    {       
        this.storage.finishTodo(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "{id}/edit")
    public void updateTodo(@PathVariable("id") int id, @RequestBody Todo todo)
    {       
        this.storage.updateTodo(todo, id);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/delete")
    public void deleteTodo(@PathVariable("id") int id)
    {
        this.storage.removeTodo(id);
    }
    
}
