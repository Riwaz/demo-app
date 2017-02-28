package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import app.model.Todo;
import java.time.LocalDateTime;

@Component
public class Storage {
    
    private final HashMap<Integer, Todo> storage;
    private int index;
    
    public Storage()
    {
        this.storage = new HashMap<>(); 
        this.index = 0;
        Todo x = new Todo(0, LocalDateTime.MIN, LocalDateTime.MIN, "dsasdas"); 
        this.addTodo(x); 
    }
    
    public boolean addTodo(Todo obj)
    {
        if(this.storage.get(obj.getId()) == null)
        {
            this.storage.put(obj.getId(), obj);
            index++; 
            return true;
        }
        return false;
    }
    
    public void removeTodo(Todo obj)
    {
        this.storage.remove(obj.getId(), obj);
    }
    
    public void updateTodo(Todo newObj)
    {
        this.storage.replace(newObj.getId(), newObj);
    }
    
    public List<Todo> getTodos()
    {
        Todo[] array = new Todo[this.storage.values().size()];
        List<Todo> list = new ArrayList<>(); 
        this.storage.values().toArray(array);
        for(int i = 0; i < array.length; i++)
        {
            list.add(array[i]); 
        }
        return list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
            
    
    
    
    
}
