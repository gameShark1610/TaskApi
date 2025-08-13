package com.ApiRestTasks.ApiRestTasks.controller;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.model.Task;
import com.ApiRestTasks.ApiRestTasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
A REST API follows the HTTP protocol.
The Controller manages all CRUD operations between the client and the database, but there are some important rules:
-The Controller should communicate with the Service Layer, not directly with the Repository Layer. This ensures that users do not have direct access to the database.
-It is also recommended to avoid direct interaction with Models (Entities). To achieve this, we use DTOs (Data Transfer Objects) for transferring data between layers.
*/

/*
Response Entity <> instead of just DTO
Is basically Spring's "full control" response wrapper, It lets you sent not just the body, but also status codes and headers in you HTTP responses
*/
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks()); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id)); //200 OK
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.createTask(taskRequestDTO)); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Integer id,@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No content
    }

}
