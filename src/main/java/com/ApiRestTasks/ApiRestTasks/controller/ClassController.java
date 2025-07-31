package com.ApiRestTasks.ApiRestTasks.controller;

import com.ApiRestTasks.ApiRestTasks.model.Task;
import com.ApiRestTasks.ApiRestTasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ClassController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Integer id){
        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task didnt found with the ID"));
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task task){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task didnt found with the ID"));
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setCompleted(task.isCompleted());
        taskToUpdate.setCreatedAt(task.getCreatedAt());
        return taskRepository.save(taskToUpdate);
    }

    @DeleteMapping("/{id}")
    public  Task deleteTask(@PathVariable Integer id){
        Task taskToDelete = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task didnt found with the ID"));
        return taskToDelete;
    }
}
