package app.model;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class Todo {
    
    private int id;
    private LocalDateTime createdOn;
    private LocalDateTime resolveUntil;
    private String description;
    private boolean resolved;
    
    public Todo()
    {
        this.resolved = false;
    }

    public Todo(int id, LocalDateTime createdOn, LocalDateTime resolveUntil, String description) {
        this.id = id;
        this.createdOn = createdOn;
        this.resolveUntil = resolveUntil;
        this.description = description;
        this.resolved = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getResolveUntil() {
        return resolveUntil;
    }

    public void setResolveUntil(LocalDateTime resolveUntil) {
        this.resolveUntil = resolveUntil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
    
    
}
