package com.ApiRestTasks.ApiRestTasks.service;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.model.Task;

import java.util.List;
/*
Service Interface
- Defines the contract for the application’s business logic.
- Contains method signatures but no implementation.
- Promotes loose coupling and allows multiple implementations if needed (e.g., different business rules or database strategies).
*/

//I thought in add a class but a good practice is use interfaces and create a carpet called imcp(is short for implementation)
public interface TaskService {
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO getTaskById(Integer id);
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    TaskResponseDTO updateTask(Integer id, TaskRequestDTO taskRequestDTO);
    void deleteTask(Integer id);
}
