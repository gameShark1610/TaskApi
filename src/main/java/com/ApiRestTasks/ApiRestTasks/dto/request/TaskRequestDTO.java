package com.ApiRestTasks.ApiRestTasks.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
RequestDTO (Data Transfer Object)
- Used to receive and validate data sent by the client in HTTP requests.
- Keeps API inputs separate from internal Entity models for security and flexibility.
- Often contains validation annotations (@NotNull, @Size, etc.).
- Example: TaskRequestDTO might include fields like title and description, excluding database-generated fields like id or createdAt.
*/
public class TaskRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;

    public TaskRequestDTO(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


}
