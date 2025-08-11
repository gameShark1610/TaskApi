package com.ApiRestTasks.ApiRestTasks.dto.respone;

import java.util.Date;
/*
ResponseDTO (Data Transfer Object)
- Used to send data back to the client in HTTP responses.
- Contains only the information the client needs to see, hiding internal or sensitive details.
- Allows shaping the response independently of the Entity model, improving API stability and versioning.
- Example: TaskResponseDTO might include id, title, description, and completed status, but exclude internal timestamps or audit fields.
*/
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private boolean completed;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
