package app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class Todo {
    
    private int id;
    private String name;
    private LocalDateTime createdOn;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
    private LocalDate resolveUntil;
    private String description;
    private boolean resolved;
    
    public Todo()
    {
    }

    public Todo(String name, String description, LocalDate resolveUntil, boolean resolved) {
        this.name = name;
        this.resolveUntil = resolveUntil;
        this.description = description;
        this.resolved = resolved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getResolveUntil() {
        return resolveUntil;
    }

    public void setResolveUntil(LocalDate resolveUntil) {
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
