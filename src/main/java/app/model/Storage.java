package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class Storage {
    
    private final HashMap<Integer, Todo> storage;
    private int index;
    
    public Storage()
    {
        this.storage = new HashMap<>(); 
        this.index = 1; 
    }
    
    public boolean addTodo(Todo obj)
    {
        obj.setId(index);
        obj.setCreatedOn(LocalDateTime.now());
        if(this.storage.get(obj.getId()) == null)
        {
            this.storage.put(obj.getId(), obj);
            index++; 
            return true;
        }
        return false;
    }
    
    public void removeTodo(int id)
    {
        Todo todo = storage.get(id); 
        this.storage.remove(id, todo);
    }
    
    public void finishTodo(int id)
    {
        Todo todo = this.getTodo(id);
        todo.setResolved(true);
        this.storage.replace(id, todo); 
    }
    
    public void updateTodo(Todo newTodo, int id)
    {
        Todo old = this.getTodo(id);
        old.setName(newTodo.getName());
        old.setDescription(newTodo.getDescription());
        old.setResolveUntil(newTodo.getResolveUntil());
        old.setResolved(newTodo.isResolved());
        this.storage.replace(old.getId(), old);
    }
    
    public List<Todo> getTodos()
    {
        Todo[] array = new Todo[this.storage.values().size()];
        List<Todo> todos = new ArrayList<>(); 
        this.storage.values().toArray(array);
        for(int i = 0; i < array.length; i++)
        {
            todos.add(array[i]); 
        }
        
        List<Todo> comparedTodos = new ArrayList<>(); 
        Comparator<Todo> byEndDate = (t1, t2) -> t1.getResolveUntil().compareTo(t2.getResolveUntil());
        Comparator<Todo> byCreateDate = (t1, t2) -> t1.getCreatedOn().compareTo(t2.getCreatedOn());
        
        todos.stream().sorted(byEndDate.thenComparing(byCreateDate)).
                forEach(todo -> comparedTodos.add(todo));
        
        return comparedTodos;
        
    }
    
    public Todo getTodo(int key)
    {
        return this.storage.get(key); 
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
            
    
    
    
    
}
