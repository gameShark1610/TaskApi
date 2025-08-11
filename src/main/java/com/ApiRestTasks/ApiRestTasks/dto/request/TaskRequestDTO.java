package com.ApiRestTasks.ApiRestTasks.dto.request;
/*
RequestDTO (Data Transfer Object)
- Used to receive and validate data sent by the client in HTTP requests.
- Keeps API inputs separate from internal Entity models for security and flexibility.
- Often contains validation annotations (@NotNull, @Size, etc.).
- Example: TaskRequestDTO might include fields like title and description, excluding database-generated fields like id or createdAt.
*/
public class TaskRequestDTO {

    private String title;
    private String description;
    private boolean completed;

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
}
